package com.barber.shop.controllers;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.barber.shop.controllers.request.SaveScheduleRequest;
import com.barber.shop.controllers.response.SaveScheduleResponse;
import com.barber.shop.controllers.response.ScheduleAppointmentMonthResponse;
import com.barber.shop.mapper.IScheduleMapper;
import com.barber.shop.models.ScheduleEntity;
import com.barber.shop.services.IScheduleService;
import com.barber.shop.services.quey.IScheduleQueryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/schedules")
@AllArgsConstructor
public class ScheduleController {
    private final IScheduleService service;
    private final IScheduleQueryService queryService;
    private final IScheduleMapper mapper;

    @GetMapping("/{year}/{month}")
    public ScheduleAppointmentMonthResponse listMonth(@PathVariable("year") final Integer year, @PathVariable("month") final Integer month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        OffsetDateTime startAt = yearMonth.atDay(1).atTime(0, 0, 0, 0).atOffset(ZoneOffset.UTC);
        OffsetDateTime endAt = yearMonth.atEndOfMonth().atTime(23, 59, 59, 999_999_999).atOffset(ZoneOffset.UTC);
        List<ScheduleEntity> entities = queryService.findInMonth(startAt, endAt);
        return mapper.toMonthResponse(year, month, entities);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SaveScheduleResponse save(@RequestBody @Valid final SaveScheduleRequest request) {
        ScheduleEntity entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        service.delete(id);
    }
}
