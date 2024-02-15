package com.zb.express.front.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.commons.constant.Constant;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.commons.utils.DateUtils;
import com.zb.express.commons.utils.FileUtils;
import com.zb.express.front.mapper.OutExpressMapper;
import com.zb.express.front.service.OutExpressService;
import com.zb.express.pojo.OutExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OutExpressServiceImpl implements OutExpressService {

    @Autowired
    private OutExpressMapper outExpressMapper;

    @Value("${upload.path.outExpressImg}")
    private String uploadPath;


    @Override
    public PageResult queryOutExpressPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> feedbackList=outExpressMapper.selectOutExpressPage(map);
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(feedbackList);
        List<Map<String, Object>> feedbacks = mapPageInfo.getList();
        return new PageResult(mapPageInfo.getTotal(), feedbacks);
    }

    @Override
    public Map<String, Object> queryOutExpressById(String id) {
        return outExpressMapper.selectOutExpressById(id);
    }

    @Override
    public int addOutExpress(OutExpress outExpress, MultipartFile file) throws IOException {
        String fileName = FileUtils.fileHandle(file, uploadPath);
        outExpress.setImg(Constant.OUTEXPRESSIMG_URL + fileName);
        outExpress.setCreateTime(DateUtils.dateToString(new Date()));
        int result=outExpressMapper.insertOutExpress(outExpress);
        return result;
    }

    @Override
    public int deleteOutExpressById(String id) {
        return outExpressMapper.deleteOutExpressById(id);
    }

    @Override
    public OutExpress queryOutExpress(String id) {
        return outExpressMapper.selectOutExpress(id);
    }

    @Override
    public int modifyOutExpress(OutExpress newOutExpress, MultipartFile file) throws IOException {
        if (file!=null){
            String fileName = FileUtils.fileHandle(file, uploadPath);
            newOutExpress.setImg(Constant.OUTEXPRESSIMG_URL+fileName);
        }
        newOutExpress.setUpdateTime(DateUtils.dateToString(new Date()));
        return outExpressMapper.updateOutExpress(newOutExpress);
    }

}
