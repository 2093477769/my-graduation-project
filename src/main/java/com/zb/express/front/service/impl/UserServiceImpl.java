package com.zb.express.front.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.commons.utils.FileUtils;
import com.zb.express.commons.utils.MD5Utils;
import com.zb.express.front.mapper.UserMapper;
import com.zb.express.front.service.UserService;
import com.zb.express.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession session;

    @Value("${upload.path.avatar}")
    private String uploadPath;

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }


    @Override
    public Integer editAvatar(MultipartFile file) throws IOException {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        // 在这里可以对接收到的图片数据进行处理，例如保存到服务器、进行图片处理等
        String fileName = FileUtils.fileHandle(file, uploadPath);
        user.setAvatar(Constant.AVATAR_URL + fileName);
        user.setUpdateTime(DateUtils.dateToString(new Date()));
        Integer result = userMapper.updateUser(user);
        session.setAttribute(Constant.SESSION_USER, user);
        return result;
    }

    @Override
    public Integer editUserByRealnameAndAddress(String realname, String address) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        user.setRealname(realname);
        user.setAddress(address);
        user.setUpdateTime(DateUtils.dateToString(new Date()));
        Integer result = userMapper.updateUser(user);
        session.setAttribute(Constant.SESSION_USER,user);
        return result;
    }

    @Override
    public Integer editUserInfo(String newUsername, String newPhone, String newRealname, String newAddress) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        user.setUsername(newUsername);
        user.setPhone(newPhone);
        user.setRealname(newRealname);
        user.setAddress(newAddress);
        user.setUpdateTime(DateUtils.dateToString(new Date()));
        Integer result= userMapper.updateUser(user);
        session.setAttribute(Constant.SESSION_USER,user);
        return result;
    }

    @Override
    public Integer editPassword(User user) {
        user.setUpdateTime(DateUtils.dateToString(new Date()));
        return userMapper.updateUser(user);
    }
}
