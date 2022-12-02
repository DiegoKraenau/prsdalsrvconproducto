package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.hibernate.exception.JDBCConnectionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialEntity;
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
    public List<ResponseMaterialDto> getAllMaterials() throws Exception {
        try {
            List<MaterialEntity> materials = materialRepository.findAll();

            return materials
                    .stream()
                    .map(material -> modelMapper.map(material, ResponseMaterialDto.class))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            // log.error(GlobalConstants.ERROR + " - " +
            // Thread.currentThread().getStackTrace()[1].getMethodName(),
            // ManageError.builder().idTransaccion(headers.getIdTransaccion()));
            throw new Exception("Error de data. Por favor probar mas tarde.");
        }
    }

    @Override
    public ResponseMaterialDto getMaterialById(Long id) throws Exception {
        MaterialEntity material = materialRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Material no encontrado con el id : " +
                        id));
        try {
            return modelMapper.map(material, ResponseMaterialDto.class);

        } catch (Exception e) {
            // log.error(GlobalConstants.ERROR + " - " +
            // Thread.currentThread().getStackTrace()[1].getMethodName(),
            // ManageError.builder().idTransaccion(headers.getIdTransaccion()));
            throw new Exception("Error de data. Por favor probar mas tarde.");
        }
    }

}
