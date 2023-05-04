package com.sigma.BlogPostTest.service;

import com.sigma.BlogPostTest.BlogPostTestApplication;
import com.sigma.BlogPostTest.common.Response;
import com.sigma.BlogPostTest.vo.PostVo;
import com.sigma.BlogPostTest.vo.UserInfoVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Setiawan Candrafu
 * @date 5/3/2023
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogPostTestApplication.class)
class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void createPost() {
        UserInfoVo userInfo = new UserInfoVo();
        userInfo.setId(1L);
        userInfo.setName("Setiawan");
        PostVo postVo = new PostVo();
        postVo.setTitle("Day 2357");
        postVo.setBody("Another day being alive");

        Response response = postService.createPost(userInfo, postVo);
        assertEquals("201", response.getCode().toString());
    }

    @Test
    void updatePost() {
        PostVo postVo = new PostVo();
        postVo.setId(201L);
        postVo.setTitle("Day 2357");
        postVo.setBody("Another day being alive");

        Response response = postService.updatePost(postVo);
        assertEquals("200", response.getCode().toString());
    }

    @Test
    void deletePost() {
        Response response = postService.deletePost(201L);
        assertEquals("202", response.getCode().toString());
    }

    @Test
    void findAllPost() {
        Response response = postService.findAllPost(PageRequest.of(0, 2));
        int size = ((List<PostVo>) ((Map<String, Object>) response.getResult()).get("data")).size();
        assertEquals(2, size);
    }

    @Test
    void findById() {
        Response response = postService.findById(201L);
        assertEquals("Setiawan", ((PostVo)((Map<String, Object>) response.getResult()).get("data")).getAuthor());
    }
}