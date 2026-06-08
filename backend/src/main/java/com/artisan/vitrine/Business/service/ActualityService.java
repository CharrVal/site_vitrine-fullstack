package com.artisan.vitrine.Business.service;

import com.artisan.vitrine.Presentation.dto.ActualityRequestDTO;
import com.artisan.vitrine.Presentation.dto.ActualityResponseDTO;

import java.util.List;

public interface ActualityService {
    ActualityResponseDTO getById(Long id);
    List<ActualityResponseDTO> getAll();
    ActualityResponseDTO createActuality(ActualityRequestDTO dto);
    ActualityResponseDTO updateActuality(Long id, ActualityRequestDTO dto);
    void deleteById(Long id);
}
