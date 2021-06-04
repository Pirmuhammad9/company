package com.example.lesson1task1.payload;

import com.example.lesson1task1.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseGetOne {
    private String message;
    private boolean success;
    private Object object;
}
