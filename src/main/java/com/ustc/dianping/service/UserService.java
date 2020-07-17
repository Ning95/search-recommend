package com.ustc.dianping.service;

import com.ustc.dianping.common.BusinessException;
import com.ustc.dianping.model.UserModel;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * 用户服务层接口
 */
public interface UserService {
    //根据用户id查询个人信息
    UserModel getUser(Integer id);

    //注册相关
    UserModel register(UserModel registerUser) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;

    //用户登录
    UserModel login(String telphone, String password, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;

    //所用商家的注册数量
    Integer countAllUser();
}
