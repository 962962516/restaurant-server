package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.UserOderBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserOderMapper extends BaseMapper<UserOderBean> {
    @Select("SELECT product_id FROM product WHERE product_name = #{product_name}")
    int findProductIdByProductName(@Param("product_name") String productName);

    @Select("SELECT price FROM product WHERE product_name = #{product_name} UNION ALL SELECT price FROM combo WHERE product_name = #{product_name}")
    int findProductPriceByName(@Param("product_name") String name);
}
