package com.example.lesson1task1.service;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.entity.Department;
import com.example.lesson1task1.entity.Worker;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.ApiResponseGetOne;
import com.example.lesson1task1.payload.WorkerDto;
import com.example.lesson1task1.repository.AddressRepository;
import com.example.lesson1task1.repository.DepartmentRepository;
import com.example.lesson1task1.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AddressRepository addressRepository;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Worker> getAll(){
        return workerRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponseGetOne getOne(Integer id){
        Optional<Worker> byId = workerRepository.findById(id);
        return byId.isPresent()?new ApiResponseGetOne("found", true, byId.get()):new ApiResponseGetOne("not found", false, byId.get());
    }

    /**
     * This function is used to add new address into database
     * @param workerDto
     * @return ResponseEntity
     */
    public ApiResponse addWorker(WorkerDto workerDto){
        boolean b = addressRepository.existsByHomeNumberAndStreet(workerDto.getHomeNumber(), workerDto.getStreet());
        Optional<Department> byId = departmentRepository.findById(workerDto.getDepartmentID());
        boolean b1 = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (b){
            return new ApiResponse("this address already exists in database", false);
        }
        if (!byId.isPresent()){
            return new ApiResponse("Department not found", false);
        }
        if (b1){
            return new ApiResponse("this phone number already exits", false);
        }
        Address address1 = new Address();
        address1.setHomeNumber(workerDto.getHomeNumber());
        address1.setStreet(workerDto.getStreet());
        addressRepository.save(address1);
        Worker worker = new Worker();
        worker.setAddress(address1);
        worker.setDepartment(byId.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(worker.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param workerDto
     * @return ResponseEntity
     */
    public ApiResponse editWorker(Integer id, WorkerDto workerDto){
        boolean b = addressRepository.existsByHomeNumberAndStreet(workerDto.getHomeNumber(), workerDto.getStreet());
        Optional<Department> byId = departmentRepository.findById(workerDto.getDepartmentID());
        boolean b1 = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (b){
            return new ApiResponse("this address already exists in database", false);
        }
        if (!byId.isPresent()){
            return new ApiResponse("Department not found", false);
        }
        if (b1){
            return new ApiResponse("this phone number already exits", false);
        }
        Address address1 = new Address();
        address1.setHomeNumber(workerDto.getHomeNumber());
        address1.setStreet(workerDto.getStreet());
        addressRepository.save(address1);
        Worker worker = workerRepository.findById(id).get();
        worker.setAddress(address1);
        worker.setDepartment(byId.get());
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(worker.getPhoneNumber());
        workerRepository.save(worker);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteWorker(Integer id){
        if (workerRepository.existsById(id)){
            workerRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }

}
