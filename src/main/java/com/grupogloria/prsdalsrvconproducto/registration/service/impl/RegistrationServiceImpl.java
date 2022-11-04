package com.grupogloria.prsdalsrvconproducto.registration.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grupogloria.prsdalsrvconproducto.registration.aop.logging.ElkLogger;
import com.grupogloria.prsdalsrvconproducto.registration.domain.RegistrationEntity;
import com.grupogloria.prsdalsrvconproducto.registration.exception.ItemNotFoundException;
import com.grupogloria.prsdalsrvconproducto.registration.exception.SqlException;
import com.grupogloria.prsdalsrvconproducto.registration.repository.RegistrationRepository;
import com.grupogloria.prsdalsrvconproducto.registration.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	RegistrationRepository registrationEntity;
	
	@Autowired
	private MessagePublishServicerImpl messagePublishService;

	@Override
	public RegistrationEntity submitRegistration(RegistrationEntity registrationDAO) throws SqlException,Exception {
		try {
			registrationDAO = registrationEntity.save(registrationDAO);
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(registrationDAO);			
			messagePublishService.send(json);
			return registrationDAO;
		}catch (CannotCreateTransactionException | JDBCConnectionException ex) {
			ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
			throw new SqlException("Connection Falied Please Try Later");
		}catch (Exception e) {
			ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<RegistrationEntity> getAllVisitors() throws SqlException {		
		try {
			List<RegistrationEntity> visitors = registrationEntity.findAll();
			return visitors;
		}catch (CannotCreateTransactionException | JDBCConnectionException ex) {
			ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
			throw new SqlException("Connection Falied Please Try Later");
		}catch (Exception e) {
			ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
			throw new SqlException("Data Issue Please Try Later");
		}
	}

	@Override
	public Optional<RegistrationEntity> getVisitorById(long id) throws SqlException {
		try {
			Optional<RegistrationEntity>  registrationDAOs = registrationEntity.findById(id);
			if(registrationDAOs != null) {
				return registrationDAOs;
			}else {
				throw new ItemNotFoundException("No Data found of Vistror with id  : " + id);
			}
		}catch (CannotCreateTransactionException | JDBCConnectionException ex) {
			ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(ex), this.getClass().getName(), ex);
			throw new SqlException("Connection Falied Please Try Later");
		}catch (Exception e) {
			ElkLogger.log(Level.ERROR, ElkLogger.getStackTrace(e), this.getClass().getName(), e);
			throw new SqlException("Data Issue Please Try Later");
		}
	}	

}
