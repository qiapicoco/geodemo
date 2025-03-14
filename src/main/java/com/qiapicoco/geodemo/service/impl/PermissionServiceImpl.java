package com.qiapicoco.geodemo.service.impl;

import com.qiapicoco.geodemo.entity.Permission;
import com.qiapicoco.geodemo.repository.PermissionRepository;
import com.qiapicoco.geodemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission getPermissionById(Integer permissionId) {
        return permissionRepository.findById(permissionId).orElse(null);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public void deletePermission(Integer permissionId) {
        permissionRepository.deleteById(permissionId);
    }
}