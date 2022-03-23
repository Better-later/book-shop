package com.book.bookshop.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.bookshop.entity.Address;
import com.book.bookshop.entity.CartVo;
import com.book.bookshop.entity.Order;
import com.book.bookshop.entity.User;
import com.book.bookshop.mapper.CartMapper;
import com.book.bookshop.service.AddressService;
import com.book.bookshop.service.CartService;
import com.book.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author qianjin
 * @create 2022-02-19 14:05
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    CartService cartService;

    @Autowired
    private AddressService addressService;
    @Autowired
    private OrderService orderService;
    /**
     * 确认订单
     */
    @RequestMapping("/confirm")
    public String confirm(String ids, HttpSession session,Model model){
        //查询用户
        List<CartVo> cartVos = cartService.findCartByIds(ids);

        //获取当前用户的收获地址
        QueryWrapper<Address> addressQueryWrapper = new QueryWrapper<>();
        //按照当前用户id找对应地址（Session中获取)
        User user = (User)session.getAttribute("user");
        addressQueryWrapper.eq("user_id",user.getId());
        List<Address> addressList = addressService.list(addressQueryWrapper);

        //将购买的商品信息添加到session中
        session.setAttribute("cartVos",cartVos);

        model.addAttribute("list",cartVos);
        model.addAttribute("addressList",addressList);
        return "confirm_order";
    }

    /**
     * 提交订单
     */
    @RequestMapping("/commitOrder")
    public String commitOrder(Integer addrId,HttpSession session){
        List<CartVo> cartVos = (List<CartVo>) session.getAttribute("cartVos");
        String flag = orderService.buy(cartVos,addrId,session);
        if(flag.equals("success")){
            //跳转至订单列表页
            return "redirect:/order/list";
        }else {
            return "redirect:/book/index";
        }
    }

    /**
     * 显示用户订单列表
     */
    @RequestMapping("/list")
    public String list(HttpSession session,Model model){
        User user = (User)session.getAttribute("user");
        List<Order> orders = orderService.findUserOrder(user.getId());
        model.addAttribute("orders",orders);
        return "order_list";
    }
}
