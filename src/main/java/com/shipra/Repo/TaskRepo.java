package com.shipra.Repo;

import com.shipra.Entity.Task;
import com.shipra.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByUser(Users user);

    Optional<Task> findByIdAndUser(Long id, Users user);
}
