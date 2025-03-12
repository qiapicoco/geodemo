package com.qiapicoco.geodemo.repository;

import com.qiapicoco.geodemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 查找到的用户 Optional 对象
     */
    Optional<User> findByUsername(String username);
}