package com.wedasoft.JavaFxSpringBootGradleApp.persistence.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
