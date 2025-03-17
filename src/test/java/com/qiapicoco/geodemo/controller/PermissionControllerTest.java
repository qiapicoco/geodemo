package com.qiapicoco.geodemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiapicoco.geodemo.dto.PermissionDto;
import com.qiapicoco.geodemo.entity.Permission;
import com.qiapicoco.geodemo.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PermissionController.class)
public class PermissionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PermissionService permissionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSavePermission() throws Exception {
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setPermissionName("testPermission");
        permissionDto.setDescription("Test description");

        Permission savedPermission = new Permission();
        savedPermission.setPermissionId(1);
        savedPermission.setPermissionName("testPermission");
        savedPermission.setDescription("Test description");

        when(permissionService.savePermission(org.mockito.ArgumentMatchers.any(Permission.class))).thenReturn(savedPermission);

        mockMvc.perform(post("/api/permissions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(permissionDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.permissionId").value(1))
                .andExpect(jsonPath("$.permissionName").value("testPermission"));
    }

    @Test
    public void testGetPermissionById() throws Exception {
        Permission permission = new Permission();
        permission.setPermissionId(1);
        permission.setPermissionName("testPermission");

        when(permissionService.getPermissionById(1)).thenReturn(permission);

        mockMvc.perform(get("/api/permissions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.permissionId").value(1))
                .andExpect(jsonPath("$.permissionName").value("testPermission"));
    }

    @Test
    public void testGetAllPermissions() throws Exception {
        Permission permission1 = new Permission();
        permission1.setPermissionId(1);
        permission1.setPermissionName("permission1");

        Permission permission2 = new Permission();
        permission2.setPermissionId(2);
        permission2.setPermissionName("permission2");

        List<Permission> permissions = Arrays.asList(permission1, permission2);

        when(permissionService.getAllPermissions()).thenReturn(permissions);

        mockMvc.perform(get("/api/permissions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].permissionName").value("permission1"))
                .andExpect(jsonPath("$[1].permissionName").value("permission2"));
    }

    @Test
    public void testDeletePermission() throws Exception {
        mockMvc.perform(delete("/api/permissions/1"))
                .andExpect(status().isNoContent());
    }
}