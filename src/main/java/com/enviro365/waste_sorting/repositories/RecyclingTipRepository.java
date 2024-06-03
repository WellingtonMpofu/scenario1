package com.enviro365.waste_sorting.repositories;

import com.enviro365.waste_sorting.models.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingTipRepository extends JpaRepository<RecyclingTip,Long> {
}