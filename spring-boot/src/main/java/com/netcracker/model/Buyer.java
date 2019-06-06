package com.netcracker.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "buyers")
@Data
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "discount", nullable = false)
    private double discount;
}
