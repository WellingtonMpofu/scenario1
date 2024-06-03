package com.enviro365.waste_sorting.controllers;

import com.enviro365.waste_sorting.models.WasteCategory;
import com.enviro365.waste_sorting.services.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("waste-categories")
public class WasteCategoryController {
    // injecting the waste category service
    @Autowired
    private WasteCategoryService wasteCategoryService;

    @PostMapping
    public void createWasteCategory(@RequestBody WasteCategory wasteCategory) {
        wasteCategoryService.createWasteCategory(wasteCategory);
    }

    @GetMapping
    public List<WasteCategory> getAllWasteCategories() {
        return wasteCategoryService.getAllWasteCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(@PathVariable Long id) {
        return wasteCategoryService.getWasteCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteCategory(@PathVariable Long id) {
        return wasteCategoryService.getWasteCategoryById(id)
                .map(existingCategory -> {
                    wasteCategoryService.deleteWasteCategoryById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategory(
            @PathVariable Long id, @RequestBody WasteCategory updatedWasteCategory) {
        return wasteCategoryService.updateWasteCategory(id, updatedWasteCategory)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
