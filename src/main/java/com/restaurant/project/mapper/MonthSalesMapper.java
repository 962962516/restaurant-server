package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.MonthSalesBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MonthSalesMapper extends BaseMapper<MonthSalesBean> {
    @Update("UPDATE month_sales SET price = price + #{price} WHERE month = #{month}")
    void reviseMonthSales(@Param("price") int price, @Param("month") String month);
}
