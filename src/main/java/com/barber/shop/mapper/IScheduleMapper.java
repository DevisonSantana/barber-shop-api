package com.barber.shop.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import com.barber.shop.controllers.request.SaveScheduleRequest;
import com.barber.shop.controllers.response.ClientScheduleAppointmentResponse;
import com.barber.shop.controllers.response.SaveScheduleResponse;
import com.barber.shop.controllers.response.ScheduleAppointmentMonthResponse;
import com.barber.shop.models.ScheduleEntity;

@Mapper(componentModel = ComponentModel.SPRING)
public interface IScheduleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity entity);

    @Mapping(target = "scheduleAppointments", expression = "java(toClientMonthResponse(entities))")
    ScheduleAppointmentMonthResponse toMonthResponse(final Integer year, final Integer month,
            final List<ScheduleEntity> entities);

    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> entities);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(entity.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity entity);
}