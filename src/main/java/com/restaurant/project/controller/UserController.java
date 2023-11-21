package com.restaurant.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.project.Exception.UserExistException;
import com.restaurant.project.bean.*;
import com.restaurant.project.bean.request.UserRequest.*;
import com.restaurant.project.bean.response.UserRsponse.*;
import com.restaurant.project.mapper.UserMapper;
import com.restaurant.project.mapper.UserOderMapper;
import com.restaurant.project.mapper.WeeklyCouponsMapper;
import com.restaurant.project.service.AdminService;
import com.restaurant.project.service.EnterpriseService;
import com.restaurant.project.service.UserService;
import com.restaurant.project.utils.ErrorCode;
import com.restaurant.project.utils.JwtTokenUtil;
import com.restaurant.project.utils.PasswordEncoder;
import com.restaurant.project.utils.Response;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import io.jsonwebtoken.Claims;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserOderMapper userOderMapper;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private WeeklyCouponsMapper weeklyCouponsMapper;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginReq loginReq) {
        try {
            String hashedPassword = PasswordEncoder.encrypt(loginReq.getPassword().trim());
            UserBean userBean = userService.findUserByUsernameAndPassword(loginReq.getUsername(), hashedPassword);
            if (userBean != null) {
                String token = jwtTokenUtil.generateToken(userBean.getUserId(), userBean.getUsername(), 1800L);
                return ResponseEntity.ok(Response.success(new LoginRsp(token, userBean)));
            } else {
                return ResponseEntity.ok(Response.error(ErrorCode.INVALID_EMAIL_OR_PASSWORD));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(Response.error(ErrorCode.ERROR_SERVICE));
    }

    @ApiOperation("注册接口")
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterReq registerReq) {
        try {
            userService.saveUser(registerReq.getUsername(), registerReq.getPassword());
        } catch (UserExistException e) {
            return ResponseEntity.ok(Response.error(ErrorCode.USER_HAS_EXIST));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(Response.success());
    }

    @ApiOperation("验证用户token是否过期")
    @GetMapping("/user/verifyUserToken")
    public ResponseEntity<Object> verifyToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization").trim();
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        // 从 claims 中获取 "username"、"userid" 和 "exp" 的值
        String username = claims.get("username", String.class);
        Date expiration = claims.getExpiration();
        int userInfo = userMapper.getUserInfo(username);
        if (new Date().before(expiration)) {
            return ResponseEntity.ok(Response.success(new VerifyUserRsp(false, userInfo)));
        } else {
            return ResponseEntity.ok(Response.error(ErrorCode.USER_TOKEN_ISTIMEOUT));
        }
    }

    @ApiOperation("获取用户名信息")
    @GetMapping("/user/getUsername")
    public ResponseEntity<Object> getUsername(@RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        String username = userMapper.getUsername(userId);
        if (username != null) {
            return ResponseEntity.ok(Response.success(username));
        }else {
            return ResponseEntity.ok(Response.error(ErrorCode.SELECT_USERNAME_ERROR));
        }
    }

    @ApiOperation("查询用户是否为会员用户")
    @GetMapping("/user/isVip")
    public ResponseEntity<Object> getUserIsVip(@RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        int userIsVip = userMapper.getUserIsVip(userId);
        return ResponseEntity.ok(Response.success(userIsVip));
    }

    @ApiOperation("录入用户订单详情")
    @PostMapping("/user/payForOder")
    public ResponseEntity<Object> payForOder(@RequestBody PayForOderReq payForOderReq,
                                             @RequestHeader("Authorization") String token) {
        int priceAndIntegral = 0;
        int count = 0;
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        //拿取订单号
        int oderNumber = payForOderReq.getOderNumber();
        List<AdminOderBean> adminOderBeanList = new ArrayList<>();
        //拿取前端发送过来的Oder
        for (Map.Entry<String, Integer> entry : payForOderReq.getProductCounts().getValue().entrySet()) {
            String name = entry.getKey();
            Integer number = entry.getValue();

            //数据传入bean中
            UserOderBean userOderBean = new UserOderBean();
            userOderBean.setName(name);
            userOderBean.setUserId(userId);
            userOderBean.setOderNumber(oderNumber);
            userOderBean.setNumber(number);
            //生成当前时间
            Date currentDate = new Date();
            Timestamp timestamp = new Timestamp(currentDate.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateFormat.format(timestamp);
            userOderBean.setOderTime(formattedDateTime);
            //插入数据库
            count += userService.updateUserOder(userOderBean);

            //积分和价格统计
            int price = userOderMapper.findProductPriceByName(name);
            priceAndIntegral += price * number;

            //数据封装到rsp
            AdminOderBean adminOderBean = new AdminOderBean();
            adminOderBean.setName(name);
            adminOderBean.setNumber(number);
            adminOderBean.setOderNumber(oderNumber);
            adminOderBean.setOderTime(formattedDateTime);
            adminOderBean.setPrice(priceAndIntegral);
            //记录数据
            adminService.saveAdminOder(adminOderBean);
            adminOderBeanList.add(adminOderBean);
            //餐品销量保存
            adminService.saveSalesVolume(name,number);
            //变动餐品库存
            adminService.reviseSalesVolume(name,number);

            // 将 Timestamp 格式化为指定格式的字符串
            String formattedDate = dateFormat.format(timestamp);

            // 从格式化的字符串中提取月份
            String[] parts = formattedDate.split("-");
            if (parts.length >= 2) {
                int monthNumber = Integer.parseInt(parts[1]); // 月份部分
                enterpriseService.saveMonthSales(monthNumber,price);
            }

        }
        if (count != 0) {
            return ResponseEntity.ok(Response.success(adminOderBeanList));
        } else {
            return ResponseEntity.ok(Response.error(ErrorCode.ODER_INSERT_ERROR));
        }
    }

    @ApiOperation("用户开通会员")
    @PostMapping("/user/vip")
    public ResponseEntity<Object> RegisteredMember(@RequestHeader("Authorization") String token) {
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        int i = userMapper.setUserVip(userId);
        if (i == 1) {
            return ResponseEntity.ok(Response.success());
        } else {
            return ResponseEntity.ok(Response.error(ErrorCode.USER_SET_VIP_ERROR));
        }
    }

    @ApiOperation("用户积分添加")
    @PostMapping("/user/addIntegral")
    public ResponseEntity<Object> addIntegral(@RequestParam("integral") int integral,
                                              @RequestHeader("Authorization") String token) {
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        int i = userMapper.addUserIntegral(integral, userId);
        if (i == 1) {
            return ResponseEntity.ok(Response.success());
        }
        return ResponseEntity.ok(Response.error(ErrorCode.USER_ADD_INTEGRAL_ERROR));
    }

    @ApiOperation("查询积分")
    @GetMapping("/user/integral")
    public ResponseEntity<Object> integral(@RequestHeader("Authorization") String token) {
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        int integral = userMapper.findIntegralByUserId(userId);
        return ResponseEntity.ok(Response.success(integral));
    }

    @ApiOperation("消费积分,并保存将用户兑换的优惠卷保存")
    @PostMapping("/user/consumeIntegral")
    public ResponseEntity<Object> consumeIntegral(@RequestBody ConsumeIntegralReq consumeIntegralReq,
                                                  @RequestHeader("Authorization") String token) {
        int price;
        double discount;
        ConsumeIntegralRsp consumeIntegralRsp;
        int couponId = userMapper.findCouponIdByProductId(consumeIntegralReq.getProductId());
        UserCouponsBean userCouponsIsHave = userService.findUserCouponsIsHave(couponId);
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));

        //判断用户的积分是否大于优惠卷积分 并且 不能重复兑换
        int userIntegral = userMapper.findIntegralByUserId(userId);
        if (userIntegral >= consumeIntegralReq.getIntegral() && userCouponsIsHave == null) {
            userService.reduceIntegral(userId, consumeIntegralReq.getIntegral());
            price = userService.findPriceByProductId(consumeIntegralReq.getProductId());
            discount = userMapper.findProductDiscountByProductId(consumeIntegralReq.getProductId());
            consumeIntegralRsp = new ConsumeIntegralRsp(price, discount, couponId);
            //保存用户所选购的优惠卷
            userService.saveUserCoupons(userId, couponId);
            return ResponseEntity.ok(Response.success(consumeIntegralRsp));
        } else if(userIntegral < consumeIntegralReq.getIntegral()){
            return ResponseEntity.ok(Response.error(ErrorCode.USER_INTEGRAL_NOT_ENOUGH));
        } else {
            return ResponseEntity.ok(Response.error(ErrorCode.USER_HAS_THIS_COUPON));
        }
    }

    @ApiOperation("消费积分,并保存将用户兑换的组合优惠卷保存")
    @PostMapping("/user/consumeComboIntegral")
    public ResponseEntity<Object> consumeComboIntegral(@RequestBody ConsumeComboIntegralReq consumeComboIntegralReq,
                                                       @RequestHeader("Authorization") String token) {
        int countOfComboProducts = userMapper.getCountOfComboProducts(consumeComboIntegralReq.getComboId());
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));

        ConsumeComboIntegralRsp consumeComboIntegralRsp;
        int userIntegral = userMapper.findIntegralByUserId(userId);
        UserCouponsComboBean userCouponsComboIsHave = userService.findUserCouponsComboIsHave(consumeComboIntegralReq.getComboId());
        //判断用户的积分是否大于优惠卷积分
        if (userIntegral >= consumeComboIntegralReq.getIntegral() && userCouponsComboIsHave == null) {
            userService.reduceIntegral(userId, consumeComboIntegralReq.getIntegral());
            //保存到用户套餐组合优惠卷数据库中
            userService.saveUserCouponsCombo(userId, consumeComboIntegralReq.getComboId());
            int price = userMapper.findPriceByComboId(consumeComboIntegralReq.getComboId());
            double comboDiscount = userMapper.findComboDiscountByComboId(consumeComboIntegralReq.getComboId());
            int comboIntegral = userMapper.findComboIntegralFromComboId(consumeComboIntegralReq.getComboId());
            consumeComboIntegralRsp = new ConsumeComboIntegralRsp(consumeComboIntegralReq.getComboId(),
                    comboIntegral,price,comboDiscount);
            return ResponseEntity.ok(Response.success(consumeComboIntegralRsp));
        } else if (userIntegral < consumeComboIntegralReq.getIntegral()) {
            return ResponseEntity.ok(Response.error(ErrorCode.USER_INTEGRAL_NOT_ENOUGH));
        }else {
            return ResponseEntity.ok(Response.error(ErrorCode.USER_HAS_THIS_COUPON));
        }
    }


    @ApiOperation("查询当前用户所有消费记录")
    @GetMapping("/user/allUserConsume")
    public ResponseEntity<Object> allUserConsume(@RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        List<AllUserConsumeRsp> allUserOderHistory = userService.getAllUserOderHistory(userId);
        return ResponseEntity.ok(Response.success(allUserOderHistory));
    }
    @ApiOperation("查询当前用户所有卡卷消费记录")
    @GetMapping("/user/allUserComboConsume")
    public ResponseEntity<Object> allUserComboConsume(@RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        List<AllUserComboConsumeRsp> userCouponsHistory = userService.getUserCouponsHistory(userId);
        return ResponseEntity.ok(Response.success(userCouponsHistory));
    }


    @ApiOperation("查询用户的所有兑换卷")
    @GetMapping("/user/allCoupons")
    public ResponseEntity<Object> allCoupons(@RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        List<AllCouponsRsp> userAllCoupons = userService.getUserAllCoupons(userId);
        return ResponseEntity.ok(Response.success(userAllCoupons));
    }
    @ApiOperation("用户消费卡卷，保存到卡卷记录表")
    @PostMapping("/user/couponsHistory")
    public ResponseEntity<Object> couponsHistory(@RequestBody CouponsHistoryReq couponsHistoryReq,
                                                 @RequestHeader("Authorization") String token ){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        //保存卡卷消费记录
        userService.saveUserCouponsHistory(couponsHistoryReq,userId);

        //清除用户卡卷
        if (couponsHistoryReq.getIsSingle() == 0) {
            if (couponsHistoryReq.getIsWeeklyCoupons() == 0) {
                userService.deleteUserCouponCombo(userId,couponsHistoryReq.getComboId());
            }else {
                userService.deleteWeeklyCouponCombo(userId,couponsHistoryReq.getComboId());
            }
        }else {
            if (couponsHistoryReq.getIsWeeklyCoupons() == 0) {
                userService.deleteUserCoupon(userId,couponsHistoryReq.getProductId());
            }else {
                userService.deleteWeeklyCoupon(userId,couponsHistoryReq.getProductId());
            }
        }
        return ResponseEntity.ok(Response.success());
    }
    @ApiOperation("用户兑换每周卡卷，并保存")
    @PostMapping("/user/consumeWeeklyCoupons")
    public ResponseEntity<Object> consumeWeeklyCoupons(@RequestParam("weeklyCouponsId") Long weeklyCouponsId,
                                                       @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        int integral = userMapper.findIntegralByUserId(userId);
        int weeklyCouponsIntegral = weeklyCouponsMapper.findIntegralByWeeklyCouponsId(weeklyCouponsId);
        if (integral < weeklyCouponsIntegral) {
            return ResponseEntity.ok(Response.error(ErrorCode.USER_INTEGRAL_NOT_ENOUGH));
        }
        userService.saveWeeklyCoupons(weeklyCouponsId,userId);
        userService.reduceIntegral(userId, weeklyCouponsIntegral);
        return ResponseEntity.ok(Response.success());
    }

    @ApiOperation("用户投诉与建议")
    @PostMapping("/user/serviceRating")
    public ResponseEntity<Object> serviceRating(@RequestBody ServiceRatingReq serviceRatingReq,
                                                @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        userService.saveServiceRating(serviceRatingReq,userId);
        return ResponseEntity.ok(Response.success());
    }

    @ApiOperation("用户个人信息补充")
    @PostMapping("/user/personalInfo")
    public ResponseEntity<Object> personalInfo(@RequestBody PersonalInfoReq personalInfoReq,
                                               @RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        userMapper.saveUserPersonalInfo(personalInfoReq.getEmail(), personalInfoReq.getPhone(),userId);
        return ResponseEntity.ok(Response.success());
    }

    @ApiOperation("获取用户每周优惠卷")
    @GetMapping("/user/getWeeklyCoupons")
    public ResponseEntity<Object> getWeeklyCoupons(@RequestHeader("Authorization") String token){
        //token解析出userId
        Claims claims = JwtTokenUtil.getClaimsFromToken(token);
        Long userId = Long.parseLong((String) claims.get("userId"));
        List<UserWeeklyCouponsBean> userWeeklyCoupons = userService.getUserWeeklyCoupons(userId);
        List<WeeklyCouponsBean> weeklyCoupons = userService.getWeeklyCoupons(userWeeklyCoupons);
        List<AllCouponsRsp> allWeeklyCoupons = userService.getAllWeeklyCoupons(weeklyCoupons);
        return ResponseEntity.ok(Response.success(allWeeklyCoupons));
    }
}

