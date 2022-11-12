package com.topprofessors.Facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.topprofessors.Entity.Region;
import com.topprofessors.Service.RegionService;
import com.topprofessors.ViewModel.Region.RegionLiteViewModel;

@Component
public class RegionFacade {

	@Autowired
	RegionService regionService;
	
	public List<RegionLiteViewModel> listAllLite(){
		List<Region> regions = regionService.listAll();
		if(regions == null)return null;
		
		List<RegionLiteViewModel> regionLiteViewModels = new ArrayList<RegionLiteViewModel>();
		for(Region region : regions) {
			regionLiteViewModels.add(new RegionLiteViewModel(region.getId(), region.getName()));
		}
		
		return regionLiteViewModels;
	}
}
