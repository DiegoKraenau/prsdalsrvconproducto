package com.grupogloria.prsdalsrvconproducto.registration.service;

import java.util.List;
import java.util.Optional;

import com.grupogloria.prsdalsrvconproducto.registration.domain.RegistrationEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;

public interface RegistrationService {
	
	RegistrationEntity submitRegistration(RegistrationEntity registrationDAO) throws SqlException,Exception;
	
	List<RegistrationEntity> getAllVisitors() throws SqlException, Exception;
	
	Optional<RegistrationEntity> getVisitorById(long id) throws SqlException, Exception;

}
