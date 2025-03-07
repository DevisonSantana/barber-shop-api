package com.barber.shop.services.quey.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.barber.shop.exceptions.EmailAlreadyInUseException;
import com.barber.shop.exceptions.NotFoundException;
import com.barber.shop.exceptions.PhoneAlreadyInUseException;
import com.barber.shop.models.ClientEntity;
import com.barber.shop.repository.IClientRepository;
import com.barber.shop.services.quey.IClientQueryService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {
    private final IClientRepository repository;

    @Override
    public ClientEntity findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Não foi encontrado o cliente com o ID: " + id));
    }

    @Override
    public List<ClientEntity> list() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if (repository.existsByPhone(phone)) {
            var message = "O telefone " + phone + " já está sendo utilizado";
            throw new PhoneAlreadyInUseException(message);
        }
    }

    @Override
    public void verifyPhone(Long id, String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            var message = "O telefone " + phone + " já está sendo utilizado";
            throw new PhoneAlreadyInUseException(message);
        }
    }

    @Override
    public void verifyEmail(String email) {
        if (repository.existsByEmail(email)) {
            var message = "O Email " + email + " já está em uso";
            throw new EmailAlreadyInUseException(message);
        }
    }

    @Override
    public void verifyEmail(Long id, String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getEmail(), email)) {
            var message = "O Email " + email + " já possui cadastro";
            throw new EmailAlreadyInUseException(message);
        }
    }

}
