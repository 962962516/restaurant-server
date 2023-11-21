package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.UserCouponsComboBean;
import com.restaurant.project.bean.UserCouponsHistoryBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCouponsHistoryMapper extends BaseMapper<UserCouponsHistoryBean> {
}
