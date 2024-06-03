package com.enviro365.waste_sorting.controllers;

import com.enviro365.waste_sorting.models.DisposalGuideline;
import com.enviro365.waste_sorting.services.DisposalGuidelineService;
import com.enviro365.waste_sorting.services.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DisposalGuidelineController {

    // injecting the disposal guideline service
    @Autowired
    private DisposalGuidelineService disposalGuidelineService;

    @PostMapping("/waste-categories/{categoryId}/disposal-guidelines")
    public DisposalGuideline createDisposalGuideline(@PathVariable Long categoryId, @RequestBody DisposalGuideline disposalGuideline) {
        return disposalGuidelineService.createDisposalGuideline(categoryId,disposalGuideline);
    }

    @GetMapping("/disposal-guidelines")
    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return disposalGuidelineService.getAllDisposalGuidelines();
    }

    @GetMapping("/disposal-guidelines/{id}")
    public ResponseEntity<DisposalGuideline> getDisposalGuidelineById(@PathVariable Long id) {
        return disposalGuidelineService.getDisposalGuidelineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/disposal-guidelines/{id}")
    public ResponseEntity<DisposalGuideline> updateDisposalGuidelineById(@PathVariable Long id, @RequestBody DisposalGuideline disposalGuideline) {
        return disposalGuidelineService.updateDisposalGuidelineById(id, disposalGuideline)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/disposal-guidelines/{id}")
    public ResponseEntity<Void> deleteDisposalGuidelineById(@PathVariable Long id) {
        return disposalGuidelineService.getDisposalGuidelineById(id)
                .map(existingGuideline -> {
                    disposalGuidelineService.deleteDisposalGuidelineById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
