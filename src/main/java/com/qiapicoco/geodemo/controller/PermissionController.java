package com.qiapicoco.geodemo.controller;

import com.qiapicoco.geodemo.entity.UserPermissionAssociation;
import com.qiapicoco.geodemo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @PostMapping("/assign/{userId}/{permissionId}")
    public ResponseEntity<UserPermissionAssociation> assignPermissionToUser(
            @PathVariable(required = true) Integer userId,
            @PathVariable(required = true) Integer permissionId) {
        if (userId == null || permissionId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserPermissionAssociation association = permissionService.assignPermissionToUser(userId, permissionId);
        if (association != null) {
            return new ResponseEntity<>(association, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserPermissionAssociation>> getPermissionsByUser(@PathVariable Integer userId) {
        List<UserPermissionAssociation> associations = permissionService.getPermissionsByUser(userId);
        return new ResponseEntity<>(associations, HttpStatus.OK);
    }


}