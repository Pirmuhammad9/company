package com.example.lesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String corpName;
    private String directorName;
    private String street;
    private Integer homeNumber;
}
