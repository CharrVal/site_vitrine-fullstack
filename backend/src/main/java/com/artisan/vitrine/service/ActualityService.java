package com.artisan.vitrine.service;

import com.artisan.vitrine.dto.ActualityRequestDTO;
import com.artisan.vitrine.dto.ActualityResponseDTO;
import com.artisan.vitrine.entity.Actuality;

import java.util.List;

public interface ActualityService {
    ActualityResponseDTO getById(Long id);
    List<ActualityResponseDTO> getAll();
    ActualityResponseDTO createActuality(ActualityRequestDTO dto);
    ActualityResponseDTO updateActuality(Long id, ActualityRequestDTO dto);
    void deleteById(Long id);
}
