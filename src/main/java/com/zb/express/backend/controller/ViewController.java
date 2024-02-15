package com.zb.express.backend.controller;

import com.zb.express.backend.service.DeliverymanService;
import com.zb.express.backend.service.ExpressCompanyService;
import com.zb.express.commons.constant.Constant;
import com.zb.express.pojo.Deliveryman;
import com.zb.express.pojo.ExpressCompany;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/backend")
@Controller
public class ViewController {

    @Autowired
    private ExpressCompanyService expressCompanyService;

    @Autowired
    private DeliverymanService deliverymanService;

    //后台界面
    @GetMapping("/index")
    public String backendIndex(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute(Constant.SESSION_USER));
        return "backend/index";
    }

    //跳转到后台用户中心界面
    @GetMapping("/personalCenter")
    public String backendUserCenter(Model model, HttpSession session) {
        model.addAttribute("user", session.getAttribute(Constant.SESSION_USER));
        return "backend/personalCenter";
    }

    //后台首页界面
    @GetMapping("/home")
    public String backendHome(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute(Constant.SESSION_USER));
        return "backend/home";
    }

    //后台寄入快递管理界面
    @GetMapping("/inExpress")
    public String backendInExpress(Model model){
        List<ExpressCompany> expressCompanyList=expressCompanyService.queryAllExpressCompany();
        List<Deliveryman> deliverymanList=deliverymanService.queryDeliverymanByStatus();
        model.addAttribute("expressCompanyList",expressCompanyList);
        model.addAttribute("deliverymanList",deliverymanList);
        return "backend/inExpress";
    }

    @GetMapping("/addAdmin")
    public String backendAddAdmin(){
        return "backend/addAdmin";
    }

    @GetMapping("/outExpress")
    public String backendOutExpress(Model model){
        List<Deliveryman> deliverymanList=deliverymanService.queryDeliverymanByStatus();
        model.addAttribute("deliverymanList",deliverymanList);
        return "backend/outExpress";
    }

    @GetMapping("/deliveryman")
    public String backendDeliveryman(){
        return "backend/deliveryman";
    }

    @GetMapping("/expressCompany")
    public String backendExpressCompany(){
        return "backend/expressCompany";
    }

    @GetMapping("/feedback")
    public String backendFeedback(){
        return "backend/feedback";
    }

    @GetMapping("/user")
    public String backendUser(){
        return "backend/user";
    }

}
