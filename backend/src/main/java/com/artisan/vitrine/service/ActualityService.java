package com.artisan.vitrine.service;

import com.artisan.vitrine.entity.Actuality;

import java.util.List;

public interface ActualityService {
    Actuality getById(Long id);
    List<Actuality> getAll();
    Actuality createActuality(Actuality actuality);
    Actuality updateActuality(Long id, Actuality actualityUpdated);
    void deleteById(Long id);
}
