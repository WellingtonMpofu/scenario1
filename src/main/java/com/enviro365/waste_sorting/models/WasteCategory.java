package com.enviro365.waste_sorting.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1500)
    private String description;

    @Column(nullable = false)
    private boolean hazardous;

    @Column(nullable = false)
    private boolean recyclable;

    @Column(nullable = false)
    private boolean compostable;

    @Column
    private Date createdAt = new Date();

    // establishing a One-To-Many relationship between WasteCategory and DisposalGuideline
    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL)
    private List<DisposalGuideline> disposalGuidelines;

    // establishing a One-To-Many relationship between WasteCategory and RecyclingTip
    @OneToMany(mappedBy = "wasteCategory", cascade = CascadeType.ALL)
    private List<RecyclingTip> recyclingTips;
}
