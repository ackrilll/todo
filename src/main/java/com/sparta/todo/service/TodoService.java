package com.sparta.todo.service;

import com.sparta.todo.dto.*;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getTodo(),
                savedTodo.getManagerName(),
                savedTodo.getCreatedAt(),
                savedTodo.getModifiedAt());
    }

    public List<TodoSimpleResponseDto> getTodos(String date) {
        LocalDateTime startDateTime = LocalDate.parse(date).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(date).atTime(LocalTime.MAX);

        List<Todo> todoList = todoRepository.findAllByCreatedAtBetweenOrderByModifiedAtDesc(startDateTime, endDateTime);
        List<TodoSimpleResponseDto> dtoList = new ArrayList<>();
        for (Todo todo : todoList){
            TodoSimpleResponseDto todoSimpleResponseDto = new TodoSimpleResponseDto(
                    todo.getId(),
                    todo.getTodo(),
                    todo.getManagerName(),
                    todo.getCreatedAt(),
                    todo.getModifiedAt());
            dtoList.add(todoSimpleResponseDto);
        }
        return dtoList;
    }

    public TodoDetailResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new NullPointerException("일정 못 찾음"));
        return new TodoDetailResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getManagerName(),
                todo.getCreatedAt(),
                todo.getModifiedAt());
    }

    @Transactional
    public TodoUpdateResponseDto updateTodo(Long todoId, TodoUpdateRequestDto todoUpdateRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new NullPointerException("일정 못 찾음"));
        todo.update(todoUpdateRequestDto.getTodo(),todoUpdateRequestDto.getManagerName());
        return new TodoUpdateResponseDto(
                todo.getId(),
                todo.getTodo(),
                todo.getManagerName(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }

    @Transactional
    public void deleteTodo(Long todoId, TodoDeleteRequestDto todoDeleteRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(()->new NullPointerException("일정 못 찾음"));
        if(!todo.getPassword().equals(todoDeleteRequestDto.getPassword())){
            throw new NullPointerException("패스워드 불일치");
        }
        todoRepository.delete(todo);

    }
}
