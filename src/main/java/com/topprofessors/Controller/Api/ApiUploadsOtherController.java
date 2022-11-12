package com.topprofessors.Controller.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.topprofessors.Facade.UploadsOtherFacade;

@RestController
@RequestMapping("uploads_other")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApiUploadsOtherController {

	@Autowired
	UploadsOtherFacade uploadsOtherFacade;
	

	@GetMapping(value = "/{filename}")
	public byte[] getPoster(@PathVariable(value="filename") String filename) {
		return uploadsOtherFacade.getFile(filename);
	}
}
