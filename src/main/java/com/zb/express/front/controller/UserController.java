package com.zb.express.front.controller;

import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.Result;
import com.zb.express.commons.utils.MD5Utils;
import com.zb.express.front.service.UserService;
import com.zb.express.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/front")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    //修改头像
    @PostMapping("/personalCenter/modifyAvatar")
    public Result modifyAvatar(MultipartFile file) {
        if (file.isEmpty()) {
            return new Result(false, "请上传图片");
        }
        try {
            Integer result = userService.editAvatar(file);
            if (result != 1) {
                return new Result(false, "头像修改失败");
            }
            return new Result(true, "头像修改成功,请刷新页面");
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, "图片上传失败");
        }
    }

    //查看用户信息是否完善
    @GetMapping("/index/viewUserInfo")
    public Result viewUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if ("".equals(user.getRealname())||user.getRealname()==null){
            return new Result(false,"请进行信息完善");
        }
        return new Result(true);
    }

    //完善用户信息
    @PostMapping("/index/improveUserInfo")
    public Result improveUserInfo(String realname,String address){
        Integer result=userService.editUserByRealnameAndAddress(realname,address);
        if (result!=1){
            return new Result(false,"完善信息失败请重试");
        }
        return new Result(true,"信息完善成功");
    }

    @PostMapping("/index/modifyPassword")
    public Result modifyPassword(String oldPassword, String newPassword, String confirmNewPassword, HttpSession session) {
        if (!newPassword.equals(confirmNewPassword)) {
            return new Result(false, "两次输入的新密码不一致");
        }
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (!user.getPassword().equals(MD5Utils.MD5Lower(oldPassword))) {
            return new Result(false, "旧密码错误");
        }
        user.setPassword(MD5Utils.MD5Lower(newPassword));
        Integer result = userService.editPassword(user);
        if (result != 1) {
            return new Result(false, "修改失败");
        }
        return new Result(true, "修改成功");
    }

    @PostMapping("/personalCenter/modifyUserInfo")
    public Result modifyUserInfo(String newUsername,String newPhone,String newRealname,String newAddress,HttpSession session){
        User userSession = (User) session.getAttribute(Constant.SESSION_USER);
        if (!userSession.getUsername().equals(newUsername)){
            User user = userService.queryUserByUsername(newUsername);
            if (user != null) {
                return new Result(false, "用户名重复请更换用户名");
            }
        }

        Integer result = userService.editUserInfo(newUsername, newPhone,newRealname,newAddress);
        if (result != 1) {
            return new Result(false, "修改失败");
        }

        return new Result(true, "修改成功,请刷新页面");
    }

}
