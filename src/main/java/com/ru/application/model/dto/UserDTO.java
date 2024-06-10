package com.ru.application.model.dto;

public record UserDTO (
        String name,
        String email,
        String password,
        Integer age
){
}
