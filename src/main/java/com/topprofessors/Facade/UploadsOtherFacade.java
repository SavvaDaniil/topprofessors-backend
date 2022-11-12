package com.topprofessors.Facade;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class UploadsOtherFacade {

	private final String UPLOAD_FOLDER_PATH = "uploads/other";
	
	public byte[] getFile(String fileName) {
		//String fileName = "poster.jpg";
		File filePathDest = new File(this.UPLOAD_FOLDER_PATH + "/");
        File destFile = new File(filePathDest.getAbsolutePath(), fileName);
        Path file = Paths.get(filePathDest.getAbsolutePath(), fileName);
        if (Files.exists(file)) 
        {
        	try {
	        	Path path = Paths.get(destFile.getAbsolutePath());
	            byte[] arr = Files.readAllBytes(path);
	            return arr;
        	} catch(Exception ex) {
            	System.out.println("UploadsOtherFacade getFile Exception: " + ex.getMessage());
        	}
        } else {
        	System.out.println("UploadsOtherFacade getFile file not found");
        }
        return null;
	}
}
