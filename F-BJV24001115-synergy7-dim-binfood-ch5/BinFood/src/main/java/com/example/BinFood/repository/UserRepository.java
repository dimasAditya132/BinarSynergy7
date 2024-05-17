package com.example.BinFood.repository;

import com.example.BinFood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE  users u SET username = :new WHERE u.username = :old")
    void queryUpdateUsername(@Param("new") String name, @Param("old") String oldName);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE users u SET password = :new WHERE u.username = :name")
    void queryUpdatePassword(@Param("new") String password, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE users u set email_address = :new where u.username = :name")
    void queryUpdateEmail(@Param("new") String email, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM users u " +
            "where u.username = :username " +
            "AND u.password = :password " +
            "AND u.email_address = :email_address")
    void queryDeleteByName(@Param("username") String username,
                           @Param("password") String password,
                           @Param("email_address") String email_address);

    @Query(nativeQuery = true, value = "SELECT * FROM users u where u.username = :username")
    User queryFindUserByName(@Param("username") String username);

    @Query(nativeQuery = true, value = "SELECT  * FROM users u WHERE u.email_address = :email_address")
    User queryFindUserByEmail(@Param("email_address") String email_address);
}
