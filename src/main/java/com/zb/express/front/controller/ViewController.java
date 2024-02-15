package com.zb.express.front.controller;

import com.zb.express.commons.constant.Constant;
import com.zb.express.front.service.DeliverymanService;
import com.zb.express.front.service.ExpressCompanyService;
import com.zb.express.pojo.Deliveryman;
import com.zb.express.pojo.ExpressCompany;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/front")
@Controller
public class ViewController {

    @Autowired
    private ExpressCompanyService expressCompanyService;

    @Autowired
    private DeliverymanService deliverymanService;
    
    //前台首页界面
    @GetMapping("/index")
    public String frontIndex(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute(Constant.SESSION_USER));
        return "front/index";
    }

    //前台首页界面
    @GetMapping("/home")
    public String frontHome(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute(Constant.SESSION_USER));
        return "front/home";
    }

    //前台个人中心界面
    @GetMapping("/personalCenter")
    public String frontPersonalCenter(Model model, HttpSession session){
        model.addAttribute("user", session.getAttribute(Constant.SESSION_USER));
        return "front/personalCenter";
    }

    //后台寄入快递管理界面
    @GetMapping("/inExpress")
    public String frontInExpress(){
        return "front/inExpress";
    }

    @GetMapping("/feedback")
    public String frontFeedback(){
        return "front/feedback";
    }

    //后台寄入快递管理界面
    @GetMapping("/outExpress")
    public String frontOutExpress(Model model, HttpSession session){
        List<ExpressCompany> expressCompanyList=expressCompanyService.queryAllExpressCompany();
        List<Deliveryman> deliverymanList=deliverymanService.queryDeliverymanByStatus();
        model.addAttribute("expressCompanyList",expressCompanyList);
        model.addAttribute("deliverymanList",deliverymanList);
        model.addAttribute("user", session.getAttribute(Constant.SESSION_USER));
        return "front/outExpress";
    }

}
