package com.zb.express.setting.service.impl;

import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.utils.VerificationCodeUtils;
import com.zb.express.setting.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean getCode(String phone) {
        try {
            String key = Constant.KEY_SMS_CODE_REG + phone;
            //生成验证码
            String code = VerificationCodeUtils.verification();
            //把短信验证码存到Redis中
            stringRedisTemplate.boundValueOps(key).set(code, 1, TimeUnit.MINUTES);
            System.out.println("验证码为:"+code);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkCode(String phone, String code) {
        String key =Constant.KEY_SMS_CODE_REG+phone;
        System.out.println(key);
        if (stringRedisTemplate.hasKey(key)){
            String querySmsCode = stringRedisTemplate.boundValueOps(key).get();
            if (code.equals(querySmsCode)){
                return true;
            }
        }
        return false;
    }
}
