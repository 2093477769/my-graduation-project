package com.zb.express.setting.service;



import com.zb.express.pojo.User;

import java.util.List;

public interface UserService {
    User queryUserByUsernameAndPassword(User user);

    User queryUserByUsername(String username);

    Integer saveUser(String phone, String username, String password);


    List<String> queryAllAvatar();
}
