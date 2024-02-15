package com.zb.express.front.service;

import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.OutExpress;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface OutExpressService {

    PageResult queryOutExpressPage(Integer pageNo, Integer pageSize, Map<String, Object> map);



    Map<String, Object> queryOutExpressById(String id);

    int addOutExpress(OutExpress outExpress, MultipartFile file) throws IOException;

    int deleteOutExpressById(String id);

    OutExpress queryOutExpress(String id);

    int modifyOutExpress(OutExpress newOutExpress, MultipartFile file) throws IOException;
}
