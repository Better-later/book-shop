package com.book.bookshop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.book.bookshop.entity.enums.Category;
import com.book.bookshop.entity.enums.Suit;
import lombok.Data;

import java.util.Date;

/**图书实体类
 * @author qianjin
 * @create 2022-02-12 21:21
 */

@Data
@TableName(value = "bs_book")
public class Book extends Model<Book> {  //Model也提供了实体类的crud操作
    @TableId(type = IdType.AUTO)
    private Integer id; //设置主键自增
    private String isbn;
    private String name;
    private String author;
    private String publisher;
    private Date publishDate;
    private double oldPrice;
    private double newPrice;
    private String authorLoc;
    private Suit suit;
    private Category category;
    private String info;
    private String imgUrl;

}



