package com.enviro365.waste_sorting.services;

import com.enviro365.waste_sorting.models.WasteCategory;
import com.enviro365.waste_sorting.repositories.WasteCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WasteCategoryService {
    // injecting the waste category repository
    @Autowired
    private WasteCategoryRepository wasteCategoryRepository;

    // creating a new waste category
    public void createWasteCategory(WasteCategory wasteCategory) {
        wasteCategoryRepository.save(wasteCategory);
    }

    // viewing all waste categories
    public List<WasteCategory> getAllWasteCategories() {
        //  fetching all the available waste categories and returning them as a list
        return new ArrayList<>(wasteCategoryRepository.findAll());
    }

    // viewing a waste category by the id
    public Optional<WasteCategory> getWasteCategoryById(Long id) {
        return wasteCategoryRepository.findById(id);
    }

    // deleting a waste category by id
    public void deleteWasteCategoryById(Long id) {
        wasteCategoryRepository.deleteById(id);
    }

    // updating waste category details by id
    public Optional<WasteCategory> updateWasteCategory(Long id, WasteCategory updatedWasteCategory) {
        return wasteCategoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedWasteCategory.getName());
                    existingCategory.setDescription(updatedWasteCategory.getDescription());
                    existingCategory.setHazardous(updatedWasteCategory.isHazardous());
                    existingCategory.setRecyclable(updatedWasteCategory.isRecyclable());
                    existingCategory.setCompostable(updatedWasteCategory.isCompostable());
                    return wasteCategoryRepository.save(existingCategory);
                });
    }

}
