package com.sparta.todo.dto;

import lombok.Getter;

@Getter
public class TodoUpdateRequestDto {
    private String todo;
    private String managerName;
}
