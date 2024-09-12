package com.sparta.todo.service;

import com.sparta.todo.dto.TodoSaveRequestDto;
import com.sparta.todo.dto.TodoSaveResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        Todo newTodo = new Todo(
                todoSaveRequestDto.getTodo(),
                todoSaveRequestDto.getManagerName(),
                todoSaveRequestDto.getPassword());
        Todo savedTodo = todoRepository.save(newTodo);
        return new TodoSaveResponseDto(savedTodo.getId(),savedTodo.getTodo(),savedTodo.getManagerName());
    }
}
