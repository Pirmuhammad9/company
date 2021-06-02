package com.example.lesson1task1.payload;

import com.example.lesson1task1.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private String name;
    private Integer companyID;

}
