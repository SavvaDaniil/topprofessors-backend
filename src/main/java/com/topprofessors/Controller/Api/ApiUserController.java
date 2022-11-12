package com.topprofessors.Controller.Api;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.topprofessors.DTO.User.UserAgreementDTO;
import com.topprofessors.DTO.User.UserForgetDTO;
import com.topprofessors.DTO.User.UserIdDTO;
import com.topprofessors.DTO.User.UserLoginDTO;
import com.topprofessors.DTO.User.UserProfileEditDTO;
import com.topprofessors.Facade.RegionFacade;
import com.topprofessors.Facade.UserFacade;
import com.topprofessors.Middleware.UserMiddleware;
import com.topprofessors.ViewModel.JsonAnswerStatus;
import com.topprofessors.ViewModel.Region.RegionLiteViewModel;
import com.topprofessors.ViewModel.User.UserLoginResultViewModel;
import com.topprofessors.ViewModel.User.UserProfileViewModel;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")//, allowCredentials = "true" (писяли, что обязательно)
public class ApiUserController {

    @Autowired
    private HttpServletRequest request;
    
	@Autowired
	UserFacade userFacade;
	
	@Autowired
	RegionFacade regionFacade;
    
    @Autowired
    private UserMiddleware userMiddleware;
	
    @GetMapping(value = "/imgdoc/get/{userFolder}/{imgDocTypeString}/{numberOfFile}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImgDoc(
    		@PathVariable(value="userFolder") String userFolder,
    		@PathVariable(value="imgDocTypeString") String imgDocTypeString,
    		@PathVariable(value="numberOfFile") int numberOfFile) {
    	System.out.println("ApiUserController getImgDoc imgDocTypeString: " + imgDocTypeString);
    	byte[] bitesOfFile = userFacade.getImgDocFile(userFolder, imgDocTypeString, numberOfFile);
    	if(bitesOfFile == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "file not found");
    	}
    	return bitesOfFile;
    }

    @PostMapping(value = "/imgdoc/delete/{imgDocTypeString}/{numberOfFile}")
    public JsonAnswerStatus deleteImgDocByUser(
    		@PathVariable(value="imgDocTypeString") String imgDocTypeString,
    		@PathVariable(value="numberOfFile") int numberOfFile) {
    	return userFacade.deleteImgDocFile(userMiddleware.getCurrentUserId(), imgDocTypeString, numberOfFile);
    }
    
    @PostMapping(value = "/imgdoc/upload/{imgDocTypeString}")
    public JsonAnswerStatus updateImgDocByUser(@PathVariable(value="imgDocTypeString") String imgDocTypeString, 
    		@RequestParam("uploadedFile") MultipartFile uploadedFile) {
    	return userFacade.uploadImgDocFile(userMiddleware.getCurrentUserId(), imgDocTypeString, uploadedFile);
    }
    
	@PostMapping("/imgdoc/list_all")
	public JsonAnswerStatus imgDocByUserListAll() {
		return new JsonAnswerStatus("success", null, userFacade.getAllImgDocFilesOfUser(userMiddleware.getCurrentUserId()));
	}
    
    /*
    @GetMapping(value = "/photo/get", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] photoGet(HttpServletRequest request, 
            HttpServletResponse response) throws IOException {
    	
    	String fileName = "photo.jpg";
    	

        String uploadsDir = "uploads";
        File filePathDest = new File(uploadsDir + "/user/1");
    	//String dataDirectory = request.getServletContext().getRealPath("/uploads/user/1");
        File destFile = new File(filePathDest.getAbsolutePath(), "photo.jpg");
        
        Path file = Paths.get(filePathDest.getAbsolutePath(), fileName);
        if (Files.exists(file)) 
        {

        	Path path = Paths.get(destFile.getAbsolutePath());
      
            // Converting the file into a byte array
            // using Files.readAllBytes() method
            byte[] arr = Files.readAllBytes(path);
            return arr;
        } else {
        	System.out.println("File not found");
        }
        
        return null;
	}
    
	@PostMapping("/photo/save")
	public JsonAnswerStatus updateProfile(@RequestParam("photoFile") MultipartFile photoFile) {
		return userFacade.photoUpload(request, photoFile, userMiddleware.getCurrentUserId());
	}
	*/

	@PostMapping("/agreement/update")
	public JsonAnswerStatus updateAgreement(@RequestBody UserAgreementDTO userAgreementDTO) {
		return userFacade.updateAgreement(userMiddleware.getCurrentUserId(), userAgreementDTO.isNewValue());
	}

	@PostMapping("/profile/save")
	//@RequestMapping(value = "/profile/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserLoginResultViewModel updateProfile(@RequestBody UserProfileEditDTO userProfileEditDTO) {
		return userFacade.updateProfile(userMiddleware.getCurrentUserId(), userProfileEditDTO);
	}

	@RequestMapping("/profile/get")
	public JsonAnswerStatus profile() {
		UserProfileViewModel userLoginResultViewModel = userFacade.getProfile(userMiddleware.getCurrentUserId());
		List<RegionLiteViewModel> regionLiteViewModels = regionFacade.listAllLite();
		userLoginResultViewModel.setRegionLiteViewModels(regionLiteViewModels);
		return (userLoginResultViewModel != null
			? new JsonAnswerStatus("success", null, userLoginResultViewModel)
			: new JsonAnswerStatus("error", null)
		);
	}
	
	
	@PostMapping("/login")
	public UserLoginResultViewModel login(@RequestBody UserLoginDTO userLoginDTO) {
		if(!userLoginDTO.isValid())return new UserLoginResultViewModel("error", "no_data");
		return userFacade.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
	}

	@PostMapping("/forget")
	public JsonAnswerStatus forget(@RequestBody UserForgetDTO userForgetDTO) {
		return userFacade.forget(userForgetDTO.getStep(), userForgetDTO.getUsername(), userForgetDTO.getForgetId(), userForgetDTO.getCode());
	}
	
	@RequestMapping("/secret")
	public JsonAnswerStatus secret() {
		return new JsonAnswerStatus("success");
	}
	
	
	@RequestMapping("/check_base_exist")
	public JsonAnswerStatus checkBaseExist() {
		return userFacade.add("user1", "123");
	}
	
	@RequestMapping("/check_done_all")
	public JsonAnswerStatus checkDoneAll(@RequestBody UserIdDTO userIdDTO) {
		return userFacade.checkCoursesForDoneByUserId(userIdDTO.getUserId());
	}
	
	@RequestMapping("/check_all_users_for_done_any")
	public JsonAnswerStatus checkAllUsersForDoneAny() {
		return userFacade.checkAllUsersForDoneAnyCourses();
	}
}