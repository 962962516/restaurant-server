package com.restaurant.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.project.bean.ProductImagesBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface ProductImagesMapper extends BaseMapper<ProductImagesBean> {
    @Insert("INSERT INTO product_images (image_id,image_data) VALUES (#{image_id},#{image_data, jdbcType=BLOB})")
    int insertFileData(@Param("image_id") String imageId,@Param("image_data") byte[] file);



}
