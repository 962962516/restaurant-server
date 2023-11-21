package com.restaurant.project.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.project.Exception.UserExistException;
import com.restaurant.project.bean.*;
import com.restaurant.project.bean.request.UserRequest.ConsumeWeeklyCouponsReq;
import com.restaurant.project.bean.request.UserRequest.CouponsHistoryReq;
import com.restaurant.project.bean.request.UserRequest.ServiceRatingReq;
import com.restaurant.project.bean.response.UserRsponse.*;
import com.restaurant.project.mapper.*;
import com.restaurant.project.utils.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DecimalFormat;
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserOderMapper userOderMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private UserCouponsMapper userCouponsMapper;
    @Resource
    private UserCouponsComboMapper userCouponsComboMapper;
    @Resource
    private WeeklyCouponsMapper weeklyCouponsMapper;
    @Resource
    private UserCouponsHistoryMapper userCouponsHistoryMapper;
    @Resource
    private UserServiceRatingMapper userServiceRatingMapper;
    @Resource
    private UserWeeklyCouponsMapper userWeeklyCouponsMapper;
    @Resource
    private ComboMapper comboMapper;
    public UserBean findUserByUsernameAndPassword(String username, String password){
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("password",password);
        return userMapper.selectOne(queryWrapper);
    }

    public void saveUser(String username, String password) throws UserExistException, NoSuchAlgorithmException {
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        if (userMapper.selectCount(queryWrapper) > 0) {
            throw new UserExistException("用户已存在");
        }
        String hashedPassword = PasswordEncoder.encrypt(password);
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setIsVip(0);
        userMapper.insert(user);
    }
    public List<ProductAndComboRsp> findAllUserOderProductByAdiseListNo(int adiseListNo){
        List<ProductAndComboRsp> productAndComboRspList = new ArrayList<>();
        if (adiseListNo != 1) {
            QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("adise_list_no",adiseListNo);
            List<ProductBean> list = productMapper.selectList(queryWrapper);
            for (ProductBean productBean: list){
                ProductAndComboRsp productAndComboRsp = new ProductAndComboRsp();
                productAndComboRsp.setName(productBean.getProductName());
                productAndComboRsp.setDescription(productBean.getDescription());
                productAndComboRsp.setPrice(productBean.getPrice());
                productAndComboRsp.setImageUrl(productBean.getImgUrl());
                productAndComboRspList.add(productAndComboRsp);
            }
        }else {
            productAndComboRspList = findHotProductAndCombo();
        }
        return productAndComboRspList;
    }
    public List<ProductAndComboRsp> findHotProductAndCombo(){
        List<ProductAndComboRsp> list = new ArrayList<>();
        QueryWrapper<ComboBean> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("is_hot",1);
        List<ComboBean> comboBeanList = comboMapper.selectList(queryWrapper1);
        for (ComboBean comboBean : comboBeanList) {
            ProductAndComboRsp productAndComboRsp = new ProductAndComboRsp();
            productAndComboRsp.setName(comboBean.getProductName());
            productAndComboRsp.setPrice(comboBean.getPrice());
            productAndComboRsp.setImageUrl(comboBean.getImgUrl());
            productAndComboRsp.setDescription(comboBean.getDescription());
            list.add(productAndComboRsp);
        }

        QueryWrapper<ProductBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_hot",1);
        List<ProductBean> productBeanList = productMapper.selectList(queryWrapper);
        for (ProductBean productBean : productBeanList) {
            ProductAndComboRsp productAndComboRsp = new ProductAndComboRsp();
            productAndComboRsp.setName(productBean.getProductName());
            productAndComboRsp.setPrice(productBean.getPrice());
            productAndComboRsp.setImageUrl(productBean.getImgUrl());
            productAndComboRsp.setDescription(productBean.getDescription());
            list.add(productAndComboRsp);
        }
        return list;
    }
    public List<ProductAndComboRsp> findAllUserOderCombo(){
        QueryWrapper<ComboBean> queryWrapper = new QueryWrapper<>();
        List<ComboBean> comboBeans = comboMapper.selectList(queryWrapper);
        List<ProductAndComboRsp> productAndComboRspList = new ArrayList<>();
        for (ComboBean comboBean : comboBeans) {
            ProductAndComboRsp productAndComboRsp = new ProductAndComboRsp();
            productAndComboRsp.setName(comboBean.getProductName());
            productAndComboRsp.setDescription(comboBean.getDescription());
            productAndComboRsp.setPrice(comboBean.getPrice());
            productAndComboRsp.setImageUrl(comboBean.getImgUrl());
            productAndComboRspList.add(productAndComboRsp);
        }
        return productAndComboRspList;
    }

    public int updateUserOder(UserOderBean userOderBean){
        if(userOderBean != null){
            userOderMapper.insert(userOderBean);
            return 1;
        }else {
            return 0;
        }
    }

    public void reduceIntegral(Long userId,int integral){
        userMapper.reduceIntegral(integral,userId);
    }
    public int findPriceByProductId(int productId){
        int price= userMapper.findPriceByProductId(productId);
        return price;
    }
    public void saveUserCoupons(Long userId, int couponId){
        UserCouponsBean userCouponsBean = new UserCouponsBean();
        userCouponsBean.setUserId(userId);
        userCouponsBean.setCouponId(couponId);
        userCouponsMapper.insert(userCouponsBean);
    }
    public void saveUserCouponsCombo(Long userId, int comboId){
        UserCouponsComboBean userCouponsComboBean = new UserCouponsComboBean();
        userCouponsComboBean.setUserId(userId);
        userCouponsComboBean.setComboId(comboId);
        userCouponsComboMapper.insert(userCouponsComboBean);
    }
    public UserCouponsBean findUserCouponsIsHave(int couponId){
        QueryWrapper<UserCouponsBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("coupon_id",couponId);
        UserCouponsBean userCouponsBean = userCouponsMapper.selectOne(queryWrapper);
        return userCouponsBean;
    }
    public UserCouponsComboBean findUserCouponsComboIsHave(int comboId){
        QueryWrapper<UserCouponsComboBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("combo_id",comboId);
        UserCouponsComboBean userCouponsComboBean = userCouponsComboMapper.selectOne(queryWrapper);
        return userCouponsComboBean;
    }

    public WeeklyCouponsRsp weeklyCoupon(Long weeklyComboId){
        QueryWrapper<WeeklyCouponsBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("weekly_coupons_id",weeklyComboId);
        WeeklyCouponsBean weeklyCouponsBean = weeklyCouponsMapper.selectOne(queryWrapper);
        String name = null;
        int price = 0;
        String description = null;
        String imageUrl = null;
        double discount;
        WeeklyCouponsRsp weeklyCouponsRsp = new WeeklyCouponsRsp();
        if (weeklyCouponsBean.getIsSingle() == 0) {
            weeklyCouponsRsp.setIsSingle(0);
            int comboId = weeklyCouponsBean.getComboId();
            weeklyCouponsRsp.setComboId(comboId);
            name = userMapper.findComboNameByComboId(comboId);
            price = userMapper.findPriceByComboId(comboId);
            description = userMapper.findComboDescriptionByComboId(comboId);
            discount = weeklyCouponsMapper.findDiscountByWeeklyCouponsId(weeklyComboId);
            imageUrl = comboMapper.getImageUrlByComboName(name);
        }else {
            weeklyCouponsRsp.setIsSingle(1);
            int productId = weeklyCouponsBean.getProductId();
            weeklyCouponsRsp.setProductId(productId);
            name = userMapper.findProductNameByProductId(productId);
            price = userMapper.findPriceByProductId(productId);
            description = userMapper.findDescriptionByProductId(productId);
            discount = weeklyCouponsMapper.findDiscountByWeeklyCouponsId(weeklyComboId);
            imageUrl = productMapper.getImageUrlByProductName(name);
        }
        weeklyCouponsRsp.setWeeklyCouponsId(weeklyComboId);
        weeklyCouponsRsp.setWeeklyCouponsName(name);
        weeklyCouponsRsp.setPrice(price);
        weeklyCouponsRsp.setDescription(description);
        weeklyCouponsRsp.setIntegral(weeklyCouponsBean.getIntegral());
        weeklyCouponsRsp.setDiscount(discount);
        weeklyCouponsRsp.setImageUrl(imageUrl);
        return weeklyCouponsRsp;
    }


    public List<AllCouponsRsp> getUserAllCoupons(Long userId){
        List<AllCouponsRsp> list = new ArrayList<>();
        QueryWrapper<UserCouponsBean> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id",userId);
        List<UserCouponsBean> userCouponsBeans = userCouponsMapper.selectList(queryWrapper1);

        QueryWrapper<UserCouponsComboBean> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_id",userId);
        List<UserCouponsComboBean> userCouponsComboBeans = userCouponsComboMapper.selectList(queryWrapper2);

        //处理单个商品兑换卷
        for(UserCouponsBean userCouponsBean : userCouponsBeans){
            AllCouponsRsp allCouponsRsp = new AllCouponsRsp();

            int productId = userMapper.findProductIdByCouponId(userCouponsBean.getCouponId());
            String productName = userMapper.findProductNameByProductId(productId);
            String description = userMapper.findDescriptionByProductId(productId);
            double productDiscount = userMapper.findProductDiscountByProductId(productId);
            int price = userMapper.findPriceByProductId(productId);
            String imageUrl = productMapper.getImageUrlByProductId(productId);

            allCouponsRsp.setName(productName);
            allCouponsRsp.setProductId(productId);
            allCouponsRsp.setDescription(description);
            allCouponsRsp.setIsSingle(1);

            // 创建 DecimalFormat 对象，用于格式化数字
            DecimalFormat df = new DecimalFormat("#0.0");
            // 使用 DecimalFormat 格式化数字并将其转为字符串
            String formattedPrice = df.format(price * productDiscount);
            double priceAfterDiscount = Double.parseDouble(formattedPrice);

            allCouponsRsp.setPriceAfterDiscount(priceAfterDiscount);
            allCouponsRsp.setImageUrl(imageUrl);
            list.add(allCouponsRsp);
        }

        //处理套餐兑换卷
        for(UserCouponsComboBean userCouponsComboBean : userCouponsComboBeans){
            AllCouponsRsp allCouponsRsp = new AllCouponsRsp();

            String comboName = userMapper.findComboNameByComboId(userCouponsComboBean.getComboId());
            String comboDescription = userMapper.findComboDescriptionByComboId(userCouponsComboBean.getComboId());
            int price = userMapper.findPriceByComboId(userCouponsComboBean.getComboId());
            double comboDiscount = userMapper.findComboDiscountByComboId(userCouponsComboBean.getComboId());
            String imageUrl = comboMapper.getImageUrlByComboId(userCouponsComboBean.getComboId());

            allCouponsRsp.setName(comboName);
            allCouponsRsp.setComboId(userCouponsComboBean.getComboId());
            allCouponsRsp.setDescription(comboDescription);
            allCouponsRsp.setIsSingle(0);

            // 创建 DecimalFormat 对象，用于格式化数字
            DecimalFormat df = new DecimalFormat("#0.0");
            // 使用 DecimalFormat 格式化数字并将其转为字符串
            String formattedPrice = df.format(price * comboDiscount);
            double priceAfterDiscount = Double.parseDouble(formattedPrice);

            allCouponsRsp.setPriceAfterDiscount(priceAfterDiscount);
            allCouponsRsp.setImageUrl(imageUrl);
            list.add(allCouponsRsp);
        }

        return list;
    }

    public void saveUserCouponsHistory(CouponsHistoryReq couponsHistoryReq,Long userId){
        UserCouponsHistoryBean userCouponsHistoryBean = new UserCouponsHistoryBean();
        userCouponsHistoryBean.setUserId(userId);
        userCouponsHistoryBean.setComboId(couponsHistoryReq.getComboId());
        userCouponsHistoryBean.setProductId(couponsHistoryReq.getProductId());
        userCouponsHistoryBean.setIsSingle(couponsHistoryReq.getIsSingle());
        userCouponsHistoryBean.setOderNumber(couponsHistoryReq.getOderNumber());
        //生成当前时间
        Date currentDate = new Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddd HH:mm:ss");
        String format = simpleDateFormat.format(timestamp);
        userCouponsHistoryBean.setTime(format);
        userCouponsHistoryMapper.insert(userCouponsHistoryBean);
    }

    public void deleteUserCoupon(Long userId ,int productId){
        QueryWrapper<UserCouponsBean> queryWrapper = new QueryWrapper<>();
        int couponId = userMapper.findCouponIdByProductId(productId);
        queryWrapper.eq("user_id",userId).eq("coupon_id",couponId);
        userCouponsMapper.delete(queryWrapper);
    }
    public void deleteWeeklyCoupon(Long userId, int productId){
        QueryWrapper<UserWeeklyCouponsBean> queryWrapper = new QueryWrapper<>();
        int weeklyCouponsId = weeklyCouponsMapper.findWeeklyCouponsIdByProductId(productId);
        queryWrapper.eq("user_id",userId).eq("weekly_coupons_id",weeklyCouponsId);
        userWeeklyCouponsMapper.delete(queryWrapper);
    }
    public void deleteWeeklyCouponCombo(Long userId, int comboId){
        QueryWrapper<UserWeeklyCouponsBean> queryWrapper = new QueryWrapper<>();
        int weeklyCouponsId = weeklyCouponsMapper.findWeeklyCouponsIdByComboId(comboId);
        queryWrapper.eq("user_id",userId).eq("weekly_coupons_id",weeklyCouponsId);
        userWeeklyCouponsMapper.delete(queryWrapper);
    }

    public void deleteUserCouponCombo(Long userId, int comboId){
        QueryWrapper<UserCouponsComboBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("combo_id",comboId);
        userCouponsComboMapper.delete(queryWrapper);
    }

    public List<AllUserConsumeRsp> getAllUserOderHistory(Long userId){
        QueryWrapper<UserOderBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<AllUserConsumeRsp> list = new ArrayList<>();
        List<UserOderBean> userOderBeans = userOderMapper.selectList(queryWrapper);
        for(UserOderBean userOderBean: userOderBeans){
            AllUserConsumeRsp allUserConsumeRsp = new AllUserConsumeRsp();
            String productName = userOderBean.getName();
            allUserConsumeRsp.setProductName(productName);
            allUserConsumeRsp.setOderTime(userOderBean.getOderTime());
            allUserConsumeRsp.setProductNumber(userOderBean.getNumber());
            allUserConsumeRsp.setOderNumber((long) userOderBean.getOderNumber());
            list.add(allUserConsumeRsp);
        }
        return list;
    }
    public List<AllUserComboConsumeRsp> getUserCouponsHistory(Long userId){
        QueryWrapper<UserCouponsHistoryBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<UserCouponsHistoryBean> userCouponsHistoryBeans = userCouponsHistoryMapper.selectList(queryWrapper);

        List<AllUserComboConsumeRsp> list = new ArrayList<>();
        for(UserCouponsHistoryBean userCouponsHistoryBean: userCouponsHistoryBeans){
            AllUserComboConsumeRsp allUserComboConsumeRsp = new AllUserComboConsumeRsp();
            if (userCouponsHistoryBean.getIsSingle() == 0) {
                String comboName = userMapper.findComboNameByComboId(userCouponsHistoryBean.getComboId());
                allUserComboConsumeRsp.setName(comboName);
            }else {
                String productName = userMapper.findProductNameByProductId(userCouponsHistoryBean.getProductId());
                allUserComboConsumeRsp.setName(productName);
            }
            allUserComboConsumeRsp.setOderNumber(userCouponsHistoryBean.getOderNumber());
            allUserComboConsumeRsp.setOderTime(userCouponsHistoryBean.getTime());
            list.add(allUserComboConsumeRsp);
        }
        return list;
    }

    public void saveServiceRating(ServiceRatingReq serviceRatingReq,Long userId){
        UserServiceRatingBean userServiceRatingBean = new UserServiceRatingBean();
        userServiceRatingBean.setUserId(userId);
        userServiceRatingBean.setRaClass(serviceRatingReq.getRaClass());
        userServiceRatingBean.setRating(serviceRatingReq.getRating());
        userServiceRatingBean.setReaction(serviceRatingReq.getReaction());
        userServiceRatingMapper.insert(userServiceRatingBean);
    }

    public void saveWeeklyCoupons(Long weeklyCouponsId, Long userId){
        UserWeeklyCouponsBean userWeeklyCouponsBean = new UserWeeklyCouponsBean();
        userWeeklyCouponsBean.setWeeklyCouponsId(weeklyCouponsId);
        userWeeklyCouponsBean.setUserId(userId);
        userWeeklyCouponsMapper.insert(userWeeklyCouponsBean);
    }

    public List<UserWeeklyCouponsBean> getUserWeeklyCoupons(Long userId){
        QueryWrapper<UserWeeklyCouponsBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<UserWeeklyCouponsBean> userWeeklyCouponsBeans = userWeeklyCouponsMapper.selectList(queryWrapper);
        return userWeeklyCouponsBeans;
    }

    public List<WeeklyCouponsBean> getWeeklyCoupons(List<UserWeeklyCouponsBean> userWeeklyCouponsBeanList){
        List<WeeklyCouponsBean> list = new ArrayList<>();
        for (UserWeeklyCouponsBean userWeeklyCouponsBean : userWeeklyCouponsBeanList) {
            QueryWrapper<WeeklyCouponsBean> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("weekly_coupons_id",userWeeklyCouponsBean.getWeeklyCouponsId());
            WeeklyCouponsBean weeklyCouponsBean = weeklyCouponsMapper.selectOne(queryWrapper);
            list.add(weeklyCouponsBean);
        }
        return list;
    }

    public List<AllCouponsRsp> getAllWeeklyCoupons(List<WeeklyCouponsBean> weeklyCouponsBeanList){
        List<AllCouponsRsp> list = new ArrayList<>();
        String productName = null;
        int price;
        for (WeeklyCouponsBean weeklyCouponsBean : weeklyCouponsBeanList) {
            AllCouponsRsp allCouponsRsp = new AllCouponsRsp();
            allCouponsRsp.setComboId(weeklyCouponsBean.getComboId());
            allCouponsRsp.setImageUrl(weeklyCouponsBean.getImageUrl());
            allCouponsRsp.setIsSingle(weeklyCouponsBean.getIsSingle());
            allCouponsRsp.setProductId(weeklyCouponsBean.getProductId());
            if (weeklyCouponsBean.getIsSingle() == 1) {
                productName = productMapper.findProductNameByProductId(weeklyCouponsBean.getProductId());
                price = productMapper.getPriceByProductId(weeklyCouponsBean.getProductId());
            }else {
                productName = comboMapper.findProductNameByComboId(weeklyCouponsBean.getComboId());
                price = comboMapper.getPriceByComboName(productName);
            }
            allCouponsRsp.setName(productName);

            // 创建 DecimalFormat 对象，用于格式化数字
            DecimalFormat df = new DecimalFormat("#0.0");
            // 使用 DecimalFormat 格式化数字并将其转为字符串
            String formattedPrice = df.format(price * weeklyCouponsBean.getDiscount());
            double priceAfterDiscount = Double.parseDouble(formattedPrice);

            allCouponsRsp.setPriceAfterDiscount(priceAfterDiscount);
            list.add(allCouponsRsp);
        }
        return list;
    }
}
