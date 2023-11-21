package com.restaurant.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.restaurant.project.bean.*;
import com.restaurant.project.bean.request.AdminRequest.ReviseComboReq;
import com.restaurant.project.bean.request.AdminRequest.ReviseProductNumberReq;
import com.restaurant.project.bean.request.AdminRequest.ReviseProductReq;
import com.restaurant.project.bean.response.AdminResponse.AddComboHistoryRsp;
import com.restaurant.project.bean.response.AdminResponse.AddProductHistoryRsp;
import com.restaurant.project.controller.AdminController;
import com.restaurant.project.mapper.*;
import com.restaurant.project.mapper.AdminOderFinishedMapper;
import com.restaurant.project.utils.UploadPicture;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.servlet.http.PushBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Resource
    private AdminOderMapper adminOderMapper;
    @Resource
    private AdminOderFinishedMapper adminOderFinishedMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ComboMapper comboMapper;
    @Resource
    private ComboProductMapper comboProductMapper;
    @Resource
    private ProductImagesMapper productImagesMapper;
    @Resource
    private AdminNumberHistoryMapper adminNumberHistoryMapper;
    @Resource
    private AdminAddProductHistoryMapper adminAddProductHistoryMapper;
    @Resource
    private AdminAddComboHistoryMapper adminAddComboHistoryMapper;
    @Resource
    private ProductSalesVolumeMapper productSalesVolumeMapper;
    @Resource
    private AdminDeleteProductHistoryMapper adminDeleteProductHistoryMapper;
    @Resource
    private WeeklyCouponsMapper weeklyCouponsMapper;

    public void saveAdminOder(AdminOderBean adminOderBean) {
        adminOderMapper.insert(adminOderBean);
    }

    public List<AdminOderBean> getAllUserOder() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<AdminOderBean> list = adminOderMapper.selectList(queryWrapper);
        return list;
    }

    public void deleteOder(AdminOderBean adminOderBean) {
        QueryWrapper<AdminOderBean> queryWrapper = new QueryWrapper();
        String name = adminOderBean.getName();
        int oderNumber = adminOderBean.getOderNumber();
        queryWrapper.eq("name", name).eq("oder_number", oderNumber);
        adminOderMapper.delete(queryWrapper);
    }

    public void saveFinishedOder(AdminOderBean adminOderBean) {
        AdminOderFinishedBean adminOderFinishedBean = new AdminOderFinishedBean();
        adminOderFinishedBean.setName(adminOderBean.getName());
        adminOderFinishedBean.setNumber(adminOderBean.getNumber());
        adminOderFinishedBean.setOderNumber(adminOderBean.getOderNumber());
        adminOderFinishedBean.setOderTime(adminOderBean.getOderTime());
        adminOderFinishedBean.setPrice(adminOderBean.getPrice());
        adminOderFinishedMapper.insert(adminOderFinishedBean);
    }

    public List<AdminOderFinishedBean> getFinishedOder() {
        QueryWrapper<AdminOderFinishedBean> queryWrapper = new QueryWrapper<>();
        List<AdminOderFinishedBean> adminOderFinishedBeans = adminOderFinishedMapper.selectList(queryWrapper);
        return adminOderFinishedBeans;
    }

    public List<ProductBean> getAllProduct() {
        QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
        List<ProductBean> productBeans = productMapper.selectList(queryWrapper);
        return productBeans;
    }

    public void reviseProduct(ProductBean productBean) {
        updateProduct(productBean);
    }
    public void updateProduct(ProductBean productBean){
        //数据更新
        if(productBean.getProductName() != null || productBean.getDescription() != null ||
                productBean.getIsHot() == 1 || productBean.getIsHot() == 0){

            UpdateWrapper<ProductBean> updateWrapper = new UpdateWrapper();
            updateWrapper.eq("product_id", productBean.getProductId());

            productBean.setProductCount(productBean.getProductCount());
            productBean.setAdiseListNo(productBean.getAdiseListNo());
            productMapper.update(productBean, updateWrapper);
        }
    }

    public List<ComboBean> getAllCombo() {
        QueryWrapper<ComboBean> queryWrapper = new QueryWrapper<>();
        List<ComboBean> comboBeans = comboMapper.selectList(queryWrapper);
        return comboBeans;
    }

    public void reviseCombo(ComboBean comboBean) {
        UpdateWrapper<ComboBean> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("combo_id", comboBean.getComboId());
        if (comboBean.getProductName() != null || comboBean.getDescription() != null ||
                comboBean.getPrice() != 0 || comboBean.getIsHot() == 1 || comboBean.getIsHot() == 0) {
        }
        comboBean.setProductNumber(comboBean.getProductNumber());
        comboMapper.update(comboBean, updateWrapper);
    }

    public void reviseProductNumber(int productId, int number) {
        productMapper.reviseProductNumber(number, productId);
    }

    public void reviseOutputProductNumber(int productId, int number) {
        productMapper.reviseOutputNumber(number, productId);
    }

    public List<ProductBean> searchProduct(String productName) {
        QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("product_name", productName);
        return productMapper.selectList(queryWrapper);
    }

    public List<ComboBean> searchCombo(String productName){
        QueryWrapper<ComboBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("product_name",productName);
        return comboMapper.selectList(queryWrapper);
    }

    public void deleteImage(String imageUrl) throws IOException {
        String imageDir = "src/main/resources/static/images/";
        // 解析URL中的文件名
        String fileName = null;
        try {
            fileName = getFileNameFromUrl(imageUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // 构建图片文件的完整路径
        String filePath = imageDir + fileName;

        // 删除文件
        Path imagePath = Paths.get(filePath);
        Files.deleteIfExists(imagePath);
    }

    private String getFileNameFromUrl(String imageUrl) throws URISyntaxException {
        URI uri = new URI(imageUrl);
        String path = uri.getPath();
        String[] pathSegments = path.split("/");
        return pathSegments[pathSegments.length - 1];
    }

    public String saveFileToService(String uploadDir,MultipartFile file) throws IOException {
        //要保存文件的目录
        String url = UploadPicture.uploadImage(uploadDir, file);
        return url;
    }

    public org.springframework.core.io.Resource loadImageAsResource(String fileName) throws IOException {
        String imageDir = "src/main/resources/static/images/";
        Path imagePath = Paths.get(imageDir, fileName);
        org.springframework.core.io.Resource resource = new UrlResource(imagePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new IOException("无法读取图片文件: " + fileName);
        }
    }

    public void deleteMoreImages() {
        QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
        List<ProductBean> productBeanList = productMapper.selectList(queryWrapper);

        QueryWrapper<ComboBean> queryWrapper1 = new QueryWrapper<>();
        List<ComboBean> comboBeanList = comboMapper.selectList(queryWrapper1);

        // 获取/static/images目录下的所有文件名
        File imageDirectory = new File("src/main/resources/static/images");
        String[] imageFiles = imageDirectory.list();

        // 获取数据库中存在的文件名集合
        List<String> dbImageUrls = productBeanList.stream()
                .map(ProductBean::getImgUrl)
                .collect(Collectors.toList());

        // 将comboBeanList的imgUrl添加到dbImageUrls中
        dbImageUrls.addAll(comboBeanList.stream()
                .map(ComboBean::getImgUrl)
                .collect(Collectors.toList()));

        // 检查/static/images目录下的文件是否存在于数据库中
        for (String fileName : imageFiles) {
            if (!dbImageUrls.contains("http://localhost:8080/images/" + fileName)) {
                // 文件不存在于数据库中，删除它
                File fileToDelete = new File(imageDirectory, fileName);
                if (fileToDelete.delete()) {
                    System.out.println("删除多余文件成功: " + fileName);
                } else {
                    System.out.println("删除多余文件失败: " + fileName);
                }
            }

        }

    }


    public void saveAdminReviseNumberHistory(Long adminId, ReviseProductNumberReq reviseProductNumberReq,int num){
        AdminNumberHistoryBean adminNumberHistoryBean = new AdminNumberHistoryBean();
        adminNumberHistoryBean.setAdminId(adminId);
        int numberChanges = reviseProductNumberReq.getNumber();
        if (num == 2) {
            numberChanges = -numberChanges;
        }
        adminNumberHistoryBean.setNumberChanges(numberChanges);
        adminNumberHistoryBean.setProductId(reviseProductNumberReq.getProductId());
        //生成当前时间
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd HH:mm:ss");
        String format = simpleDateFormat.format(timestamp);

        adminNumberHistoryBean.setTime(format);
        adminNumberHistoryMapper.insert(adminNumberHistoryBean);
    }

    public void saveAdminAddProductHistory(Long adminId, String name){
        AdminAddProductHistoryBean adminAddProductHistoryBean = new AdminAddProductHistoryBean();
        adminAddProductHistoryBean.setAdminId(adminId);
        adminAddProductHistoryBean.setProductName(name);
        //当前时间戳
        //生成当前时间
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd HH:mm:ss");
        String format = simpleDateFormat.format(timestamp);
        adminAddProductHistoryBean.setTime(format);
        adminAddProductHistoryMapper.insert(adminAddProductHistoryBean);
    }
    public List<AddProductHistoryRsp> numberHistory(int show){
        List<AddProductHistoryRsp> addProductHistoryRspList = new ArrayList<>();
        List<AdminNumberHistoryBean> adminNumberHistoryBeanList = new ArrayList<>();
        if (show == 1) {
            adminNumberHistoryBeanList = adminNumberHistoryMapper.findAllAddProductHistory();
        }else {
            adminNumberHistoryBeanList = adminNumberHistoryMapper.findAllOutProductHistory();
        }

        for (AdminNumberHistoryBean adminNumberHistoryBean : adminNumberHistoryBeanList) {
            AddProductHistoryRsp addProductHistoryRsp = new AddProductHistoryRsp();
            addProductHistoryRsp.setAdminId(adminNumberHistoryBean.getAdminId());
            if (addProductHistoryRsp.getNumberChanges() < 0 ) {
                addProductHistoryRsp.setNumberChanges(-adminNumberHistoryBean.getNumberChanges());
            }
            addProductHistoryRsp.setNumberChanges(adminNumberHistoryBean.getNumberChanges());
            addProductHistoryRsp.setTime(adminNumberHistoryBean.getTime());
            String productName = productMapper.findProductNameByProductId(adminNumberHistoryBean.getProductId());
            addProductHistoryRsp.setProductName(productName);
            addProductHistoryRspList.add(addProductHistoryRsp);
        }
        return addProductHistoryRspList;
    }

    public void saveAdminAddComboHistory(Long adminId, String name){
        AdminAddComboHistoryBean adminAddComboHistoryBean = new AdminAddComboHistoryBean();
        adminAddComboHistoryBean.setAdminId(adminId);
        adminAddComboHistoryBean.setProductName(name);
        //生成当前时间
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd HH:mm:ss");
        String format = simpleDateFormat.format(timestamp);
        adminAddComboHistoryBean.setTime(format);
        adminAddComboHistoryMapper.insert(adminAddComboHistoryBean);
    }
    public void saveSalesVolume(String name,int number){
        QueryWrapper<ProductSalesVolumeBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_name",name);
        ProductSalesVolumeBean productSalesVolumeBean = productSalesVolumeMapper.selectOne(queryWrapper);
        if (productSalesVolumeBean == null) {
            ProductSalesVolumeBean productSalesVolumeBean1 = new ProductSalesVolumeBean();
            productSalesVolumeBean1.setProductName(name);
            productSalesVolumeBean1.setSalesVolume(number);
            productSalesVolumeMapper.insert(productSalesVolumeBean1);
        }else {
            productSalesVolumeMapper.saveSalesVolume(name,number);
        }
    }

    public void reviseSalesVolume(String name, int number){
        QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_name",name);
        ProductBean productBean = productMapper.selectOne(queryWrapper);
        if (productBean == null) {
            QueryWrapper<ComboBean> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("product_name",name);
            comboMapper.reviseComboNumber(name,number);
        }else {
            productMapper.reviseOutputNumberByName(name,number);
        }
    }

    public void deleteProduct(int productId){
        QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        productMapper.delete(queryWrapper);
    }
    public void deleteCombo(int comboId){
        QueryWrapper<ComboBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("combo_id",comboId);
        comboMapper.delete(queryWrapper);
    }
    public void saveDeleteProduct(Long adminId, int productId){
        AdminDeleteProductHistoryBean adminDeleteProductHistoryBean = new AdminDeleteProductHistoryBean();
        adminDeleteProductHistoryBean.setAdminId(adminId);

        String productName = productMapper.findProductNameByProductId(productId);
        adminDeleteProductHistoryBean.setProductName(productName);
        //生成当前时间
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd HH:mm:ss");
        String format = simpleDateFormat.format(timestamp);
        adminDeleteProductHistoryBean.setTime(format);
        adminDeleteProductHistoryMapper.insert(adminDeleteProductHistoryBean);
    }
    public void saveDeleteCombo(Long adminId, int comboId){
        AdminDeleteProductHistoryBean adminDeleteProductHistoryBean = new AdminDeleteProductHistoryBean();
        adminDeleteProductHistoryBean.setAdminId(adminId);

        String productName = comboMapper.findProductNameByComboId(comboId);
        adminDeleteProductHistoryBean.setProductName(productName);
        //生成当前时间
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd HH:mm:ss");
        String format = simpleDateFormat.format(timestamp);
        adminDeleteProductHistoryBean.setTime(format);
        adminDeleteProductHistoryMapper.insert(adminDeleteProductHistoryBean);
    }

    public List<String> getAllProductName(){
        List<String> list = new ArrayList<>();
        QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
        List<ProductBean> productBeanList = productMapper.selectList(queryWrapper);
        for (ProductBean productBean : productBeanList) {
            list.add(productBean.getProductName());
        }
        return list;
    }
    public List<String> getAllComboName(){
        List<String> list = new ArrayList<>();
        QueryWrapper<ComboBean> queryWrapper = new QueryWrapper<>();
        List<ComboBean> comboBeanList = comboMapper.selectList(queryWrapper);
        for (ComboBean comboBean : comboBeanList) {
            list.add(comboBean.getProductName());
        }
        return list;
    }


    public WeeklyCouponsBean setChangeWeeklyCouponInfo(String name, int price) {
        WeeklyCouponsBean weeklyCouponsBean = new WeeklyCouponsBean();
        QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_name",name);
        ProductBean productBean = productMapper.selectOne(queryWrapper);
        if (productBean == null){
            QueryWrapper<ComboBean> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("product_name", name);
            ComboBean comboBean = comboMapper.selectOne(queryWrapper1);

            double discount = price / comboBean.getPrice();
            // 创建一个DecimalFormat对象，用于格式化double值并保留两位小数
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedDiscount = decimalFormat.format(discount);
            // 将格式化后的字符串解析回double类型（如果需要）
            double roundedDiscount = Double.parseDouble(formattedDiscount);

            weeklyCouponsBean.setComboId(comboBean.getComboId());
            weeklyCouponsBean.setImageUrl(comboBean.getImgUrl());
            weeklyCouponsBean.setIsSingle(comboBean.getIsHot());
            weeklyCouponsBean.setDiscount(roundedDiscount);
            weeklyCouponsBean.setIsSingle(0);
        }else {
            double discount = price / productBean.getPrice();
            // 创建一个DecimalFormat对象，用于格式化double值并保留两位小数
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedDiscount = decimalFormat.format(discount);
            // 将格式化后的字符串解析回double类型（如果需要）
            double roundedDiscount = Double.parseDouble(formattedDiscount);

            weeklyCouponsBean.setProductId(productBean.getProductId());
            weeklyCouponsBean.setImageUrl(productBean.getImgUrl());
            weeklyCouponsBean.setIsSingle(productBean.getIsHot());
            weeklyCouponsBean.setDiscount(roundedDiscount);
            weeklyCouponsBean.setIsSingle(1);
        }
        return weeklyCouponsBean;
    }

    public void saveChangeWeeklyCoupon(WeeklyCouponsBean weeklyCouponsBean){
        UpdateWrapper<WeeklyCouponsBean> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("weekly_coupons_id", weeklyCouponsBean.getWeeklyCouponsId());
        // 执行更新操作
        weeklyCouponsMapper.update(weeklyCouponsBean, updateWrapper);
    }
}
