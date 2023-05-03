package com.sigma.BlogPostTest.service;

import com.sigma.BlogPostTest.common.Response;
import com.sigma.BlogPostTest.enums.ResponsCodeTypeEnum;
import com.sigma.BlogPostTest.model.Posts;
import com.sigma.BlogPostTest.repository.PostsRepository;
import com.sigma.BlogPostTest.response.ResponseUtil;
import com.sigma.BlogPostTest.vo.PostVo;
import com.sigma.BlogPostTest.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

/**
 * @author Setiawan Candrafu
 * @date 5/2/2023
 */
@Slf4j
@Service
public class PostService {

    @Autowired
    private PostsRepository postsRepository;

    @Transactional
    public Response createPost(UserInfoVo userInfo, PostVo postVo) {
        try {
            Map<String, Object> result = new HashMap<>();

            Posts posts = new Posts();
            BeanUtils.copyProperties(postVo, posts);
            posts.setAuthor(userInfo.getName());

            log.info("Creating post.");
            Posts post = postsRepository.save(posts);

            log.info("Post created, id : {}.", post.getId());
            result.put("message", "Post has been created.");
            result.put("data", post);
            return ResponseUtil.getSuccessResponse(result, ResponsCodeTypeEnum.CREATED.getCode());
        } catch (Exception e) {
            log.error("Failed to create post.", e);
            return ResponseUtil.getErrorMessageResponse(e.getMessage());
        }
    }

    @Transactional
    public Response updatePost(PostVo postVo) {
        try {
            Map<String, Object> result = new HashMap<>();

            log.info("Finding post : {}.", postVo.getId());
            Optional<Posts> posts = postsRepository.findById(postVo.getId());
            if (!posts.isPresent()) {
                log.info("Post not found id : {}.", postVo.getId());
                return ResponseUtil.getErrorMessageResponse(ResponsCodeTypeEnum.PARAMETER_ERROR.getCode(), "Post not found id : " + postVo.getId());
            }

            Posts updatedPost = posts.get();
            BeanUtils.copyProperties(postVo, updatedPost);

            log.info("update post.");
            postsRepository.save(updatedPost);

            log.info("Post updated, id : {}.", updatedPost.getId());
            result.put("message", "Post has been updated.");
            result.put("data", updatedPost);
            return ResponseUtil.getSuccessResponse(result, ResponsCodeTypeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            log.error("Failed to update post.", e);
            return ResponseUtil.getErrorMessageResponse(e.getMessage());
        }
    }

    @Transactional
    public Response deletePost(Long id) {
        try {
            Map<String, Object> result = new HashMap<>();

            log.info("Finding post : {}.", id);
            Optional<Posts> posts = postsRepository.findById(id);
            if (!posts.isPresent()) {
                log.info("Post not found id : {}.", id);
                return ResponseUtil.getErrorMessageResponse(ResponsCodeTypeEnum.PARAMETER_ERROR.getCode(), "Post not found id : " + id);
            }

            log.info("delete post.");
            postsRepository.delete(posts.get());

            log.info("Post deleted, id : {}.", id);
            result.put("message", "Post has been deleted.");
            return ResponseUtil.getSuccessResponse(result, ResponsCodeTypeEnum.ACCEPTED.getCode());
        } catch (Exception e) {
            log.error("Failed to delete post.", e);
            return ResponseUtil.getErrorMessageResponse(e.getMessage());
        }
    }

    public Response findAllPost(Pageable pageable) {
        try {
            Map<String, Object> result = new HashMap<>();

            List<Posts> posts = postsRepository.findAll(pageable).getContent();
            log.info("posts size : {}", posts.size());
            if (posts.isEmpty()) {
                log.info("Posts not found");
                return ResponseUtil.getErrorMessageResponse(ResponsCodeTypeEnum.PARAMETER_ERROR.getCode(), "Posts not found");
            }
            List<PostVo> postVos = new ArrayList<>();
            for (Posts c : posts) {
                PostVo data = new PostVo();
                BeanUtils.copyProperties(c, data);
                postVos.add(data);
            }
            log.info("postVos size : {}", postVos.size());
            result.put("message", "Data Found.");
            result.put("data", postVos);
            return ResponseUtil.getSuccessResponse(result, ResponsCodeTypeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            log.error("Failed to retrieve post.", e);
            return ResponseUtil.getErrorMessageResponse(e.getMessage());
        }
    }

    public Response findById(Long id) {
        try {
            Map<String, Object> result = new HashMap<>();

            log.info("Finding post : {}.", id);
            Optional<Posts> posts = postsRepository.findById(id);
            if (!posts.isPresent()) {
                log.info("Post not found id : {}.", id);
                return ResponseUtil.getErrorMessageResponse(ResponsCodeTypeEnum.PARAMETER_ERROR.getCode(), "Post not found id : " + id);
            }
            PostVo postVo = new PostVo();
            BeanUtils.copyProperties(posts.get(), postVo);

            result.put("message", "Data Found.");
            result.put("data", postVo);
            return ResponseUtil.getSuccessResponse(result, ResponsCodeTypeEnum.SUCCESS.getCode());
        } catch (Exception e) {
            log.error("Failed to retrieve post.", e);
            return ResponseUtil.getErrorMessageResponse(e.getMessage());
        }
    }

}
