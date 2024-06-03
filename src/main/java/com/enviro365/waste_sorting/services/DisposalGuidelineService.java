package com.enviro365.waste_sorting.services;

import com.enviro365.waste_sorting.models.DisposalGuideline;
import com.enviro365.waste_sorting.models.WasteCategory;
import com.enviro365.waste_sorting.repositories.DisposalGuidelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalGuidelineService {
    // injecting the waste category repository
    @Autowired
    private DisposalGuidelineRepository disposalGuidelineRepository;
    @Autowired
    private WasteCategoryService wasteCategoryService;

    // creating a new disposal guideline
    public DisposalGuideline createDisposalGuideline(Long categoryId,DisposalGuideline disposalGuideline) {
        // check if the wasteCategory with the given id exist before creating the disposal guideline
        Optional<WasteCategory> wasteCategoryOptional = wasteCategoryService.getWasteCategoryById(categoryId);
        if (wasteCategoryOptional.isPresent()) { // Check if the WasteCategory exists
            WasteCategory wasteCategory = wasteCategoryOptional.get();
            disposalGuideline.setWasteCategory(wasteCategory);
            return disposalGuidelineRepository.save(disposalGuideline);
        }
        else {
            return null;
        }
    }

    // viewing all disposal guidelines
    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return disposalGuidelineRepository.findAll();
    }

    // viewing a disposal guideline by id
    public Optional<DisposalGuideline> getDisposalGuidelineById(Long id) {
        return disposalGuidelineRepository.findById(id);
    }

    // deleting a disposal guideline by id
    public void deleteDisposalGuidelineById(Long id) {
        disposalGuidelineRepository.deleteById(id);
    }

    // updating disposal guideline details by id
    public Optional<DisposalGuideline> updateDisposalGuidelineById(Long id, DisposalGuideline updatedDisposalGuideline) {
        return disposalGuidelineRepository.findById(id)
                .map(existingGuideline -> {
                    existingGuideline.setDisposalMethod(updatedDisposalGuideline.getDisposalMethod());
                    existingGuideline.setInstructions(updatedDisposalGuideline.getInstructions());
                    existingGuideline.setRegulations(updatedDisposalGuideline.getRegulations());
                    existingGuideline.setHazardLevel(updatedDisposalGuideline.getHazardLevel());
                    return disposalGuidelineRepository.save(existingGuideline);
                });
    }
}

