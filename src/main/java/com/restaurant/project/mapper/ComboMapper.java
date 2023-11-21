package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.ComboBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ComboMapper extends BaseMapper<ComboBean> {
    @Select("SELECT product_name FROM combo WHERE combo_id = #{combo_id}")
    String findProductNameByComboId(@Param("combo_id") int comboId);
    @Select("SELECT combo_id FROM combo WHERE product_name = #{product_name}")
    int findComboIdByProductName(@Param("product_name") String productName);
    @Update("UPDATE combo SET price = #{price} WHERE combo_id = #{combo_id}")
    void updatePriceByComboId (@Param("price") int price,@Param("combo_id") int productId);
    @Update("UPDATE combo SET product_number = product_number + #{product_number} WHERE product_name = #{product_name}")
    void reviseComboNumber(@Param("product_name") String productName, @Param("product_number") int productNumber);
    @Select("SELECT price FROM combo WHERE product_name = #{product_name}")
    int getPriceByComboName(@Param("product_name") String productName);
    @Select("SELECT img_url FROM combo WHERE product_name = #{product_name}")
    String getImageUrlByComboName(@Param("product_name") String name);

    @Select("SELECT img_url FROM combo WHERE combo_id = #{combo_id}")
    String getImageUrlByComboId(@Param("combo_id") int comboId);
}
