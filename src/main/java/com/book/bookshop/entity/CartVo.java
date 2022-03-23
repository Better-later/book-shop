package com.book.bookshop.entity;

import lombok.Data;

/**购物车书籍信息展示
 * @author qianjin
 * @create 2022-02-17 17:22
 */
@Data
public class CartVo {
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private Integer count;
    private String bookName;
    private String imgUrl;
    private double newPrice;
}
