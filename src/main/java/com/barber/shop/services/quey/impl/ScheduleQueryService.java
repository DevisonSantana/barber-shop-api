package com.barber.shop.services.quey.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.barber.shop.exceptions.NotFoundException;
import com.barber.shop.exceptions.ScheduleAlreadyInUseException;
import com.barber.shop.models.ScheduleEntity;
import com.barber.shop.repository.IScheduleRepository;
import com.barber.shop.services.quey.IScheduleQueryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {
    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Agendamento não encontrado"));
    }

    @Override
    public List<ScheduleEntity> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return repository
                .findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if (repository.existsByStartAtAndEndAt(startAt, endAt)) {
            var message = "Já existe um cliente agendado no horario solicitado";
            throw new ScheduleAlreadyInUseException(message);
        }
    }

}
