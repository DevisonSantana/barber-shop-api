package com.barber.shop.services.quey;

import java.time.OffsetDateTime;
import java.util.List;

import com.barber.shop.models.ScheduleEntity;

public interface IScheduleQueryService {
    ScheduleEntity findById(final Long id);

    List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt);

    void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt);
}
