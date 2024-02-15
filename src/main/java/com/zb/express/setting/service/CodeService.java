package com.zb.express.setting.service;

public interface CodeService {
    boolean getCode(String phone);

    boolean checkCode(String phone, String code);
}
