package com.ustc.dianping.service.impl;

import com.ustc.dianping.common.BusinessException;
import com.ustc.dianping.common.EmBusinessError;
import com.ustc.dianping.mapper.UserModelMapper;
import com.ustc.dianping.model.UserModel;
import com.ustc.dianping.service.UserService;
import com.ustc.dianping.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {
    public static final String CURRENT_USER_SESSION = "currentUser_session";
    public static final String COOKIE_NAME_TOKEN = "login_token";
    private static final int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public UserModel register(UserModel registerUser) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        registerUser.setPassword(encodeByMd5(registerUser.getPassword()));
        registerUser.setCreatedAt(new Date());
        registerUser.setUpdatedAt(new Date());

        // 手机号在数据库中被设置为唯一索引
        try {
            userModelMapper.insertSelective(registerUser);
        } catch (DuplicateKeyException ex) {
            throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
        }
        return getUser(registerUser.getId());
    }

    @Override
    public UserModel login(String telphone, String password, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        UserModel userModel = userModelMapper.selectByTelphoneAndPassword(telphone, encodeByMd5(password));
        if (userModel == null) {
            throw new BusinessException(EmBusinessError.LOGIN_FAIL);
        }
        //生成Cookie
        //String uuid = UUIDUtil.getUUID();
        //addCookie(response, uuid);
        return userModel;
    }

    @Override
    public Integer countAllUser() {
        return userModelMapper.countAllUser();
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchAlgorithmException {
        // 使用md5算法对用户密码进行加密, 在数据库中以密文的方式存储
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("UTF-8")));
    }

    private void addCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(DEFAULT_EXPIRED_SECONDS);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
