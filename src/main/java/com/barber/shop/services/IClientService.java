package com.barber.shop.services;

import com.barber.shop.models.ClientEntity;

public interface IClientService {
    ClientEntity save(final ClientEntity entity);

    ClientEntity update(final ClientEntity entity);

    void delete(final Long id);
}
