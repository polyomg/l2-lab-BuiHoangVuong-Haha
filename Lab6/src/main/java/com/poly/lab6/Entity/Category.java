package com.poly.lab6.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "category")
    List<Product> products;
}
