package com.springboot.moneylion.featureaccessmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.moneylion.featureaccessmanagement.entity.EmployeeAccess;

public interface EmployeeAccessRepository extends JpaRepository<EmployeeAccess, Long> {

	EmployeeAccess findByEmailAndFeatureName(String email, String featureName);
	
}