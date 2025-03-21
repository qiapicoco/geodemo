package com.qiapicoco.geodemo.entity;

import jakarta.persistence.*;

//import javax.persistence.*;
//// 复合主键类
//import javax.persistence.Embeddable;


@Entity
@Table(name = "UserPermissionAssociationTable")
public class UserPermissionAssociation {


    @EmbeddedId
    private UserPermissionAssociationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    private Permission permission;

    // Getters and Setters
    public UserPermissionAssociationId getId() {
        return id;
    }

    public void setId(UserPermissionAssociationId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}


