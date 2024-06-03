package com.enviro365.waste_sorting.controllers;

import com.enviro365.waste_sorting.models.DisposalGuideline;
import com.enviro365.waste_sorting.models.RecyclingTip;
import com.enviro365.waste_sorting.services.RecyclingTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecyclingTipController {
    // injecting the recycling tip service
    @Autowired
    private RecyclingTipService recyclingTipService;

    @PostMapping("/waste-categories/{categoryId}/recycling-tips")
    public RecyclingTip createRecyclingTip(@PathVariable Long categoryId, @RequestBody RecyclingTip recyclingTip) {
        return recyclingTipService.createRecyclingTip(categoryId,recyclingTip);
    }

    @GetMapping("/recycling-tips")
    public List<RecyclingTip> getAllRecyclingTips() {
        return recyclingTipService.getAllRecyclingTips();
    }

    @GetMapping("/recycling-tips/{id}")
    public ResponseEntity<RecyclingTip> getRecyclingTipById(@PathVariable Long id) {
        return recyclingTipService.getRecyclingTipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/recycling-tips/{id}")
    public ResponseEntity<RecyclingTip> updateRecyclingTipById(@PathVariable Long id, @RequestBody RecyclingTip recyclingTip) {
        return recyclingTipService.updateRecyclingTipById(id, recyclingTip)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/recycling-tips/{id}")
    public ResponseEntity<Void> deleteRecyclingTipById(@PathVariable Long id) {
        return recyclingTipService.getRecyclingTipById(id)
                .map(existingGuideline -> {
                    recyclingTipService.deleteRecyclingTipById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
