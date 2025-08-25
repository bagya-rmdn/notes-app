package com.example.notes_app.repository;

import com.example.notes_app.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> getByUsername(@Param("username") String username);
}
