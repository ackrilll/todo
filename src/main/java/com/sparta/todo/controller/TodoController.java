package com.sparta.todo.controller;

import com.sparta.todo.dto.*;
import com.sparta.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto){
        return ResponseEntity.ok(todoService.saveTodo(todoSaveRequestDto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoSimpleResponseDto>> getTodos(@RequestParam String date){
        return ResponseEntity.ok(todoService.getTodos(date));
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<TodoDetailResponseDto> getTodo(@PathVariable Long todoId){
        return ResponseEntity.ok(todoService.getTodo(todoId));
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<TodoUpdateResponseDto> updateTodo(@PathVariable Long todoId,
                                                            @RequestBody TodoUpdateRequestDto todoUpdateRequestDto){
        return ResponseEntity.ok(todoService.updateTodo(todoId, todoUpdateRequestDto));
    }
}
