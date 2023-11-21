package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.AdminAddProductHistoryBean;
import com.restaurant.project.bean.AdminNumberHistoryBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminNumberHistoryMapper extends BaseMapper<AdminNumberHistoryBean> {
    @Select("SELECT * FROM admin_number_history WHERE number_changes > 0")
    List<AdminNumberHistoryBean> findAllAddProductHistory();

    @Select("SELECT * FROM admin_number_history WHERE number_changes < 0")
    List<AdminNumberHistoryBean> findAllOutProductHistory();
}
