package com.zb.express.front.service;

import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.InExpress;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface InExpressService {

    int queryInExpressStatusById(Integer id);

    int modifyInExpressStatus(InExpress inExpress);

    PageResult queryInExpressPageToFront(Integer pageNo, Integer pageSize, Map<String, Object> map);
}
