package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupogloria.prsdalsrvconproducto.registration.constants.GlobalConstants;
import com.grupogloria.prsdalsrvconproducto.registration.controller.request.HeaderRequest;
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.repository.MaterialRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.MaterialService;
import com.grupogloria.prsdalsrvconproducto.registration.util.ManageError;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MaterialServiceImpl implements MaterialService {

        @Autowired
        MaterialRepository materialRepository;

        @Autowired
        ModelMapper modelMapper;

        @Autowired
        ObjectMapper objMapper;

        @Override
        public List<ResponseMaterialDto> getAllMaterials(HeaderRequest headers) throws Exception {
                try {
                        log.info(
                                        GlobalConstants.HEADER + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                        + " - "
                                                        + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(headers));

                        List<MaterialEntity> materials = materialRepository.findAll();

                        List<ResponseMaterialDto> response = materials
                                        .stream()
                                        .map(material -> modelMapper.map(material, ResponseMaterialDto.class))
                                        .collect(Collectors.toList());

                        log.info(
                                        GlobalConstants.RESPONSE + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName() +
                                                        " - " + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(response));

                        return response;

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

        @Override
        public ResponseMaterialDto getMaterialById(String id, HeaderRequest headers) throws Exception {
                log.info(
                                GlobalConstants.HEADER + " - "
                                                + Thread.currentThread().getStackTrace()[1].getMethodName() + " - "
                                                + headers.getIdTransaccion() + ": {}",
                                objMapper.writeValueAsString(headers));

                JSONObject params = new JSONObject();
                params.put("id", id);

                log.info(
                                GlobalConstants.PARAMS + " - "
                                                + Thread.currentThread().getStackTrace()[1].getMethodName() + " - "
                                                + headers.getIdTransaccion() + ": {}",
                                params);

                MaterialEntity material = materialRepository
                                .findById(id)
                                .orElseThrow(() -> new Exception("Material no encontrado con el id : " +
                                                id));

                try {
                        ResponseMaterialDto response = modelMapper.map(material, ResponseMaterialDto.class);

                        log.info(
                                        GlobalConstants.RESPONSE + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName() +
                                                        " - " + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(response));

                        return response;

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
