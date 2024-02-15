package com.zb.express.backend.mapper;


import com.zb.express.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User selectUserByUsernameAndPassword(User user);

    User selectUserByUsername(String username);

    Integer insertUser(User user);

    Integer updateUser(User user);

    List<User> selectUserForPage(Map<String, Object> map);

    Integer deleteUserById(String id);

    User selectUserById(String id);
}
