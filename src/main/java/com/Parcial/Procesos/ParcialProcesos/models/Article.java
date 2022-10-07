package com.Parcial.Procesos.ParcialProcesos.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "store")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String code;

    @Column(length = 100)
    private String name;

    @Column(length = 300, nullable = false)
    private String description;

    private Date dateOfRegister;

    private Category category;

    @Column(length = 10, nullable = false)
    private String stock;

    @Column(length = 20, nullable = false)
    private String salePrice;

    @Column(length = 20)
    private String purchasePrice;

}