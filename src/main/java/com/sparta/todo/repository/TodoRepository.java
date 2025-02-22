package com.sparta.todo.repository;

import com.sparta.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByCreatedAtBetweenOrderByModifiedAtDesc(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
