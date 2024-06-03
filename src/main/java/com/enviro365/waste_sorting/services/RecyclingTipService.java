package com.enviro365.waste_sorting.services;

import com.enviro365.waste_sorting.models.DisposalGuideline;
import com.enviro365.waste_sorting.models.RecyclingTip;
import com.enviro365.waste_sorting.models.WasteCategory;
import com.enviro365.waste_sorting.repositories.RecyclingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipService {
    // injecting the waste category repository
    @Autowired
    private RecyclingTipRepository recyclingTipRepository;
    @Autowired
    private WasteCategoryService wasteCategoryService;

    // creating a new recycling tip
    public RecyclingTip createRecyclingTip(Long categoryId, RecyclingTip recyclingTip) {
        // check if the wasteCategory with the given id exist before creating the recycling tip
        Optional<WasteCategory> wasteCategoryOptional = wasteCategoryService.getWasteCategoryById(categoryId);
        if (wasteCategoryOptional.isPresent()) { // Check if the WasteCategory exists
            WasteCategory wasteCategory = wasteCategoryOptional.get();
            recyclingTip.setWasteCategory(wasteCategory);
            return recyclingTipRepository.save(recyclingTip);
        }
        else {
            return null;
        }
    }

    // viewing all recycling tips
    public List<RecyclingTip> getAllRecyclingTips() {
        return recyclingTipRepository.findAll();
    }

    // viewing a recycling tip by id
    public Optional<RecyclingTip> getRecyclingTipById(Long id) {
        return recyclingTipRepository.findById(id);
    }

    // deleting a recycling tip by id
    public void deleteRecyclingTipById(Long id) {
        recyclingTipRepository.deleteById(id);
    }

    // updating recycling tip details by id
    public Optional<RecyclingTip> updateRecyclingTipById(Long id, RecyclingTip recyclingTip) {
        return recyclingTipRepository.findById(id)
                .map(existingGuideline -> {
                    existingGuideline.setTip(recyclingTip.getTip());
                    existingGuideline.setDescription(recyclingTip.getDescription());
                    return recyclingTipRepository.save(existingGuideline);
                });
    }

}
