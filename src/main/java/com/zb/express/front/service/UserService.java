package com.zb.express.front.service;

import com.zb.express.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    User queryUserByUsername(String username);

    Integer editAvatar(MultipartFile file) throws IOException;

    Integer editUserByRealnameAndAddress(String realname, String address);

    Integer editUserInfo(String newUsername, String newPhone, String newRealname, String newAddress);


    Integer editPassword(User user);
}
