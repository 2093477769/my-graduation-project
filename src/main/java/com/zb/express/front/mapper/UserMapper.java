package com.zb.express.front.mapper;


import com.zb.express.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    User selectUserByUsername(String username);

    Integer updateUser(User user);

}
