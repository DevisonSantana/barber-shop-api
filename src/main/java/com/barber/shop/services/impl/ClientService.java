package com.barber.shop.services.impl;

import org.springframework.stereotype.Service;

import com.barber.shop.models.ClientEntity;
import com.barber.shop.repository.IClientRepository;
import com.barber.shop.services.IClientService;
import com.barber.shop.services.quey.IClientQueryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {
    private final IClientRepository repository;
    private final IClientQueryService queryService;

    @Override
    public ClientEntity save(ClientEntity entity) {
        queryService.verifyEmail(entity.getEmail());
        queryService.verifyPhone(entity.getPhone());
        return repository.save(entity);
    }

    @Override
    public ClientEntity update(ClientEntity entity) {
        queryService.verifyEmail(entity.getId(), entity.getEmail());
        queryService.verifyPhone(entity.getId(), entity.getPhone());
        var stored = queryService.findById(entity.getId());
        stored.setName(entity.getName());
        stored.setEmail(entity.getEmail());
        stored.setPhone(entity.getPhone());
        return repository.save(stored);
    }

    @Override
    public void delete(Long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }

}
