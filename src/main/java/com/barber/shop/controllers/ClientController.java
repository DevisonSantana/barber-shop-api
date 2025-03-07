package com.barber.shop.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.barber.shop.controllers.request.SaveClientRequest;
import com.barber.shop.controllers.request.UpdateClientRequest;
import com.barber.shop.controllers.response.ClientDetailResponse;
import com.barber.shop.controllers.response.ListClientResponse;
import com.barber.shop.controllers.response.SaveClientResponse;
import com.barber.shop.controllers.response.UpdateClientResponse;
import com.barber.shop.mapper.IClientMapper;
import com.barber.shop.services.IClientService;
import com.barber.shop.services.quey.IClientQueryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {
    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @GetMapping
    public List<ListClientResponse> list() {
        var entities = queryService.list();
        return mapper.toListResponse(entities);
    }

    @GetMapping("/{id}")
    public ClientDetailResponse findById(@PathVariable("id") final Long id) {
        var entity = queryService.findById(id);
        return mapper.toDetailResponse(entity);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public SaveClientResponse save(@RequestBody @Valid final SaveClientRequest request) {
        var entity = mapper.toEntity(request);
        service.save(entity);
        return mapper.toSaveResponse(entity);
    }

    @PutMapping("/{id}")
    public UpdateClientResponse update(@PathVariable("id") final Long id, @RequestBody @Valid final UpdateClientRequest request) {
        var entity = mapper.toEntity(id, request);
        service.update(entity);
        return mapper.toUpdateResponse(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        service.delete(id);
    }
}
