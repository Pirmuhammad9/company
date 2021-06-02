package com.example.lesson1task1.repository;

import com.example.lesson1task1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Integer id);
    boolean existsByDirectorName(String directorName);
    boolean existsByDirectorNameAndIdNot(String directorName, Integer id);
}
