package com.book.bookshop.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**图书是否为套装
 * @author qianjin
 * @create 2022-02-12 22:00
 */
@Getter
public enum Suit {

    YES(1,"是"),NO(2,"否");

    Suit(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    @EnumValue//标记数据库存的值是code
    private final int code;
    private final String desc;


}
