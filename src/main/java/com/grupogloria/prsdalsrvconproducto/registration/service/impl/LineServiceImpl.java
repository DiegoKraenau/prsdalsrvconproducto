package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.domain.LineEntity;
import com.grupogloria.prsdalsrvconproducto.registration.repository.LineRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.LineService;
import com.grupogloria.prsdalsrvconproducto.registration.util.ManageError;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseLineDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LineServiceImpl implements LineService {

    @Autowired
    LineRepository lineRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ObjectMapper objMapper;

    @Override
    public ResponseLineDto getLineByID(Long id, HeaderRequest headers) throws Exception {
        log.info(
                GlobalConstants.HEADER + " - "
                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                        + " - "
                        + headers.getIdTransaccion() + ": {}",
                objMapper.writeValueAsString(headers));

        JSONObject params = new JSONObject();
        params.put("id", id);

        log.info(
                GlobalConstants.PARAMS + " - "
                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                        + " - "
                        + headers.getIdTransaccion() + ": {}",
                params);
        try {
            LineEntity line = lineRepository.findById(id)
                    .orElseThrow(() -> new Exception("Linea no encontrada con el id : " +
                            id));

            ResponseLineDto response = modelMapper.map(line, ResponseLineDto.class);

            log.info(
                    GlobalConstants.RESPONSE + " - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName() +
                            " - " + headers.getIdTransaccion() + ": {}",
                    objMapper.writeValueAsString(response));

            return modelMapper.map(line, ResponseLineDto.class);

        } catch (Exception e) {
            log.error(
                    GlobalConstants.ERROR + " - "
                            + Thread.currentThread().getStackTrace()[1].getMethodName()
                            + " - "
                            + headers.getIdTransaccion() + ": {}",
                    objMapper.writeValueAsString(ManageError
                            .builder()
                            .idTransaccion(headers.getIdTransaccion())
                            .message(e.getMessage())
                            .build()));

            throw new Exception("Error de data. Por favor probar mas tarde.");
        }
    }

}
