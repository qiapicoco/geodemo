package com.qiapicoco.geodemo.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class UserPermissionAssociationId implements java.io.Serializable {

    private static final long serialVersionUID = 6800584404336522530L;
    private Integer userId;
    private Integer permissionId;

    // Getters, Setters, equals, and hashCode
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermissionAssociationId that = (UserPermissionAssociationId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, permissionId);
    }
}
