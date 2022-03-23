package com.book.bookshop.entity;

import lombok.Data;

/**用户购物车信息展示
 * @author qianjin
 * @create 2022-02-18 10:09
 */
@Data
public class UserCartVo {

    private Integer num;
    private double totalPrice;


}
