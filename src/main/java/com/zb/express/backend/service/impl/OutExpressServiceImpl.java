package com.zb.express.backend.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zb.express.backend.mapper.OutExpressMapper;
import com.zb.express.backend.service.OutExpressService;
import com.zb.express.commons.entry.PageResult;
import com.zb.express.pojo.OutExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OutExpressServiceImpl implements OutExpressService {

    @Autowired
    private OutExpressMapper outExpressMapper;

    //分页查询
    @Override
    public PageResult queryOutExpressPage(Integer pageNo, Integer pageSize, Map<String, Object> map) {
        PageHelper.startPage(pageNo,pageSize);
        List<Map<String,Object>> feedbackList=outExpressMapper.selectOutExpressPage(map);
        PageInfo<Map<String, Object>> mapPageInfo = new PageInfo<>(feedbackList);
        List<Map<String, Object>> feedbacks = mapPageInfo.getList();
        return new PageResult(mapPageInfo.getTotal(), feedbacks);
    }

    //获取快递公司或者快递员对应的快递数量
    @Override
    public int queryOutExpressCountByCIdOrDId(String id) {
        return outExpressMapper.selectOutExpressCount(id);
    }

    //配送快递修改快递状态及对应快递员
    @Override
    public int modifyOutExpressStatusAndDeliverymanId(OutExpress outExpress) {
        return outExpressMapper.modifyOutExpressStatusAndDeliverymanId(outExpress);
    }
    //通过id获取快递信息
    @Override
    public OutExpress getOutExpressById(String id) {
        return outExpressMapper.selectOutExpressById(id);
    }

    @Override
    public Map<String, Object> queryOutExpressById(String id) {
        return outExpressMapper.selectOutExpress(id);
    }

}
