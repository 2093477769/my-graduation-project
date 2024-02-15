package com.zb.express.setting.mapper;


import com.zb.express.pojo.User;

import java.util.List;


public interface UserMapper {

    User selectUserByUsernameAndPassword(User user);

    User selectUserByUsername(String username);

    Integer insertUser(User user);

    List<String> selectUserAvatar();
}
