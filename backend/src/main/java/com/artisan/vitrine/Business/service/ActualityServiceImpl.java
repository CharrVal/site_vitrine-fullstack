package com.artisan.vitrine.Business.service;

import com.artisan.vitrine.Presentation.dto.ActualityRequestDTO;
import com.artisan.vitrine.Presentation.dto.ActualityResponseDTO;
import com.artisan.vitrine.Persistence.entity.Actuality;
import com.artisan.vitrine.Business.mapper.ActualityMapper;
import com.artisan.vitrine.Persistence.repository.ActualityRepository;
import com.artisan.vitrine.Business.service.exception.ProductServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualityServiceImpl implements ActualityService {

    private final ActualityRepository repository;
    private final ActualityMapper mapper;

    public ActualityServiceImpl(ActualityRepository repository, ActualityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ActualityResponseDTO getById(Long id) {
        Actuality actuality = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actuality not found" + id));
        return mapper.toResponse(actuality);
    }

    @Override
    public List<ActualityResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public ActualityResponseDTO createActuality(ActualityRequestDTO dto) {
        Actuality actuality = new Actuality();
        actuality.setTitle(dto.getTitle());
        actuality.setDescription(dto.getDescription());
        Actuality saved = repository.save(actuality);
        return mapper.toResponse(saved);
    }

    @Override
    public ActualityResponseDTO updateActuality(Long id, ActualityRequestDTO dto) {
        Actuality actuality = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actuality not found: " + id));;

        actuality.setTitle(dto.getTitle());
        actuality.setDescription(dto.getDescription());
        Actuality saved = repository.save(actuality);
        return mapper.toResponse(saved);
    }

    @Override
    public void deleteById(Long id) {
        Actuality actuality = repository.findById(id)
                .orElseThrow(() -> new ProductServiceException("Actuality not found with id " + id));
        repository.delete(actuality);
    }
}
