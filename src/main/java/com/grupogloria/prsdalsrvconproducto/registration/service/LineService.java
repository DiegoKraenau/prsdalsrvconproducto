package com.grupogloria.prsdalsrvconproducto.registration.service;

import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseLineDto;

public interface LineService {
    ResponseLineDto getLineByID(Long id, HeaderRequest headers) throws Exception;
}
