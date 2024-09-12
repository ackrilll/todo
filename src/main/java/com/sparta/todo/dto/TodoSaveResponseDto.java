package com.sparta.todo.dto;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {
    private final Long id;
    private final String todo;
    private final String managerName;

    public TodoSaveResponseDto(Long id, String todo, String managerName){
        this.id = id;
        this.todo = todo;
        this.managerName = managerName;
    }
}
