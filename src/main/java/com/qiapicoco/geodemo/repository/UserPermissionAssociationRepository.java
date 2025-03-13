package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.UserPermissionAssociation;
import com.qiapicoco.geodemo.entity.UserPermissionAssociationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPermissionAssociationRepository extends JpaRepository<UserPermissionAssociation, UserPermissionAssociationId> {

    /**
     * 根据用户 ID 查找该用户的所有权限关联记录
     *
     * @param user_Id 用户 ID
     * @return 权限关联记录列表
     */
    List<UserPermissionAssociation> findByUserId(Integer user_Id);
}