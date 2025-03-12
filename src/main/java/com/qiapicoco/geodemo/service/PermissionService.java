package com.qiapicoco.geodemo.service;

import com.qiapicoco.geodemo.entity.Permission;
import com.qiapicoco.geodemo.entity.User;
import com.qiapicoco.geodemo.entity.UserPermissionAssociation;
import com.qiapicoco.geodemo.entity.UserPermissionAssociationId;
import com.qiapicoco.geodemo.repository.PermissionRepository;
import com.qiapicoco.geodemo.repository.UserPermissionAssociationRepository;
import com.qiapicoco.geodemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserPermissionAssociationRepository userPermissionAssociationRepository;

    public UserPermissionAssociation assignPermissionToUser(Integer userId, Integer permissionId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Permission> permissionOptional = permissionRepository.findById(permissionId);

        if (userOptional.isPresent() && permissionOptional.isPresent()) {
            User user = userOptional.get();
            Permission permission = permissionOptional.get();

            UserPermissionAssociationId id = new UserPermissionAssociationId();
            id.setUserId(userId);
            id.setPermissionId(permissionId);

            UserPermissionAssociation association = new UserPermissionAssociation();
            association.setId(id);
            association.setUser(user);
            association.setPermission(permission);

            return userPermissionAssociationRepository.save(association);
        }
        return null;
    }

    public List<UserPermissionAssociation> getPermissionsByUser(Integer userId) {
        return userPermissionAssociationRepository.findByUserId(userId);
    }
}