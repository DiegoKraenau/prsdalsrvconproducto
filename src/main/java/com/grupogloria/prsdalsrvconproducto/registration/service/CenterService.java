package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseCenterDto;

public interface CenterService {
    List<ResponseCenterDto> getAllCenters(HeaderRequest headers) throws Exception;
}
