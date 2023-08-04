package com.product.repositories;

import com.product.entities.Task;
import com.product.entities.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    Page<Task> findAllByTodo(Todo todo, Pageable pageable);

    Optional<Task> findByIdAndTodo(UUID id, Todo todo);
}
