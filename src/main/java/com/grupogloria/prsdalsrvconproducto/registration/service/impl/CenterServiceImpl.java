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
import com.grupogloria.prsdalsrvconproducto.registration.domain.CenterEntity;
import com.grupogloria.prsdalsrvconproducto.registration.repository.CenterRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.CenterService;
import com.grupogloria.prsdalsrvconproducto.registration.util.ManageError;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseCenterDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.UnitMeasureDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CenterServiceImpl implements CenterService {

        @Autowired
        CenterRepository centerRepository;

        @Autowired
        private ModelMapper modelMapper;

        @Autowired
        ObjectMapper objMapper;

        @Override
        public List<ResponseCenterDto> getAllCenters(HeaderRequest headers) throws Exception {
                try {
                        log.info(
                                        GlobalConstants.HEADER + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                        + " - "
                                                        + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(headers));
                        List<CenterEntity> centers = centerRepository.findAll();

                        List<ResponseCenterDto> response = centers
                                        .stream()
                                        .map(center -> {
                                                ResponseCenterDto res = modelMapper.map(center,
                                                                ResponseCenterDto.class);
                                                res.setMateriales(getMaterials(center));
                                                return res;
                                        })
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

        private List<ResponseMaterialDto> getMaterials(CenterEntity center) {

                return center.getCentroMateriales()
                                .stream()
                                .map(centerMaterial -> {
                                        ResponseMaterialDto res = modelMapper.map(centerMaterial.getMaterial(),
                                                        ResponseMaterialDto.class);

                                        List<UnitMeasureDto> unitsMeasure = centerMaterial.getMaterial()
                                                        .getUnidadMedidas()
                                                        .stream()
                                                        .map(unitMeasure -> modelMapper.map(unitMeasure,
                                                                        UnitMeasureDto.class))
                                                        .collect(Collectors.toList());

                                        res.setUnidadMedidas(unitsMeasure);
                                        return res;
                                })
                                .collect(Collectors.toList());

        }

        @Override
        public ResponseCenterDto getCenterById(String id, HeaderRequest headers) throws Exception {
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
                CenterEntity center = centerRepository.findById(id)
                                .orElseThrow(() -> new Exception("Centro no encontrado con el id : " +
                                                id));
                try {

                        ResponseCenterDto response = modelMapper.map(center, ResponseCenterDto.class);

                        log.info(
                                        GlobalConstants.RESPONSE + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName() +
                                                        " - " + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(response));

                        return modelMapper.map(center, ResponseCenterDto.class);

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
