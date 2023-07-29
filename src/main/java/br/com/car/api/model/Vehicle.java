package br.com.car.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicleId")
    private Long id;

    @Column(name = "modelName")
    private String modelName;

    @Column(name = "fuelType")
    private String fuelType;

    @Column(name = "fabricationYear")
    private int fabricationYear;

    @Column(name = "model")
    private int model;

    @Column(name = "plate")
    private String plate;

    @Column(name = "occupants")
    private int occupants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "typeName")
    private Type typeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colorName")
    private Color colorName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturerName")
    private Manufacturer manufacturerName;

    @Column(name = "createdAt")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updatedAt")
    @UpdateTimestamp
    private Date updatedAt;
}
