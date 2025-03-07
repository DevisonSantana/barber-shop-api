package com.barber.shop.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import com.barber.shop.controllers.request.SaveClientRequest;
import com.barber.shop.controllers.request.UpdateClientRequest;
import com.barber.shop.controllers.response.ClientDetailResponse;
import com.barber.shop.controllers.response.ListClientResponse;
import com.barber.shop.controllers.response.SaveClientResponse;
import com.barber.shop.controllers.response.UpdateClientResponse;
import com.barber.shop.models.ClientEntity;

@Mapper(componentModel = ComponentModel.SPRING)
public interface IClientMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity entity);

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final Long id, final UpdateClientRequest request);

    UpdateClientResponse toUpdateResponse(final ClientEntity entity);

    ClientDetailResponse toDetailResponse(final ClientEntity entity);

    List<ListClientResponse> toListResponse(final List<ClientEntity> entities);
}
