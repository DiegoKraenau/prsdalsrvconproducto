package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.Calendar;
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
import com.grupogloria.prsdalsrvconproducto.registration.domain.MaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.PlanRequirementMaterialEntity;
import com.grupogloria.prsdalsrvconproducto.registration.domain.UnitMeasureEntity;
import com.grupogloria.prsdalsrvconproducto.registration.repository.CenterRepository;
import com.grupogloria.prsdalsrvconproducto.registration.repository.MaterialRepository;
import com.grupogloria.prsdalsrvconproducto.registration.repository.PlanRequirementMaterialRepository;
import com.grupogloria.prsdalsrvconproducto.registration.repository.UnitMeasureRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.PlanRequirementMaterialService;
import com.grupogloria.prsdalsrvconproducto.registration.util.ManageError;
import com.grupogloria.prsdalsrvconproducto.registration.util.Util;
import com.grupogloria.prsdalsrvconproducto.registration.util.dtos.RequestEditPlanRequirementMaterialDto;
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
        @Autowired
        ObjectMapper objMapper;

        @Override
        public PlanRequirementMaterialEntity registerPlanRequirementMaterial(
                        RequestPlanRequirementMaterialDto registerDto, HeaderRequest headers)
                        throws Exception {

                log.info(
                                GlobalConstants.HEADER + " - "
                                                + Thread.currentThread().getStackTrace()[1].getMethodName() + " - "
                                                + headers.getIdTransaccion() + ": {}",
                                objMapper.writeValueAsString(headers));

                JSONObject params = new JSONObject();
                params.put("registerDto", registerDto);

                log.info(
                                GlobalConstants.PARAMS + " - "
                                                + Thread.currentThread().getStackTrace()[1].getMethodName() + " - "
                                                + headers.getIdTransaccion() + ": {}",
                                params);

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
                                .findOneByFilter(registerDto.getMaterialId(), registerDto.getFecha(),
                                                registerDto.getCentroId(), registerDto.getUnidadMedidaId())
                                .isPresent()) {
                        throw new Exception("Plan de Requerimiento de Material existe");
                }

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

                        log.info(
                                        GlobalConstants.RESPONSE + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName() +
                                                        " - " + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(planRequirementMaterialRegistered));

                        return planRequirementMaterialRegistered;

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
        public List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterials(HeaderRequest headers)
                        throws Exception {

                try {
                        log.info(
                                        GlobalConstants.HEADER + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                        + " - "
                                                        + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(headers));

                        List<PlanRequirementMaterialEntity> planRequirementMaterials = planRequirementMaterialRepository
                                        .findAll();

                        List<ResponsePlanRequirementMaterialDto> response = planRequirementMaterials
                                        .stream()
                                        .map(planRequirementMaterial -> {
                                                ResponsePlanRequirementMaterialDto res = modelMapper.map(
                                                                planRequirementMaterial,
                                                                ResponsePlanRequirementMaterialDto.class);

                                                res.setFamiliaProducto(
                                                                planRequirementMaterial.getMaterial()
                                                                                .getCategoriaMaterial()
                                                                                .getCodCatMaterial());
                                                res.setNombreProducto(
                                                                planRequirementMaterial.getMaterial().getNombreCorto());
                                                res.setProductoId(planRequirementMaterial.getMaterial().getId());
                                                res.setCentroId(planRequirementMaterial.getCentro().getIdCentro());
                                                res.setNombreCentro(planRequirementMaterial.getCentro().getCentro());
                                                res.setUnidadMedida(planRequirementMaterial.getUnidadMedida()
                                                                .getUnidadMedida());

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

        @Override
        public List<ResponsePlanRequirementMaterialDto> getAllPlanRequirementMaterialsByFilters(String fechaInicio,
                        String fechaFin, String centroId, HeaderRequest headers)
                        throws Exception {
                try {
                        log.info(
                                        GlobalConstants.HEADER + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                        + " - "
                                                        + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(headers));

                        JSONObject params = new JSONObject();
                        params.put("fechaInicio", fechaInicio);
                        params.put("fechaFin", fechaFin);
                        params.put("centroId", centroId);

                        log.info(
                                        GlobalConstants.PARAMS + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                        + " - "
                                                        + headers.getIdTransaccion() + ": {}",
                                        params);

                        List<PlanRequirementMaterialEntity> planRequirementMaterials = planRequirementMaterialRepository
                                        .findAllByFilters(fechaInicio, fechaFin, centroId);

                        List<ResponsePlanRequirementMaterialDto> response = planRequirementMaterials
                                        .stream()
                                        .map(planRequirementMaterial -> {
                                                ResponsePlanRequirementMaterialDto res = modelMapper.map(
                                                                planRequirementMaterial,
                                                                ResponsePlanRequirementMaterialDto.class);

                                                res.setFamiliaProducto(
                                                                planRequirementMaterial.getMaterial()
                                                                                .getCategoriaMaterial()
                                                                                .getCodCatMaterial());
                                                res.setNombreProducto(
                                                                planRequirementMaterial.getMaterial().getNombreCorto());
                                                res.setProductoId(planRequirementMaterial.getMaterial().getId());
                                                res.setCentroId(planRequirementMaterial.getCentro().getIdCentro());
                                                res.setNombreCentro(planRequirementMaterial.getCentro().getCentro());
                                                res.setUnidadMedida(planRequirementMaterial.getUnidadMedida()
                                                                .getUnidadMedida());

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

        @Override
        public ResponsePlanRequirementMaterialDto updatePlanRequirementMaterial(Long id,
                        RequestEditPlanRequirementMaterialDto updateDto, HeaderRequest headers)
                        throws Exception {
                try {
                        log.info(
                                        GlobalConstants.HEADER + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                        + " - "
                                                        + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(headers));

                        JSONObject params = new JSONObject();
                        params.put("updateDto", new JSONObject(updateDto));

                        log.info(
                                        GlobalConstants.PARAMS + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                        + " - "
                                                        + headers.getIdTransaccion() + ": {}",
                                        params);

                        PlanRequirementMaterialEntity plantRequirementMaterial = planRequirementMaterialRepository
                                        .findById(id)
                                        .orElseThrow(() -> new Exception(
                                                        "Plan Requerimiento Material no encontrado."));

                        Util.copyNonNullProperties(updateDto, plantRequirementMaterial);
                        plantRequirementMaterial = planRequirementMaterialRepository
                                        .save(plantRequirementMaterial);

                        ResponsePlanRequirementMaterialDto res = modelMapper.map(plantRequirementMaterial,
                                        ResponsePlanRequirementMaterialDto.class);

                        res.setFamiliaProducto(
                                        plantRequirementMaterial.getMaterial().getCategoriaMaterial()
                                                        .getCodCatMaterial());
                        res.setNombreProducto(plantRequirementMaterial.getMaterial().getNombreCorto());
                        res.setProductoId(plantRequirementMaterial.getMaterial().getId());
                        res.setCentroId(plantRequirementMaterial.getCentro().getIdCentro());
                        res.setNombreCentro(plantRequirementMaterial.getCentro().getCentro());
                        res.setUnidadMedida(plantRequirementMaterial.getUnidadMedida().getUnidadMedida());

                        log.info(
                                        GlobalConstants.RESPONSE + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName() +
                                                        " - " + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(res));

                        return res;

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
        public ResponsePlanRequirementMaterialDto getPlanRequierementMaterialById(Long id, HeaderRequest headers)
                        throws Exception {
                log.info(
                                GlobalConstants.HEADER + " - "
                                                + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                + " - "
                                                + headers.getIdTransaccion() + ": {}",
                                objMapper.writeValueAsString(headers));

                JSONObject params = new JSONObject();
                params.put("id", new JSONObject(id));

                log.info(
                                GlobalConstants.PARAMS + " - "
                                                + Thread.currentThread().getStackTrace()[1].getMethodName()
                                                + " - "
                                                + headers.getIdTransaccion() + ": {}",
                                params);

                PlanRequirementMaterialEntity plantRequirementMaterial = planRequirementMaterialRepository
                                .findById(id)
                                .orElseThrow(() -> new Exception(
                                                "Plan Requerimiento Material no encontrado."));
                try {

                        ResponsePlanRequirementMaterialDto res = modelMapper.map(plantRequirementMaterial,
                                        ResponsePlanRequirementMaterialDto.class);

                        res.setFamiliaProducto(
                                        plantRequirementMaterial.getMaterial()
                                                        .getCategoriaMaterial()
                                                        .getCodCatMaterial());
                        res.setNombreProducto(
                                        plantRequirementMaterial.getMaterial().getNombreCorto());
                        res.setProductoId(plantRequirementMaterial.getMaterial().getId());
                        res.setCentroId(plantRequirementMaterial.getCentro().getIdCentro());
                        res.setNombreCentro(plantRequirementMaterial.getCentro().getCentro());
                        res.setUnidadMedida(plantRequirementMaterial.getUnidadMedida()
                                        .getUnidadMedida());

                        log.info(
                                        GlobalConstants.RESPONSE + " - "
                                                        + Thread.currentThread().getStackTrace()[1].getMethodName() +
                                                        " - " + headers.getIdTransaccion() + ": {}",
                                        objMapper.writeValueAsString(res));

                        return res;

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
