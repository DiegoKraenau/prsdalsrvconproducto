package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;

import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseCenterDto;

public interface CenterService {
    List<ResponseCenterDto> getAllCenters() throws SqlException, Exception;
}
