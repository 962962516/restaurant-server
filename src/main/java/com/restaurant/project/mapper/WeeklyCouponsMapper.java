package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.WeeklyCouponsBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface WeeklyCouponsMapper extends BaseMapper<WeeklyCouponsBean> {
    @Select("SELECT combo_id FROM weekly_coupons WHERE weekly_coupons_id = #{weekly_coupons_id}")
    int findComboIdByWeeklyCouponsId(@Param("weekly_coupons_id") Long weeklyCouponsId);
    @Select("SELECT product_id FROM weekly_coupons WHERE weekly_coupons_id = #{weekly_coupons_id}")
    int findProductIdByWeeklyCouponsId(@Param("weekly_coupons_id") Long weeklyCouponsId);
    @Select("SELECT discount FROM weekly_coupons WHERE weekly_coupons_id = #{weekly_coupons_id}")
    double findDiscountByWeeklyCouponsId(@Param("weekly_coupons_id") Long weeklyCouponsId);
    @Select("SELECT integral FROM weekly_coupons WHERE weekly_coupons_id = #{weekly_coupons_id}")
    int findIntegralByWeeklyCouponsId(@Param("weekly_coupons_id") Long weeklyCouponsId);
    @Select("SELECT weekly_coupons_id FROM weekly_coupons WHERE product_id = #{product_id}")
    int findWeeklyCouponsIdByProductId(@Param("product_id") int productId);
    @Select("SELECT weekly_coupons_id FROM weekly_coupons WHERE combo_id = #{combo_id}")
    int findWeeklyCouponsIdByComboId(@Param("combo_id") int comboId);
}
