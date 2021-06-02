package com.example.lesson1task1.service;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.entity.Company;
import com.example.lesson1task1.entity.Department;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.DepartmentDto;
import com.example.lesson1task1.repository.CompanyRepository;
import com.example.lesson1task1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Department> getAll(){
        return departmentRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Department> byId = departmentRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param departmentDto
     * @return ResponseEntity
     */
    public ApiResponse addDepartment(DepartmentDto departmentDto){
        Optional<Company> byId = companyRepository.findById(departmentDto.getCompanyID());
        if (!byId.isPresent()){
            return new ApiResponse("this company not found", false);
        }
        boolean b = departmentRepository.existsByNameAndCompany(departmentDto.getName(), byId.get());
        if (b){
            return new ApiResponse("this department already exists in database", false);
        }
        Department department = new Department();
        department.setCompany(byId.get());
        department.setName(departmentDto.getName());
       departmentRepository.save(department);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param departmentDto
     * @return ResponseEntity
     */
    public ApiResponse editDepartment(Integer id, DepartmentDto departmentDto){
        Optional<Company> byId = companyRepository.findById(departmentDto.getCompanyID());
        Optional<Department> byId1 = departmentRepository.findById(id);
        if (!byId.isPresent() || !byId1.isPresent()){
            return new ApiResponse("something wrong", false);
        }
        boolean b = departmentRepository.existsByNameAndCompanyAndIdNot(departmentDto.getName(), byId.get(), id);
        if (b){
            return new ApiResponse("this department already exists in database", false);
        }
        Department department = byId1.get();
        department.setCompany(byId.get());
        department.setName(departmentDto.getName());
        departmentRepository.save(department);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteDepartment(Integer id){
        if (departmentRepository.existsById(id)){
            departmentRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found", false);
    }

}
