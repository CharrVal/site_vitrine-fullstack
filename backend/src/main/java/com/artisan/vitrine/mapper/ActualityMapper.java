package com.artisan.vitrine.mapper;

import com.artisan.vitrine.dto.ActualityRequestDTO;
import com.artisan.vitrine.dto.ActualityResponseDTO;
import com.artisan.vitrine.entity.Actuality;
import org.springframework.stereotype.Component;

@Component
public class ActualityMapper {

    public Actuality toEntity (ActualityRequestDTO dto) {
        Actuality actuality = new Actuality();
        actuality.setTitle(dto.getTitle());
        actuality.setDescription(dto.getDescription());

        return actuality;
    }

    public ActualityResponseDTO toResponse (Actuality actuality) {
        ActualityResponseDTO dto = new ActualityResponseDTO();
        dto.setId(actuality.getId());
        dto.setTitle(actuality.getTitle());
        dto.setDescription(actuality.getDescription());

        return dto;
    }
}
