package com.zb.express.setting.service.impl;

import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.commons.utils.MD5Utils;
import com.zb.express.setting.mapper.UserMapper;
import com.zb.express.pojo.User;
import com.zb.express.setting.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpSession session;


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
    public List<String> queryAllAvatar() {
        return userMapper.selectUserAvatar();
    }


}
