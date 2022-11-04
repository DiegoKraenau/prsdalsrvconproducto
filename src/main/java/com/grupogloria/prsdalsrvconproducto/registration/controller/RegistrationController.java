package com.grupogloria.prsdalsrvconproducto.registration.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.LogMethodCall;
import com.grupogloria.prsdalsrvconproducto.registration.domain.RegistrationEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.service.RegistrationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.swagger.annotations.Api;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin 
@Api(tags = "Gloria Registration API")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
    @LogMethodCall
	@PostMapping("/visita")
	public RegistrationEntity submitRegistration(@Valid @RequestBody RegistrationEntity registrationDAO) throws SqlException,Exception  {
		return registrationService.submitRegistration(registrationDAO);
	}
	
    @LogMethodCall
	@GetMapping("/visita/findAll")
	public List<RegistrationEntity> getAllVisitors() throws SqlException,Exception {
		return registrationService.getAllVisitors();
	}

    @LogMethodCall
	@GetMapping("/visita/findById/{id}")
	public RegistrationEntity getVisitorById(@PathVariable long id) throws SqlException,Exception  {
		Optional<RegistrationEntity>  registrationDAOs = registrationService.getVisitorById(id);
		return registrationDAOs.get();
	}
}
