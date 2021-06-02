package com.example.lesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private String name;
    private String phoneNumber;
    private String street;
    private Integer homeNumber;
    private Integer departmentID;
}
