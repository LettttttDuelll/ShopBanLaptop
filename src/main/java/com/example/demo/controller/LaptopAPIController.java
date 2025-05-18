package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Laptop;
import com.example.demo.service.LaptopService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class LaptopAPIController {

    @Autowired
    private LaptopService lService;

    @GetMapping("/laptops")
    public List<Laptop> getLaptops() {
        return lService.getLaptops();
    }

    @GetMapping("/laptops/{id}")
    public Laptop getLaptop(@PathVariable Integer id) {
        return lService.getLaptopById(id);
    }

    @DeleteMapping("/laptops")
    public void deleteLaptop(@RequestParam Integer id){
        lService.deleteLaptop(id);
    }
    //@RequestParam dùng để lấy giá trị từ tham số query trong URL hoặc dữ liệu form trong HTTP request.

    @PostMapping("/laptops")
    public Laptop saveLaptop(@RequestBody Laptop laptop) {
        return lService.saveLaptop(laptop);
    }

    @PutMapping("/laptops/{id}")
    public Laptop updateLaptop(@PathVariable Integer id, @RequestBody Laptop laptop) {
        laptop.setId(id);
        return lService.updateLaptop(laptop);
    }
    @GetMapping("/laptops/{name}/{model}")
    public List<Laptop> getLaptopByNameOrModel(@PathVariable String name,@PathVariable String model) {
        return lService.getLaptopByNameOrModel(name, model);
    }
    
}
