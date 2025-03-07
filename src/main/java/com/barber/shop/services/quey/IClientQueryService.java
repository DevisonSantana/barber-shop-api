package com.barber.shop.services.quey;

import java.util.List;

import com.barber.shop.models.ClientEntity;

public interface IClientQueryService {
    ClientEntity findById(final Long id);

    List<ClientEntity> list();

    void verifyPhone(final String phone);

    void verifyPhone(final Long id, final String phone);

    void verifyEmail(final String email);

    void verifyEmail(final Long id, final String email);
}
