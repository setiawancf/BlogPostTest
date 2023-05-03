package com.sigma.BlogPostTest.service;

import com.sigma.BlogPostTest.model.Users;
import com.sigma.BlogPostTest.repository.UsersRepository;
import com.sigma.BlogPostTest.security.JwtToken;
import com.sigma.BlogPostTest.vo.LoginVo;
import com.sigma.BlogPostTest.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    JwtToken jwtToken;

    public UserInfoVo getUserInfo(LoginVo loginVo) throws Exception {
        //validate user
        Users user = usersRepository.findUser(loginVo.getName(), loginVo.getPassword());
        if (user == null) {
            log.error("User Not Found");
            throw new Exception("User Not Found");
        }
        UserInfoVo userInfo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfo);

        return userInfo;
    }

}
