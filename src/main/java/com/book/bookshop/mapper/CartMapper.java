package com.book.bookshop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.book.bookshop.entity.Cart;
import com.book.bookshop.entity.CartVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qianjin
 * @create 2022-02-17 15:46
 */
@Repository
public interface CartMapper extends BaseMapper<Cart> {

    @Select("SELECT\n" +
            " bsc.*,bsb.`name` AS bookName,bsb.`img_url` AS img_url,\n" +
            " bsb.`new_price` AS new_price\n" +
            " FROM\n" +
            " bs_cart bsc \n" +
            " LEFT JOIN \n" +
            " bs_book bsb \n" +
            " ON \n" +
            " bsc.`book_id` = bsb.`id` \n" +
            " WHERE \n" +
            " bsc.`user_id` = #{userId}")
    List<CartVo> findCartListByUserId(Integer userId);

    @Select({
            "<script>" +
            "SELECT\n" +
            " bsc.*,bsb.`name` AS bookName,bsb.`img_url` AS img_url,\n" +
            " bsb.`new_price` AS new_price\n" +
            " FROM\n" +
            " bs_cart bsc \n" +
            " LEFT JOIN \n" +
            " bs_book bsb \n" +
            " ON \n" +
            " bsc.`book_id` = bsb.`id` \n" +
            " WHERE bsc.id in \n"+
            " <foreach item='item' collection='ids' open='(' separator=',' close=')'>" +
                    "#{item}" +
                    "</foreach>" +
    "</script>"})
    List<CartVo> findCartListByIds(@Param("ids") List<String> ids);
}
