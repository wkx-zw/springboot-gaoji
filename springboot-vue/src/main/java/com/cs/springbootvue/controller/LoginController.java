package com.cs.springbootvue.controller;


import com.cs.springbootvue.annotation.PassToken;
import com.cs.springbootvue.entity.User;
import com.cs.springbootvue.exception.BaseException;
import com.cs.springbootvue.service.UserService;
import com.cs.springbootvue.utils.BaseResult;
import com.cs.springbootvue.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yantao
 */
@CrossOrigin
@Controller
@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @PassToken //有这个注解之后，url会免校验
    @PostMapping("/login")
    public BaseResult login(@RequestParam("userName") String userName,@RequestParam("password") String password) {
        System.out.println(password);
        try {
            System.out.println("=======================");
            User user = userService.login(userName,password);

            System.out.println("================" + password);
            String jwtToken = JwtUtils.createToken(user.getId(), user.getUserName());
            //System.out.println("jwtToken  is :  "+ jwtToken);
            return BaseResult.getBaseResult(0, "SUCCESS", jwtToken, user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return BaseResult.getBaseResult(1, e.getMessage(), null);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

}
