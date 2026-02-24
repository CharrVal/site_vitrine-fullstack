package com.artisan.vitrine.service;

import com.artisan.vitrine.entity.Actuality;
import com.artisan.vitrine.repository.ActualityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualityServiceImpl implements ActualityService {

    private final ActualityRepository repository;

    public ActualityServiceImpl(ActualityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Actuality getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actuality not found" + id));
    }

    @Override
    public List<Actuality> getAll() {
        return repository.findAll();
    }

    @Override
    public Actuality createActuality(Actuality actuality) {
        return repository.save(actuality);
    }

    @Override
    public Actuality updateActuality(Long id, Actuality actualityUpdated) {
        Actuality actualityExisting = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actuality not found: " + id));;

        actualityExisting.setTitle(actualityUpdated.getTitle());
        actualityExisting.setDescription(actualityUpdated.getDescription());
        return repository.save(actualityExisting);
    }

    @Override
    public void deleteById(Long id) {

    }
}
