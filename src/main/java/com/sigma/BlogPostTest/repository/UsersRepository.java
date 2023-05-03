package com.sigma.BlogPostTest.repository;

import com.sigma.BlogPostTest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query(
            value = "SELECT * \n" +
                    "FROM users\n" +
                    "WHERE name = :name\n" +
                    "AND password = :password",
            nativeQuery = true)
    Users findUser(@Param("name") String name, @Param("password") String password);
    @Query(
            value = "SELECT * \n" +
                    "FROM users\n" +
                    "WHERE name = :name\n",
            nativeQuery = true)
    Users findUserByName(@Param("name") String name);
    @Query(
            value = "SELECT * \n" +
                    "FROM users\n" +
                    "WHERE id = :id\n",
            nativeQuery = true)
    Users findUserById(@Param("id") Long id);
}