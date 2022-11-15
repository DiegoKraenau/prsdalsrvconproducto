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
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.repository.MaterialRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.MaterialService;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponseMaterialDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<ResponseMaterialDto> getAllMaterials() throws SqlException, Exception {
        try {
            List<MaterialEntity> materials = materialRepository.findAll();

            return materials
                    .stream()
                    .map(material -> modelMapper.map(material, ResponseMaterialDto.class))
                    .collect(Collectors.toList());

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Conexion fallida. Por favor probar mas tarde.");
        } catch (Exception e) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
            throw new SqlException("Error de data. Por favor probar mas tarde.");
        }
    }

    @Override
    public ResponseMaterialDto getMaterialById(Long id) throws SqlException, Exception {
        MaterialEntity material = materialRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Material no encontrado con el id : " +
                        id));
        try {
            return modelMapper.map(material, ResponseMaterialDto.class);

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Conexion fallida. Por favor probar mas tarde.");
        } catch (Exception e) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
            throw new SqlException("Error de data. Por favor probar mas tarde.");
        }
    }

}
