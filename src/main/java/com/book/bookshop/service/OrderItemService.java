package com.book.bookshop.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.bookshop.entity.OrderItem;
import com.book.bookshop.mapper.OrderItemMapper;
import org.springframework.stereotype.Service;

/**
 * @author qianjin
 * @create 2022-02-19 17:35
 */
@Service
public class OrderItemService extends ServiceImpl<OrderItemMapper, OrderItem> {
}
