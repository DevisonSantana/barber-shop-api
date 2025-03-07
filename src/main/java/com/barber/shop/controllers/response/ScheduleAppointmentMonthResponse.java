package com.barber.shop.controllers.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ScheduleAppointmentMonthResponse(
        @JsonProperty("year")
        Integer year,
        @JsonProperty("month")
        Integer month,
        @JsonProperty("")
        List<ClientScheduleAppointmentResponse> scheduleAppointments) {

}
