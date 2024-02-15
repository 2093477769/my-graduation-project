package com.zb.express.setting.controller;

import com.zb.express.setting.service.CodeService;
import com.zb.express.setting.service.UserService;
import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.Result;
import com.zb.express.pojo.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //跳转到登录页面
    @GetMapping("/")
    public String toLogin() {
        return "login";
    }

    //跳转到注册页面
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    //退出登录,清除session并跳转到登录页面
    @GetMapping("/logout")
    private String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }


    //登录业务
    @PostMapping("/login")
    @ResponseBody
    public Result toLogin(User user, HttpSession session) {
        Result result = new Result(false);

        user = userService.queryUserByUsernameAndPassword(user);
        if (user == null) {
            result.setMessage("用户名或密码错误");
            return result;
        }
        session.setAttribute(Constant.SESSION_USER, user);
        result.setFlag(true);
        return result;
    }

    //获取注册验证码
    @PostMapping("/getCode")
    @ResponseBody
    public Result getCode(@RequestParam String phone) {
        String key = Constant.KEY_SMS_CODE_REG + phone;
        if (stringRedisTemplate.hasKey(key)) {
            return new Result(false, "验证码还能使用");
        } else {
            boolean flag = codeService.getCode(phone);
            if (flag) {
                return new Result(true, "验证码发送成功");
            } else {
                return new Result(false, "验证码发送失败");
            }
        }


    }

    //注册用户
    @PostMapping("/register")
    @ResponseBody
    public Result register(String phone, String code, String username,
                           String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return new Result(false, "两次密码输入不一致");
        }
        if (!codeService.checkCode(phone, code)) {
            return new Result(false, "验证码无效");
        }

        User user = userService.queryUserByUsername(username);
        if (user != null) {
            return new Result(false, "用户名重复请更换用户名");
        }

        Integer registerResult = userService.saveUser(phone, username, password);
        if (registerResult != 1) {
            return new Result(false, "注册失败");
        }
        return new Result(true, "注册成功");
    }


}
