package com.enviro365.waste_sorting.repositories;

import com.enviro365.waste_sorting.models.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline,Long> {
}
