package com.sigma.BlogPostTest.repository;

import com.sigma.BlogPostTest.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Setiawan Candrafu
 * @date 5/2/2023
 */
@EnableJpaRepositories
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
