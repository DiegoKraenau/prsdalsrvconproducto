package com.grupogloria.prsdalsrvconproducto.registration.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "VISIT") 
@JsonInclude(Include.NON_EMPTY)
public class RegistrationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss" )
	private Timestamp dateTimeEntry;	
	
	private String personalIdentifier;	
	
	private long providerCompanyId;
	
	private String visitorNames;	
	
	private String visitorLastName;
	
	private long groupCompanyId;
	
	private long employeeId;
	
	private String reason;
}
