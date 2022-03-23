package com.book.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookshop.entity.CartVo;
import com.book.bookshop.entity.Order;
import com.book.bookshop.entity.OrderItem;
import com.book.bookshop.entity.User;
import com.book.bookshop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author qianjin
 * @create 2022-02-19 17:31
 */
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CartService cartService;

    /**
     * 购买
     */
    public String buy(List<CartVo> cartVos,Integer addrId, HttpSession session){
        //1.生成订单表记录
        Order order = new Order();
        order.setAddressId(addrId);
        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());
        order.setCreateDate(new Date());
        order.setOrderNum(UUID.randomUUID().toString());
//        order.setOrderNum(OrderUtils.createOrderNum());//自定义生成订单编号
        order.setOrderStatus("1");
        orderMapper.insert(order);

        //2.生成订单明细表记录
        List<OrderItem> orderItems = new ArrayList<>();
        List<Integer> cartIds = new ArrayList<>();
        for (CartVo cart: cartVos) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(cart.getBookId());
            orderItem.setCount(cart.getCount());
            orderItem.setOrderId(order.getId());
            orderItems.add(orderItem);
            cartIds.add(cart.getId());
        }
        orderItemService.saveBatch(orderItems);

        //3.删除购物车表中记录
        cartService.removeByIds(cartIds);
        return "success";
    }
    /**
     * 查询用户订单
     */
    public List<Order> findUserOrder(Integer id){
        return orderMapper.findOrderAndOrderDetailListByUser(1);
    }
}
