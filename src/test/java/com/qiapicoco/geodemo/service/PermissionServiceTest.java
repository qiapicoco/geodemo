package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.Permission;
import com.qiapicoco.geodemo.repository.PermissionRepository;
import com.qiapicoco.geodemo.service.impl.PermissionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionService permissionService = new PermissionServiceImpl();

    @Test
    public void testSavePermission() {
        Permission permission = new Permission();
        permission.setPermissionName("testPermission");
        when(permissionRepository.save(permission)).thenReturn(permission);

        Permission savedPermission = permissionService.savePermission(permission);
        assertEquals("testPermission", savedPermission.getPermissionName());
        verify(permissionRepository, times(1)).save(permission);
    }

    @Test
    public void testGetPermissionById() {
        Permission permission = new Permission();
        permission.setPermissionId(1);
        permission.setPermissionName("testPermission");
        when(permissionRepository.findById(1)).thenReturn(Optional.of(permission));

        Permission foundPermission = permissionService.getPermissionById(1);
        assertEquals("testPermission", foundPermission.getPermissionName());
        verify(permissionRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllPermissions() {
        Permission permission1 = new Permission();
        permission1.setPermissionName("permission1");
        Permission permission2 = new Permission();
        permission2.setPermissionName("permission2");
        List<Permission> permissions = Arrays.asList(permission1, permission2);
        when(permissionRepository.findAll()).thenReturn(permissions);

        List<Permission> allPermissions = permissionService.getAllPermissions();
        assertEquals(2, allPermissions.size());
        verify(permissionRepository, times(1)).findAll();
    }

    @Test
    public void testDeletePermission() {
        doNothing().when(permissionRepository).deleteById(1);
        permissionService.deletePermission(1);
        verify(permissionRepository, times(1)).deleteById(1);
    }
}