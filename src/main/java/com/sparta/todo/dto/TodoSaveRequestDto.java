package com.sparta.todo.dto;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {
    private String todo;
    private String managerName;
    private String password;
}
