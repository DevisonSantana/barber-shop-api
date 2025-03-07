package com.barber.shop.services;

import com.barber.shop.models.ScheduleEntity;

public interface IScheduleService {
    ScheduleEntity save(final ScheduleEntity entity);

    void delete(final Long id);
}
