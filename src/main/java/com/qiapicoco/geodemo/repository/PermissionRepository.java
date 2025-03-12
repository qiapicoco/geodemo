package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    /**
     * 根据权限名称查找权限
     *
     * @param permissionName 权限名称
     * @return 查找到的权限 Optional 对象
     */
    Optional<Permission> findByPermissionName(String permissionName);
}