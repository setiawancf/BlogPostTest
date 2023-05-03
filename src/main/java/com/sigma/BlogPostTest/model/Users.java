package com.sigma.BlogPostTest.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Setiawan Candrafu
 * @date 3/18/2023
 */
@Data
@Entity
public class Users {

    @Id
    @SequenceGenerator(name= "USERS_SEQUENCE", sequenceName = "USERS_SEQUENCE_ID", initialValue=1, allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="USERS_SEQUENCE")
    private long id;
    private String name;
    private String email;
    private String password;


}
