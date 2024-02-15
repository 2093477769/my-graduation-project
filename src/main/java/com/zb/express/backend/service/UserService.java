package com.zb.express.backend.service;


import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UserService {
    User queryUserByUsernameAndPassword(User user);

    User queryUserByUsername(String username);

    Integer saveUser(String phone, String username, String password);

    Integer editPassword(User user);

    Integer editAdminInfo(String newUsername,String newPhone);

    Integer editAvatar(MultipartFile file) throws IOException;




    PageResult queryUserForPage(Map<String, Object> map);

    Boolean queryUserTypeIsAdminById(String id);

    Integer deleteUserById(String id);

    User queryUserById(String id);
}
