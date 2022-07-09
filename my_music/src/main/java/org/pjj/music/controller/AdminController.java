package org.pjj.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.pjj.music.service.AdminService;
import org.pjj.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 管理员 Controller
 * @author PengJiaJun
 * @Date 2021/11/7 16:41
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 判断是否登录成功
     */
    @PostMapping(value = "/login/status")
    public Object loginStatus(String name,String password,HttpSession session){
        JSONObject jsonObject = new JSONObject();

        boolean flag = adminService.verifyPassword(name,password);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"登录成功");
            session.setAttribute(Const.NAME,name);
        }else{
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"用户名或密码错误");
        }

        return jsonObject;

    }

}
