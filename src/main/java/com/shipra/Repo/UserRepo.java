package com.shipra.Repo;

import com.shipra.Entity.Task;
import com.shipra.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByEmail(String email);

    boolean existsByEmail(String email);

}
