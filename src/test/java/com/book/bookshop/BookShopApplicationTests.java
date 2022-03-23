package com.book.bookshop;

import com.book.bookshop.entity.Cart;
import com.book.bookshop.mapper.CartMapper;
import com.book.bookshop.mapper.OrderMapper;
import com.book.bookshop.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookShopApplicationTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void findBookList() {
        bookService.list().forEach(System.out::println);
    }

    @Test
    public void findCartList(){

        cartMapper.findCartListByUserId(1).forEach(System.out::println);
    }

    @Test
    public void findOrderList(){
        orderMapper.findOrderAndOrderDetailListByUser(1);
    }
}
