package com.zb.express.backend.service;

import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.InExpress;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface InExpressService {

    PageResult queryInExpressPage(Integer pageNo, Integer pageSize, Map<String, Object> map);

    int addInExpress(InExpress inExpress, MultipartFile file) throws IOException;

    Map<String,Object> queryInExpressById(String id);

    int deleteInExpressById(String id);

    int sendInExpress(InExpress inExpress);

    InExpress getInExpressById(String id);

    int modifyInExpress(InExpress inExpress, MultipartFile file) throws IOException;

    int queryInExpressCountByCIdODId(String id);

    int queryInExpressStatusById(Integer id);

    int modifyInExpressStatus(InExpress inExpress);

}
