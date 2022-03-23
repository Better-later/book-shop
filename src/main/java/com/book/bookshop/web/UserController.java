package com.book.bookshop.web;

import com.book.bookshop.entity.User;
import com.book.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**用户控制器
 * @author qianjin
 * @create 2022-02-14 22:05
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/checkUserName")
    public String checkUserName(String username){
        return userService.checkUser(username);
    }

    /**
     * 用户注册
     */
    @ResponseBody
    @RequestMapping("register")
    public String register(User user){
        userService.save(user);
        return "success";
    }

    /**
     * 用户登录
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(User user, HttpSession session){
        String check = userService.loginCheck(user,session);
        return  check;
    }

    /**
     * 安全退出
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/home/index";
    }

}
