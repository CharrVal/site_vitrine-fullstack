package com.artisan.vitrine.Business.mapper;

import com.artisan.vitrine.Presentation.dto.ActualityRequestDTO;
import com.artisan.vitrine.Presentation.dto.ActualityResponseDTO;
import com.artisan.vitrine.Persistence.entity.Actuality;
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
