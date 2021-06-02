package com.example.lesson1task1.repository;

import com.example.lesson1task1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByHomeNumberAndStreet(Integer homeNumber, String street);

    boolean existsByHomeNumberAndStreetAndIdNot(Integer homeNumber, String street, Integer id);
}
