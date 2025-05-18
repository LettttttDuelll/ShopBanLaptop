package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Laptop;

public interface LaptopService {

    List<Laptop> getLaptops();

    Laptop saveLaptop(Laptop laptop);

    Laptop getLaptopById(Integer id);

    void deleteLaptop(Integer id);

    Laptop updateLaptop(Laptop laptop);

    List<Laptop> getLaptopByNameOrModel(String name, String model);

    public Page<Laptop> getLaptops1(Pageable pageable);
    
    public Page<Laptop> getLaptopByNameOrModel1(String keywork, Pageable pageable) ;
}
