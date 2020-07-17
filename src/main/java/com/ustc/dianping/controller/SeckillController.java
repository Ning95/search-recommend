package com.ustc.dianping.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.ustc.dianping.common.BusinessException;
import com.ustc.dianping.common.CommonRes;
import com.ustc.dianping.common.EmBusinessError;
import com.ustc.dianping.mapper.OrderMapper;
import com.ustc.dianping.model.UserModel;
import com.ustc.dianping.service.SeckillService;
import com.ustc.dianping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

/**
 * @author Ning
 * 抢购优惠券
 */

@Controller
@RequestMapping("/coupon/seckill")
public class SeckillController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeckillController.class);

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private UserService userService;

    //每秒放行100个请求
    private RateLimiter rateLimiter = RateLimiter.create(100);

    @RequestMapping(path = "/order", method = RequestMethod.GET)
    @ResponseBody
    public CommonRes seckill(@RequestParam("goodsId") int goodsId, @RequestParam("userId") int userId) throws BusinessException {
        UserModel userModel = userService.getUser(userId);
        if (userModel == null) {
            return  CommonRes.create(EmBusinessError.COUPON_USER_NOT_LOGIN,"fail");
        }
        // 阻塞式获取令牌
        // LOGGER.info("等待时间" + rateLimiter.acquire());
        // 非阻塞式获取令牌
        if (!rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)) {
            LOGGER.warn("用户" + userId + "被限流了");
            return CommonRes.create(EmBusinessError.COUPON_IS_ERROR,"fail");
        }
        try {
            seckillService.createOptimisticOrder(userId, goodsId);
        } catch (BusinessException e) {
            throw new BusinessException(EmBusinessError.COUPON_IS_ERROR);
        }
        return CommonRes.create(userModel);
    }
}
