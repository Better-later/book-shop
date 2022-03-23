package com.book.bookshop.web;

import com.book.bookshop.entity.Address;
import com.book.bookshop.entity.User;
import com.book.bookshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * @author qianjin
 * @create 2022-02-19 16:33
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ResponseBody
    @RequestMapping("/save")
    public String save(Address address ,HttpSession session){
        User user = (User)session.getAttribute("user");
        address.setUserId(user.getId());
        if(address.getIsDefault() != null && address.getIsDefault().equals("on")){
            address.setIsDefault("1");
        }
        addressService.save(address);
        return "success";
    }

}
