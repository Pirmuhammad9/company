package com.example.lesson1task1.controller;

import com.example.lesson1task1.entity.Address;
import com.example.lesson1task1.payload.ApiResponse;
import com.example.lesson1task1.payload.ApiResponseGetOne;
import com.example.lesson1task1.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(addressService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        ApiResponseGetOne response = addressService.getOne(id);
        if (response.isSuccess()){
            return ResponseEntity.ok(response.getObject());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * This function is used to add new address into database
     * @param address
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody Address address){
        ApiResponse apiResponse = addressService.addAddress(address);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param address
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editAddress(@PathVariable Integer id, @RequestBody Address address){
        ApiResponse apiResponse = addressService.editAddress(id, address);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
