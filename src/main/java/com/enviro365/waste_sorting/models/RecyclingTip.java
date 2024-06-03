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
public class RecyclingTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tip;

    @Column(length = 2000)
    private String description;

    @Column
    private Date createdAt = new Date();

    // establishing a Many-To-One relationship between RecyclingTip and WasteCategory based on category_id
    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "category_id", nullable = false)
    @JoinColumn(name = "category_id")
    private WasteCategory wasteCategory;
}
