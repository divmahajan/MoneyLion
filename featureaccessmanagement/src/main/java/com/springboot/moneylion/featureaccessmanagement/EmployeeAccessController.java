package com.springboot.moneylion.featureaccessmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.moneylion.featureaccessmanagement.dto.EmployeeAccessDTO;
import com.springboot.moneylion.featureaccessmanagement.dto.ResponseDTO;
import com.springboot.moneylion.featureaccessmanagement.service.EmployeeAccessService;

@RestController
public class EmployeeAccessController {

	//Auto wiring
	@Autowired
	private EmployeeAccessService service;


	@RequestMapping(value = "/feature", method = RequestMethod.POST)
	public ResponseEntity<String> saveEmpFeatureAccess(@RequestBody EmployeeAccessDTO employeeDTO) {
			
		Long response =service.saveData(employeeDTO);
		if(response==-1L) {
		        return new ResponseEntity<>("Wrong Input.", HttpStatus.BAD_REQUEST);
		}else if (response>=0L){
		        return new ResponseEntity<>("Updated Successfully.", HttpStatus.OK);
		}
		return new ResponseEntity<>("Not Modified.", HttpStatus.NOT_MODIFIED);

		
	}
	
	@RequestMapping(value = "/feature", method = RequestMethod.GET)
	public ResponseDTO getEmpFeatureAccess(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "featureName", required = true) String feature){
		
		ResponseDTO response = new ResponseDTO();
		response.setCanAccess(service.getData(email,feature));
		return response;
	}
}

