package com.qiapicoco.geodemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiapicoco.geodemo.model.domain.User;
// 在UserMapper接口上方添加注解
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper
 */
public interface UserMapper extends BaseMapper<User> {

}



