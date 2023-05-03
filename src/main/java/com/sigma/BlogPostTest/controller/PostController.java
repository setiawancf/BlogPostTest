package com.sigma.BlogPostTest.controller;

import com.sigma.BlogPostTest.common.Response;
import com.sigma.BlogPostTest.constant.Urls;
import com.sigma.BlogPostTest.enums.ResponsCodeTypeEnum;
import com.sigma.BlogPostTest.response.ResponseUtil;
import com.sigma.BlogPostTest.security.JwtToken;
import com.sigma.BlogPostTest.service.PostService;
import com.sigma.BlogPostTest.vo.PostVo;
import com.sigma.BlogPostTest.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Setiawan Candrafu
 * @date 5/2/2023
 */
@Slf4j
@RestController
@RequestMapping(value = Urls.Post.MODULE)
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    JwtToken jwtToken;

    @PostMapping(Urls.Post.V1_CREATE_POST)
    public Response createPost(@RequestHeader String authorization, @RequestBody PostVo postVo) {
        UserInfoVo userInfo = jwtToken.validate(authorization);
        if (userInfo == null) {
            return ResponseUtil.getResponse(ResponsCodeTypeEnum.UNAUTHORIZED.getCode());
        }
        return postService.createPost(userInfo, postVo);
    }

    @PostMapping(Urls.Post.V1_UPDATE_POST)
    public Response updatePost(@RequestHeader String authorization, @RequestBody PostVo postVo) {
        if (jwtToken.validate(authorization) == null) {
            return ResponseUtil.getResponse(ResponsCodeTypeEnum.UNAUTHORIZED.getCode());
        }
        return postService.updatePost(postVo);
    }

    @GetMapping(Urls.Post.V1_DELETE_POST)
    public Response deletePost(@RequestHeader String authorization, Long id) {
        if (jwtToken.validate(authorization) == null) {
            return ResponseUtil.getResponse(ResponsCodeTypeEnum.UNAUTHORIZED.getCode());
        }
        return postService.deletePost(id);
    }

    @GetMapping(Urls.Post.V1_ALL_POSTS)
    public Response findAll(@RequestHeader String authorization, Pageable pageable) {
        if (jwtToken.validate(authorization) == null) {
            return ResponseUtil.getResponse(ResponsCodeTypeEnum.UNAUTHORIZED.getCode());
        }
        return postService.findAllPost(pageable);
    }

    @GetMapping(Urls.Post.V1_POST_BY_ID)
    public Response findById(@RequestHeader String authorization, Long id) {
        if (jwtToken.validate(authorization) == null) {
            return ResponseUtil.getResponse(ResponsCodeTypeEnum.UNAUTHORIZED.getCode());
        }
        return postService.findById(id);
    }
}
