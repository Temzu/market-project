package com.temzu.market_project.msauth.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_table")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
