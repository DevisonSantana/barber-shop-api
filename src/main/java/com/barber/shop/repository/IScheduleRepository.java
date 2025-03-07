package com.barber.shop.repository;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barber.shop.models.ScheduleEntity;

@Repository
public interface IScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(
            final OffsetDateTime startAt, final OffsetDateTime endAt);

    Boolean existsByStartAtAndEndAt(final OffsetDateTime startAt, final OffsetDateTime endAt);
}
