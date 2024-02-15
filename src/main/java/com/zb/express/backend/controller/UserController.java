package com.zb.express.backend.controller;

import com.zb.express.backend.service.UserService;
import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.entry.Result;
import com.zb.express.commons.utils.MD5Utils;
import com.zb.express.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/backend")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    //管理员修改密码
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

    //修改管理员信息
    @PostMapping("/personalCenter/modifyAdminInfo")
    public Result modifyAdminInfo(String newUsername, String newPhone,HttpSession session) {
        User userSession = (User) session.getAttribute(Constant.SESSION_USER);
        if (!userSession.getUsername().equals(newUsername)){
            User user = userService.queryUserByUsername(newUsername);
            if (user != null) {
                return new Result(false, "用户名重复请更换用户名");
            }
        }
        Integer result = userService.editAdminInfo(newUsername, newPhone);
        if (result != 1) {
            return new Result(false, "修改失败");
        }

        return new Result(true, "修改成功,请刷新页面");
    }

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
    


    @GetMapping("/user/queryUserForPage")
    public PageResult queryUserForPage(
            Integer pageNo,Integer pageSize,String username, String phone, String type,String address){
        Map<String,Object> map=new HashMap<>();
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        map.put("username",username);
        map.put("phone",phone);
        map.put("type",type);
        map.put("address",address);
        PageResult result = userService.queryUserForPage(map);
        return result;
    }

    @PostMapping("/user/deleteUserById")
    public Result deleteUserByIds(String id){
        Boolean flag=userService.queryUserTypeIsAdminById(id);
        if (!flag){
            return new Result(false,"对不起,您没有权限删除管理员用户");
        }

        Integer result=userService.deleteUserById(id);
        if (result==0){
            return new Result(false,"删除失败");
        }
        return new Result(true,"删除成功");
    }

    @PostMapping("/user/queryUserById")
    public Result queryUserById(String id){
        User user=userService.queryUserById(id);
        if (user==null){
            return new Result(false,"系统异常请稍后重试");
        }
        return new Result(true,"",user);
    }


}
