package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<UserBean> {
    @Select("SELECT user_info FROM user WHERE username = #{username}")
    Integer getUserInfo(@Param("username") String username);
    @Update("UPDATE user SET is_vip = 1 WHERE user_id = #{user_id}")
    int setUserVip(@Param("user_id") Long userId);
    @Update("UPDATE user SET user_integral = user_integral + #{user_integral} WHERE user_id = #{user_id}")
    int addUserIntegral(@Param("user_integral") int integral,@Param("user_id") Long userId);
    @Select("SELECT user_integral FROM user WHERE user_id = #{user_id}")
    int findIntegralByUserId(@Param("user_id") Long userId);
    @Update("UPDATE user SET user_integral = user_integral - #{user_integral} WHERE user_id = #{user_id}")
    void reduceIntegral(@Param("user_integral") int integral, @Param("user_id") Long userId);
    @Update("UPDATE user SET email = #{email} , user_phone = #{user_phone} WHERE user_id = #{user_id}")
    void saveUserPersonalInfo(@Param("email") String email ,@Param("user_phone") String userPhone,@Param("user_id") Long userId);
    @Update("Update user SET username = #{username} WHERE user_id = #{user_id}")
    void changeAdminUsername(@Param("username") String username, @Param("user_id") Long userId);
    //product表
    @Select("SELECT price FROM product WHERE product_id = #{product_id}")
    int findPriceByProductId(@Param("product_id") int productId);
    @Select("SELECT product_name FROM product WHERE product_id = #{product_id}")
    String findProductNameByProductId(@Param("product_id") int productId);
    @Select("SELECT description FROM product WHERE product_id = #{product_id}")
    String findDescriptionByProductId(@Param("product_id") int productId);
    @Update("UPDATE user SET password = #{password} WHERE user_id = #{user_id}")
    void updateAdminPassword(@Param("password") String password,@Param("user_id") Long userId);
    @Select("SELECT username FROM user WHERE user_id = #{user_id}")
    String getUsername(@Param("user_id") Long userId);

    @Select("SELECT is_vip FROM user WHERE user_id = #{user_id}")
    int getUserIsVip(@Param("user_id") Long userId);

    //coupons表
    @Select("SELECT discount FROM coupons WHERE product_id = #{product_id}")
    double findProductDiscountByProductId(@Param("product_id") int productId);
    @Select("SELECT coupon_id FROM coupons WHERE product_id = #{product_id}")
    int findCouponIdByProductId(@Param("product_id") int productId);
    @Select("SELECT product_id FROM coupons WHERE coupon_id = #{coupon_id}")
    int findProductIdByCouponId(@Param("coupon_id") int couponId);
    @Select("SELECT COUNT(*) FROM restaurant.combo_product WHERE combo_id = #{comboId}")
    int getCountOfComboProducts(@Param("comboId") int comboId);

    //combo数据库操作
    @Select("SELECT price FROM combo WHERE combo_id = #{combo_id}")
    int findPriceByComboId(@Param("combo_id") int comboId);
    @Select("SELECT product_name FROM combo WHERE combo_id = #{combo_id}")
    String findComboNameByComboId(@Param("combo_id") int comboId);
    @Select("SELECT description FROM combo WHERE combo_id = #{combo_id}")
    String findComboDescriptionByComboId(@Param("combo_id") int comboId);

    //coupons_combo数据库操作
    @Select("SELECT discount FROM coupons_combo WHERE combo_id = #{combo_id}")
    double findComboDiscountByComboId(@Param("combo_id") int comboId);
    @Select("SELECT integral FROM coupons_combo WHERE combo_id = #{combo_id}")
    int findComboIntegralFromComboId(@Param("combo_id") int comboId);

    //user_coupons_combo
    @Select("SELECT combo_id FROM user_coupons_combo WHERE user_id = #{user_id}")
    int findComboIdByUserId(@Param("user_id") Long userId);


}
