package com.springboot.moneylion.featureaccessmanagement.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springboot.moneylion.featureaccessmanagement.dto.EmployeeAccessDTO;
import com.springboot.moneylion.featureaccessmanagement.entity.EmployeeAccess;
import com.springboot.moneylion.featureaccessmanagement.repository.EmployeeAccessRepository;

@Component
public class EmployeeAccessService {
	@Autowired
	EmployeeAccessRepository employeeAccessRepository;

	public Long saveData(EmployeeAccessDTO employeeDTO) {
		if(employeeDTO.getFeatureName()!=null && employeeDTO.getEmail()!=null &&
				employeeDTO.getEnable()!=null && !(employeeDTO.getFeatureName().isEmpty() || employeeDTO.getEmail().isEmpty())) {
			employeeDTO.setFeatureName(employeeDTO.getFeatureName().toUpperCase().replaceAll(" ", ""));
			employeeDTO.setEmail(employeeDTO.getEmail().toLowerCase().replaceAll(" ", ""));
	
			
			 
			String regex = "^[A-Za-z0-9+_.-]+@(.+)$";		 
			Pattern pattern = Pattern.compile(regex);
			 
			
		    Matcher matcher = pattern.matcher(employeeDTO.getEmail());
		    if(matcher.matches()) {
		    	ModelMapper modelMapper = new ModelMapper();
		    	EmployeeAccess empAccessToBeSaved = modelMapper.map(employeeDTO, EmployeeAccess.class);
				EmployeeAccess existingAccess = employeeAccessRepository.findByEmailAndFeatureName(employeeDTO.getEmail(),employeeDTO.getFeatureName());
		        if (existingAccess != null) {
		        	empAccessToBeSaved.setId(existingAccess.getId());
		        }
		      
		        EmployeeAccess savedItem = employeeAccessRepository.save(empAccessToBeSaved);
		        return savedItem.getId();
		    }
		}
	    return -1L;
	}

	public boolean getData(String email, String feature) {
		EmployeeAccess existingAccess = employeeAccessRepository.findByEmailAndFeatureName(email.toLowerCase().replaceAll(" ", ""),feature.toUpperCase().replaceAll(" ", ""));
        if (existingAccess == null) {
        	return false;
        }
      
        
        return existingAccess.getEnable();
	}
		
		
		
} 