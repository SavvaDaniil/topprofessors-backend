package com.topprofessors.DTO.OnlineAuditoriumUserFile;

import org.springframework.web.multipart.MultipartFile;

public class OnlineAuditoriumUserFileNewFromUserDTO {

	private MultipartFile uploadedFile;

	public MultipartFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(MultipartFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
}
