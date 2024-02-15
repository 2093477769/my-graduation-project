package com.zb.express.backend.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.backend.service.UserService;
import com.zb.express.backend.mapper.UserMapper;
import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.commons.utils.FileUtils;
import com.zb.express.commons.utils.MD5Utils;
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
    public User queryUserByUsernameAndPassword(User user) {
        user.setPassword(MD5Utils.MD5Lower(user.getPassword()));
        return userMapper.selectUserByUsernameAndPassword(user);
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public Integer saveUser(String phone,String username , String password) {
        password = MD5Utils.MD5Lower(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAvatar(Constant.DEFAULT_AVATAR_URL);
        user.setPhone(phone);
        User sessionUser = (User) session.getAttribute(Constant.SESSION_USER);
        if (sessionUser!=null && sessionUser.getType()==0){
            user.setType(0);
        }else{
            user.setType(1);
        }
        user.setCreateTime(DateUtils.dateToString(new Date()));
        return userMapper.insertUser(user);
    }

    @Override
    public Integer editPassword(User user) {
        user.setUpdateTime(DateUtils.dateToString(new Date()));
        return userMapper.updateUser(user);
    }

    @Override
    public Integer editAdminInfo(String newUsername,String newPhone) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        user.setUsername(newUsername);
        user.setPhone(newPhone);
        user.setUpdateTime(DateUtils.dateToString(new Date()));
        Integer result= userMapper.updateUser(user);
        session.setAttribute(Constant.SESSION_USER,user);
        return result;
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
    public PageResult queryUserForPage(Map<String, Object> map) {
        PageHelper.startPage((Integer) map.get("pageNo"),(Integer) map.get("pageSize"));
        List<User> userList=userMapper.selectUserForPage(map);;
        PageInfo<User> mapPageInfo = new PageInfo<>(userList);
        return new PageResult(mapPageInfo.getTotal(), mapPageInfo.getList());
    }

    @Override
    public Boolean queryUserTypeIsAdminById(String id) {
        User user=userMapper.selectUserById(id);
            if (user.getType()==0){
                return false;
            }
        return true;
    }

    @Override
    public Integer deleteUserById(String id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public User queryUserById(String id) {
        return userMapper.selectUserById(id);
    }
}
