package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.Permission;
import com.qiapicoco.geodemo.entity.User;
import com.qiapicoco.geodemo.entity.UserPermissionAssociation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserPermissionAssociationRepositoryTest {

    @Autowired
    private UserPermissionAssociationRepository userPermissionAssociationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Test
    public void testSaveUserPermissionAssociation() {
        User user = new User();
        user.setUsername("testUser");
        User savedUser = userRepository.save(user);

        Permission permission = new Permission();
        permission.setPermissionName("testPermission");
        Permission savedPermission = permissionRepository.save(permission);

        UserPermissionAssociation association = new UserPermissionAssociation();
        association.setUser(savedUser);
        association.setPermission(savedPermission);
        UserPermissionAssociation savedAssociation = userPermissionAssociationRepository.save(association);

        assertEquals(savedUser.getUserId(), savedAssociation.getUser().getUserId());
        assertEquals(savedPermission.getPermissionId(), savedAssociation.getPermission().getPermissionId());
    }

    @Test
    public void testGetUserPermissionAssociationById() {
        User user = new User();
        user.setUsername("testUser");
        User savedUser = userRepository.save(user);

        Permission permission = new Permission();
        permission.setPermissionName("testPermission");
        Permission savedPermission = permissionRepository.save(permission);

        UserPermissionAssociation association = new UserPermissionAssociation();
        association.setUser(savedUser);
        association.setPermission(savedPermission);
        UserPermissionAssociation savedAssociation = userPermissionAssociationRepository.save(association);

        Optional<UserPermissionAssociation> foundAssociation = userPermissionAssociationRepository.findById(savedAssociation.getId());
        assertTrue(foundAssociation.isPresent());
        assertEquals(savedUser.getUserId(), foundAssociation.get().getUser().getUserId());
        assertEquals(savedPermission.getPermissionId(), foundAssociation.get().getPermission().getPermissionId());
    }
}