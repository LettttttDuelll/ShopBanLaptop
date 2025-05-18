package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_laptop")
public class Laptop {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )//id tự động sinh, ko cần thêm id lúc save 1 new one
    private int id;

    private String laptop_name;

    private String laptop_model;

    private String laptop_originalPrice;

    private String laptop_currentPrice;

    private String laptop_discord;

    private String laptop_link;

    private String laptop_description;

    public void setId(Integer id) {
        this.id = id;
    }
}
