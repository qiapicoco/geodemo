package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.Permission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PermissionRepositoryTest {

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void testSavePermission() {
        Permission permission = new Permission();
        permission.setPermissionName("testPermission");
        Permission savedPermission = permissionRepository.save(permission);
        assertEquals("testPermission", savedPermission.getPermissionName());
    }

    @Test
    public void testGetPermissionById() {
        Permission permission = new Permission();
        permission.setPermissionName("testPermission");
        Permission savedPermission = permissionRepository.save(permission);
        Optional<Permission> foundPermission = permissionRepository.findById(savedPermission.getPermissionId());
        assertTrue(foundPermission.isPresent());
        assertEquals("testPermission", foundPermission.get().getPermissionName());
    }
}