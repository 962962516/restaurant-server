package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.ProductSalesVolumeBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductSalesVolumeMapper extends BaseMapper<ProductSalesVolumeBean> {
    @Update("update product_sales_volume SET sales_volume = sales_volume + #{sales_volume} WHERE product_name = #{product_name}")
    void saveSalesVolume(@Param("product_name") String name ,@Param("sales_volume") int number);
}
