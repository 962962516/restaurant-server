package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.ProductBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface ProductMapper extends BaseMapper<ProductBean> {
    @Update("UPDATE product SET product_count = product_count + #{product_count} WHERE product_id = #{product_id}")
    void reviseProductNumber(@Param("product_count") int number, @Param("product_id") int product_id);
    @Update("UPDATE product SET product_count = product_count - #{product_count} WHERE product_id = #{product_id}")
    void reviseOutputNumber(@Param("product_count") int number, @Param("product_id") int product_id);

    @Update("UPDATE product SET product_count = product_count - #{product_count} WHERE product_name = #{product_name}")
    void reviseOutputNumberByName(@Param("product_name") String name, @Param("product_count") int number);
    @Select("SELECT product_id FROM product WHERE product_name = #{product_name} ")
    int findProductIdByProductName(@Param("product_name") String productName);
    @Select("SELECT product_name FROM product WHERE product_id = #{product_id}")
    String findProductNameByProductId(@Param("product_id") int product_id);
    @Update("UPDATE product SET price = #{price} WHERE product_id = #{product_id}")
    void updatePriceByproductId (@Param("price") int price,@Param("product_id") int productId);
    @Select("SELECT adise_list_no FROM product WHERE product_name = #{product_name}")
    Integer getAsideListNoByProductName(@Param("product_name") String name);
    @Select("SELECT price FROM product WHERE product_name = #{product_name}")
    int getPriceByProductName(@Param("product_name") String product_name);
    @Select("SELECT img_url FROM product WHERE product_name = #{product_name}")
    String getImageUrlByProductName(@Param("product_name") String name);
    @Select("SELECT price FROM product WHERE product_id = #{product_id}")
    int getPriceByProductId(@Param("product_id") int product_id);

    @Select("SELECT img_url FROM product WHERE product_id = #{product_id}")
    String getImageUrlByProductId(@Param("product_id") int productId);

}
