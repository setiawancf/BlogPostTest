package com.sigma.BlogPostTest.controller;

import com.sigma.BlogPostTest.common.Response;
import com.sigma.BlogPostTest.constant.Urls;
import com.sigma.BlogPostTest.response.ResponseUtil;
import com.sigma.BlogPostTest.security.JwtToken;
import com.sigma.BlogPostTest.service.UserService;
import com.sigma.BlogPostTest.vo.LoginVo;
import com.sigma.BlogPostTest.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Setiawan Candrafu
 * @date 3/18/2023
 */
@Slf4j
@RestController
@RequestMapping(value = Urls.Login.MODULE)
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtToken jwtToken;

    @PostMapping(Urls.Login.V1_USER_LOGIN_GET_TOKEN)
    public Response<Map<String, Object>> login(@RequestBody final LoginVo loginVo) {
        Map<String, Object> result = new HashMap<>();
        try {
            UserInfoVo userInfo = userService.getUserInfo(loginVo);
            result.put("userInfo", userInfo);
            result.put("token", jwtToken.generateToken(userInfo));
            return ResponseUtil.getSuccessResponse(result);
        } catch (Exception e) {
            log.error(e.getMessage());
            result.put("message",e.getMessage());
            return ResponseUtil.getErrorMessageResponse(e.getMessage());
        }
    }
}
