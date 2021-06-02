package com.example.lesson1task1.service;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.entity.Company;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.CompanyDto;
import com.example.lesson1task1.repository.AddressRepository;
import com.example.lesson1task1.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Company> getAll(){
        return companyRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Company> byId = companyRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param companyDto
     * @return ResponseEntity
     */
    public ApiResponse addCompany(CompanyDto companyDto){
        boolean b = addressRepository.existsByHomeNumberAndStreet(companyDto.getHomeNumber(), companyDto.getStreet());
        boolean b1 = companyRepository.existsByCorpName(companyDto.getCorpName());
        boolean b2 = companyRepository.existsByDirectorName(companyDto.getDirectorName());
        if (b){
            return new ApiResponse("this address already exists in database", false);
        }
        if (b1 || b2){
            return new ApiResponse("something wrong", false);
        }
        Address address1 = new Address();
        address1.setHomeNumber(companyDto.getHomeNumber());
        address1.setStreet(companyDto.getStreet());
        addressRepository.save(address1);
        Company company = new Company();
        company.setAddress(address1);
        company.setCorpName(company.getCorpName());
        company.setDirectorName(company.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param companyDto
     * @return ResponseEntity
     */
    public ApiResponse editCompany(Integer id, CompanyDto companyDto){
        boolean b = addressRepository.existsByHomeNumberAndStreetAndIdNot(companyDto.getHomeNumber(), companyDto.getStreet(), id);
        boolean b1 = companyRepository.existsByCorpNameAndIdNot(companyDto.getCorpName(), id);
        boolean b2 = companyRepository.existsByDirectorNameAndIdNot(companyDto.getDirectorName(), id);
        if (b){
            return new ApiResponse("this address already exists in database", false);
        }
        if (b1 || b2){
            return new ApiResponse("something wrong", false);
        }
        if (!addressRepository.findById(id).isPresent()){
            return new ApiResponse("not found", false);
        }
        Address address1 = addressRepository.findById(id).get();
        address1.setHomeNumber(companyDto.getHomeNumber());
        address1.setStreet(companyDto.getStreet());
        addressRepository.save(address1);
        Company company = companyRepository.findById(id).get();
        company.setAddress(address1);
        company.setCorpName(company.getCorpName());
        company.setDirectorName(company.getDirectorName());
        companyRepository.save(company);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete company by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteCompany(Integer id){
        if (companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
