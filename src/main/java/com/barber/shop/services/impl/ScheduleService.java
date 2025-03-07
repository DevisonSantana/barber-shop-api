package com.barber.shop.services.impl;

import org.springframework.stereotype.Service;

import com.barber.shop.models.ScheduleEntity;
import com.barber.shop.repository.IScheduleRepository;
import com.barber.shop.services.IScheduleService;
import com.barber.shop.services.quey.IScheduleQueryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService {
    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(ScheduleEntity entity) {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }

}
