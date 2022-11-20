package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.hibernate.exception.JDBCConnectionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.ElkLogger;
import com.grupogloria.prsdalsrvconproducto.registration.domain.CenterEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.repository.CenterRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.CenterService;
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

    @Override
    public List<ResponseCenterDto> getAllCenters() throws SqlException, Exception {
        try {
            List<CenterEntity> centers = centerRepository.findAll();

            return centers
                    .stream()
                    .map(center -> {
                        ResponseCenterDto res = modelMapper.map(center, ResponseCenterDto.class);
                        res.setMateriales(getMaterials(center));
                        return res;
                    })
                    .collect(Collectors.toList());

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Conexion fallida. Por favor probar mas tarde.");
        } catch (Exception e) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
            throw new SqlException("Error de data. Por favor probar mas tarde.");
        }
    }

    private List<ResponseMaterialDto> getMaterials(CenterEntity center) {

        return center.getCentroMateriales()
                .stream()
                .map(centerMaterial -> {
                    ResponseMaterialDto res = modelMapper.map(centerMaterial.getMaterial(), ResponseMaterialDto.class);

                    List<UnitMeasureDto> unitsMeasure = centerMaterial.getMaterial().getUnidadMedidas()
                            .stream()
                            .map(unitMeasure -> modelMapper.map(unitMeasure, UnitMeasureDto.class))
                            .collect(Collectors.toList());

                    res.setUnidadMedidas(unitsMeasure);
                    return res;
                })
                .collect(Collectors.toList());

    }

}
