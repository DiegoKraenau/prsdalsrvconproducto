package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Level;
import org.hibernate.exception.JDBCConnectionException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import com.google.common.util.concurrent.ExecutionError;
import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.ElkLogger;
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.PlanRequirementMaterialId;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.repository.MaterialRepository;
import com.grupogloria.prsdalsrvconproducto.registration.repository.PlanRequirementMaterialRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.PlanRequirementMaterialService;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.RequestPlanRequirementMaterialDto;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.ResponsePlanRequirementMaterialDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanRequirementMaterialServiceImpl implements PlanRequirementMaterialService {

    @Autowired
    private PlanRequirementMaterialRepository planRequirementMaterialRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlanRequirementMaterialEntity registerPlanRequirementMaterial(RequestPlanRequirementMaterialDto registerDto)
            throws SqlException, Exception {

        MaterialEntity material = materialRepository
                .findById(registerDto.getMaterialId())
                .orElseThrow(() -> new Exception("Material not found with id : " +
                        registerDto.getMaterialId()));

        if (planRequirementMaterialRepository
                .findById(PlanRequirementMaterialId.builder().material(registerDto.getMaterialId())
                        .date(registerDto.getDate()).build())
                .isPresent()) {
            throw new Exception("Plan Requirement Material exits");
        }
        // .orElseThrow(() -> new Exception("Plan Requirement exits"));

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(registerDto.getDate().getTime());

            PlanRequirementMaterialEntity planRequirementMaterialRegistered = planRequirementMaterialRepository
                    .save(PlanRequirementMaterialEntity.builder()
                            .material(material)
                            .date(registerDto.getDate())
                            .year(cal.get(Calendar.YEAR))
                            .month(String.valueOf(cal.get(Calendar.MONTH) + 1))
                            .amount(registerDto.getAmount())
                            .creationDate(registerDto.getCreationDate())
                            .updateDate(registerDto.getUpdateDate())
                            .userCreation(registerDto.getUserCreation())
                            .userUpdate(registerDto.getUserUpdate())
                            .teamCreation(registerDto.getTeamCreation())
                            .teamUpdate(registerDto.getTeamUpdate())
                            .canceledFlag(false)
                            .build());

            // ObjectMapper mapper = new ObjectMapper();
            // String json = mapper.writeValueAsString(registrationDAO);

            return planRequirementMaterialRegistered;

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Connection Falied Please Try Later");
        } catch (Exception e) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterials()
            throws SqlException, ExecutionError {

        try {
            List<PlanRequirementMaterialEntity> planRequirementMaterials = planRequirementMaterialRepository.findAll();

            return planRequirementMaterials
                    .stream()
                    .map(planRequirementMaterial -> {
                        ResponsePlanRequirementMaterialDto res = modelMapper.map(planRequirementMaterial,
                                ResponsePlanRequirementMaterialDto.class);

                        res.setProductFamily(
                                planRequirementMaterial.getMaterial().getMaterialCategory().getCodCatMaterial());
                        res.setProductName(planRequirementMaterial.getMaterial().getShortName());
                        res.setProductId(planRequirementMaterial.getMaterial().getId());

                        return res;
                    })
                    .collect(Collectors.toList());

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Connection Falied Please Try Later");
        } catch (Exception e) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
            throw new SqlException("Data Issue Please Try Later");
        }
    }

}
