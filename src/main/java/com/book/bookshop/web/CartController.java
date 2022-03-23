package com.book.bookshop.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.bookshop.entity.Cart;
import com.book.bookshop.entity.CartVo;
import com.book.bookshop.entity.User;
import com.book.bookshop.entity.UserCartVo;
import com.book.bookshop.service.CartService;
import com.book.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**购物车控制器
 * @author qianjin
 * @create 2022-02-17 15:37
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @ResponseBody
    @RequestMapping("add")
    public String add(Cart cart , HttpSession session){ //session中获取user.id
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id",user.getId());  //user.id作为数据库user_id键
        cartQueryWrapper.eq("book_id",cart.getBookId());
        Cart queryCart = cartService.getOne(cartQueryWrapper);
        //判断当前购买是否和购物车书一致
        if(queryCart == null){
            cartService.save(cart);
        } else {
            queryCart.setCount(queryCart.getCount() + cart.getCount());
            cartService.updateById(queryCart);
        }
        return "success";
    }

    /**
     * 查询当前用户的购物车
     */
    @RequestMapping("/list")
    public String list(HttpSession session, Model model){

        User user = (User)session.getAttribute("user");
        List<CartVo> cartVos = cartService.findCartByUserId(user.getId());
        UserCartVo userCartVo = new UserCartVo();
        //用户购物车结果放入session中
        userCartVo.setNum(cartVos.size());
        userCartVo.setTotalPrice(cartService.getCartItemTotal(cartVos));
        session.setAttribute("userCartInfo",userCartVo);
        model.addAttribute("cartList",cartVos);
        return "cart";
    }

    /**
     * 更新购物车信息
     */
    @ResponseBody
    @RequestMapping("/update")
    public String update(HttpSession session,Cart cart){
        //先更新，再查询
        cartService.updateById(cart);
        User user = (User) session.getAttribute("user");
        List<CartVo> cartVos = cartService.findCartByUserId(user.getId());
        //将用户的购物车信息存放到session中
//        UserCartVo userCartVo = cartService.wrapperCart(cartVos);
//        session.setAttribute("userCartInfo",userCartVo);

        double price = cartService.getCartItemTotal(cartVos);
        return String.valueOf(price);

    }

    /**
     * 删除购物车
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(String ids){
        return cartService.batchDelete(ids);
    }

}
