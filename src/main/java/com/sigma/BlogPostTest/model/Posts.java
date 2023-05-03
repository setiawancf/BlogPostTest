package com.sigma.BlogPostTest.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Setiawan Candrafu
 * @date 5/2/2023
 */
@Data
@Entity
public class Posts {

    @Id
    @SequenceGenerator(name= "POSTS_SEQUENCE", sequenceName = "POSTS_SEQUENCE_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="POSTS_SEQUENCE")
    private long id;
    private String title;
    private String body;
    private String author;
}
