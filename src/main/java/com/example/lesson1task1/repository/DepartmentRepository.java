package com.example.lesson1task1.repository;

import com.example.lesson1task1.entity.Company;
import com.example.lesson1task1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByNameAndCompany(String name, Company company);
    boolean existsByNameAndCompanyAndIdNot(String name, Company company, Integer id);
}
