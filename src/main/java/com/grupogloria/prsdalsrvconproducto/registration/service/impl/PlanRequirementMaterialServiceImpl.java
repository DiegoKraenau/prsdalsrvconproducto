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
import com.grupogloria.prsdalsrvconproducto.registration.domain.CenterEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.UnitMeasureEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.helpers.PlanRequirementMaterialId;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.repository.CenterRepository;
import com.grupogloria.prsdalsrvconproducto.registration.repository.MaterialRepository;
import com.grupogloria.prsdalsrvconproducto.registration.repository.PlanRequirementMaterialRepository;
import com.grupogloria.prsdalsrvconproducto.registration.repository.UnitMeasureRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.PlanRequirementMaterialService;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.EditPlanRequirementMaterialDto;
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
    private CenterRepository centerRepository;

    @Autowired
    private UnitMeasureRepository unitMeasureRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlanRequirementMaterialEntity registerPlanRequirementMaterial(RequestPlanRequirementMaterialDto registerDto)
            throws SqlException, Exception {

        MaterialEntity material = materialRepository
                .findById(registerDto.getMaterialId())
                .orElseThrow(() -> new Exception("Material no encontrado con el id : " +
                        registerDto.getMaterialId()));

        CenterEntity center = centerRepository
                .findById(registerDto.getCentroId())
                .orElseThrow(() -> new Exception("Centro no encontrado con el id : " +
                        registerDto.getCentroId()));

        UnitMeasureEntity unitMeasure = unitMeasureRepository
                .findById(registerDto.getUnidadMedidaId())
                .orElseThrow(() -> new Exception("Unidad de medida no encontrado con el id : " +
                        registerDto.getUnidadMedidaId()));

        if (planRequirementMaterialRepository
                .findById(PlanRequirementMaterialId.builder().material(registerDto.getMaterialId())
                        .fecha(registerDto.getFecha()).centro(center.getIdCentro())
                        .unidadMedida(unitMeasure.getIdUnidadMedida()).build())
                .isPresent()) {
            throw new Exception("Plan de Requerimiento de Material existe");
        }
        // .orElseThrow(() -> new Exception("Plan Requirement exits"));

        try {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(registerDto.getFecha().getTime());

            PlanRequirementMaterialEntity planRequirementMaterialRegistered = planRequirementMaterialRepository
                    .save(PlanRequirementMaterialEntity.builder()
                            .material(material)
                            .fecha(registerDto.getFecha())
                            .anio(cal.get(Calendar.YEAR))
                            .codMes(String.valueOf(cal.get(Calendar.MONTH) + 1))
                            .cantidad(registerDto.getCantidad())
                            .fecCreacion(registerDto.getFecCreacion())
                            .fecActualizacion(registerDto.getFecActualizacion())
                            .usuCreacion(registerDto.getUsuCreacion())
                            .usuActualizacion(registerDto.getUsuActualizacion())
                            .equipoCreacion(registerDto.getEquipoCreacion())
                            .equipoActualizacion(registerDto.getEquipoActualizacion())
                            .flgAnulado(false)
                            .centro(center)
                            .unidadMedida(unitMeasure)
                            .build());

            // ObjectMapper mapper = new ObjectMapper();
            // String json = mapper.writeValueAsString(registrationDAO);

            return planRequirementMaterialRegistered;

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Conexion fallida. Por favor probar mas tarde.");
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

                        res.setFamiliaProducto(
                                planRequirementMaterial.getMaterial().getCategoriaMaterial().getCodCatMaterial());
                        res.setNombreProducto(planRequirementMaterial.getMaterial().getNombreCorto());
                        res.setProductoId(planRequirementMaterial.getMaterial().getId());
                        res.setCentroId(planRequirementMaterial.getCentro().getIdCentro());
                        res.setNombreCentro(planRequirementMaterial.getCentro().getCentro());
                        res.setUnidadMedida(planRequirementMaterial.getUnidadMedida().getUnidadMedida());

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

    @Override
    public List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterialsByFilters(String fechaInicio,
            String fechaFin, String centroId)
            throws SqlException, Exception {
        try {
            List<PlanRequirementMaterialEntity> planRequirementMaterials = planRequirementMaterialRepository
                    .findAllByFilters(fechaInicio, fechaFin, centroId);

            return planRequirementMaterials
                    .stream()
                    .map(planRequirementMaterial -> {
                        ResponsePlanRequirementMaterialDto res = modelMapper.map(planRequirementMaterial,
                                ResponsePlanRequirementMaterialDto.class);

                        res.setFamiliaProducto(
                                planRequirementMaterial.getMaterial().getCategoriaMaterial().getCodCatMaterial());
                        res.setNombreProducto(planRequirementMaterial.getMaterial().getNombreCorto());
                        res.setProductoId(planRequirementMaterial.getMaterial().getId());
                        res.setCentroId(planRequirementMaterial.getCentro().getIdCentro());
                        res.setNombreCentro(planRequirementMaterial.getCentro().getCentro());
                        res.setUnidadMedida(planRequirementMaterial.getUnidadMedida().getUnidadMedida());

                        return res;
                    })
                    .collect(Collectors.toList());

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Conexion fallida. Por favor probar  mas tarde.");
        } catch (Exception e) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
            throw new SqlException("Error de data. Por favor probar mas tarde.");
        }
    }

    @Override
    public ResponsePlanRequirementMaterialDto updatePlanRequirementMaterial(
            EditPlanRequirementMaterialDto updateDto)
            throws SqlException, Exception {
        try {
            PlanRequirementMaterialEntity plantRequirementMaterial = planRequirementMaterialRepository
                    .getOne(PlanRequirementMaterialId.builder()
                            .centro(updateDto.getCentro())
                            .fecha(updateDto.getFecha())
                            .material(updateDto.getMaterial())
                            .unidadMedida(updateDto.getUnidadMedida())
                            .build());
            Util.copyNonNullProperties(updateDto, plantRequirementMaterial);
            // plantRequirementMaterial.setCantidad(updateDto.getCantidad());
            // plantRequirementMaterial.setFlgAnulado(updateDto.getFlgAnulado());
            // plantRequirementMaterial.setFecActualizacion(updateDto.getFecActualizacion());
            // plantRequirementMaterial.setUsuActualizacion(updateDto.getUsuActualizacion());
            // plantRequirementMaterial.setEquipoActualizacion(updateDto.getEquipoActualizacion());
            plantRequirementMaterial = planRequirementMaterialRepository
                    .save(plantRequirementMaterial);

            ResponsePlanRequirementMaterialDto res = modelMapper.map(plantRequirementMaterial,
                    ResponsePlanRequirementMaterialDto.class);

            res.setFamiliaProducto(
                    plantRequirementMaterial.getMaterial().getCategoriaMaterial().getCodCatMaterial());
            res.setNombreProducto(plantRequirementMaterial.getMaterial().getNombreCorto());
            res.setProductoId(plantRequirementMaterial.getMaterial().getId());
            res.setCentroId(plantRequirementMaterial.getCentro().getIdCentro());
            res.setNombreCentro(plantRequirementMaterial.getCentro().getCentro());
            res.setUnidadMedida(plantRequirementMaterial.getUnidadMedida().getUnidadMedida());

            return res;

        } catch (CannotCreateTransactionException | JDBCConnectionException ex) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
            throw new SqlException("Conexion fallida. Por favor probar mas tarde.");
        } catch (Exception e) {
            ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
            throw new SqlException("Error de data. Por favor probar mas tarde.");
        }
    }

}
