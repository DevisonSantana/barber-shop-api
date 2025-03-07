package com.barber.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barber.shop.models.ClientEntity;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {

    Boolean existsByEmail(final String email);

    Optional<ClientEntity> findByEmail(final String email);

    Boolean existsByPhone(final String phone);

    Optional<ClientEntity> findByPhone(final String phone);
}
