package com.poly.lab7.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    private Date createDate = new Date();

    @ManyToOne
    @JoinColumn(name = "Categoryid")
    private Category category;
}