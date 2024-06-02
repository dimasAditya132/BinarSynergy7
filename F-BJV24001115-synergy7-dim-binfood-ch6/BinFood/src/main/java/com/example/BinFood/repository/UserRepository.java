package com.example.BinFood.repository;

import com.example.BinFood.model.accounts.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Procedure("insert_user_data")
    void insertUserData(String name, String username, String emailAddress, String password);

    @Procedure("update_user_username")
    void updateUserUsername(UUID id, String username);

    @Procedure("delete_user_data")
    void deleteUserData(UUID id);

    Optional<User> findByUsernameAndDeleted(String username, Boolean deleted);

    List<User> findByUsernameLike(String s);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmailAddress(String emailAddress);

}
