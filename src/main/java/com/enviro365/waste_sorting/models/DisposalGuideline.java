package com.enviro365.waste_sorting.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String disposalMethod;

    @Column
    private String instructions;

    @Column
    private String hazardLevel; // hazard level associated with the disposal

    @Column
    private String regulations; // applicable regulations that govern the disposal

    @Column
    private Date createdAt = new Date();

    // establishing a Many-To-One relationship between DisposalGuideline and WasteCategory based on category_id
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private WasteCategory wasteCategory;

}
