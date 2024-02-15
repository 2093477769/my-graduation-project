package com.zb.express.backend.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.backend.service.InExpressService;
import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.commons.utils.FileUtils;
import com.zb.express.backend.mapper.InExpressMapper;
import com.zb.express.pojo.InExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class InExpressServiceImpl implements InExpressService {

    @Autowired
    private InExpressMapper inExpressMapper;

    @Value("${upload.path.inExpressImg}")
    private String uploadPath;



    @Override
    public PageResult queryInExpressPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> feedbackList=inExpressMapper.selectInExpressPage(map);
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(feedbackList);
        List<Map<String, Object>> feedbacks = mapPageInfo.getList();
        return new PageResult(mapPageInfo.getTotal(), feedbacks);
    }

    @Override
    public int addInExpress(InExpress inExpress, MultipartFile file) throws IOException {
        String fileName = FileUtils.fileHandle(file, uploadPath);
        inExpress.setImg(Constant.INEXPRESSIMG_URL + fileName);
        inExpress.setCreateTime(DateUtils.dateToString(new Date()));
        int result=inExpressMapper.insertInExpress(inExpress);
        return result;
    }

    @Override
    public Map<String,Object> queryInExpressById(String id) {
        return inExpressMapper.selectInExpressById(id);
    }

    @Override
    public int deleteInExpressById(String id) {
        return inExpressMapper.deleteInExpressById(id);
    }

    @Override
    public int sendInExpress(InExpress inExpress) {
        inExpress.setUpdateTime(DateUtils.dateToString(new Date()));
        return inExpressMapper.updateInExpressStatusById(inExpress);
    }

    @Override
    public InExpress getInExpressById(String id) {
        return inExpressMapper.selectInExpress(id);
    }

    @Override
    public int modifyInExpress(InExpress inExpress, MultipartFile file) throws IOException {
        if (file!=null){
            String fileName = FileUtils.fileHandle(file, uploadPath);
            inExpress.setImg(Constant.INEXPRESSIMG_URL+fileName);
        }
        inExpress.setUpdateTime(DateUtils.dateToString(new Date()));
        return inExpressMapper.updateInExpress(inExpress);
    }

    @Override
    public int queryInExpressCountByCIdODId(String id) {
        return inExpressMapper.selectInExpressCount(id);
    }

    @Override
    public int queryInExpressStatusById(Integer id) {
        return inExpressMapper.selectInExpressStatusById(id);
    }

    @Override
    public int modifyInExpressStatus(InExpress inExpress) {
        return inExpressMapper.updateInExpressStatusById(inExpress);
    }


}
