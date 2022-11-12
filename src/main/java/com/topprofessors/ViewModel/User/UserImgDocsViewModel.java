package com.topprofessors.ViewModel.User;

import java.util.ArrayList;

public class UserImgDocsViewModel {

	private ArrayList<UserImgDocViewModel> diplomUserImgDocs;
	private ArrayList<UserImgDocViewModel> supplementUserImgDocs;
	private ArrayList<UserImgDocViewModel> statementUserImgDocs;
	private ArrayList<UserImgDocViewModel> changeSecondnameUserImgDocs;
	public ArrayList<UserImgDocViewModel> getDiplomUserImgDocs() {
		return diplomUserImgDocs;
	}
	public void setDiplomUserImgDocs(ArrayList<UserImgDocViewModel> diplomUserImgDocs) {
		this.diplomUserImgDocs = diplomUserImgDocs;
	}

	public ArrayList<UserImgDocViewModel> getSupplementUserImgDocs() {
		return supplementUserImgDocs;
	}
	public void setSupplementUserImgDocs(ArrayList<UserImgDocViewModel> supplementUserImgDocs) {
		this.supplementUserImgDocs = supplementUserImgDocs;
	}
	public ArrayList<UserImgDocViewModel> getStatementUserImgDocs() {
		return statementUserImgDocs;
	}
	public void setStatementUserImgDocs(ArrayList<UserImgDocViewModel> statementUserImgDocs) {
		this.statementUserImgDocs = statementUserImgDocs;
	}
	public ArrayList<UserImgDocViewModel> getChangeSecondnameUserImgDocs() {
		return changeSecondnameUserImgDocs;
	}
	public void setChangeSecondnameUserImgDocs(ArrayList<UserImgDocViewModel> changeSecondnameUserImgDocs) {
		this.changeSecondnameUserImgDocs = changeSecondnameUserImgDocs;
	}
	public UserImgDocsViewModel(ArrayList<UserImgDocViewModel> diplomUserImgDocs,
			ArrayList<UserImgDocViewModel> supplementUserImgDocs, ArrayList<UserImgDocViewModel> statementUserImgDocs,
			ArrayList<UserImgDocViewModel> changeSecondnameUserImgDocs) {
		super();
		this.diplomUserImgDocs = diplomUserImgDocs;
		this.supplementUserImgDocs = supplementUserImgDocs;
		this.statementUserImgDocs = statementUserImgDocs;
		this.changeSecondnameUserImgDocs = changeSecondnameUserImgDocs;
	}

	
	
	
	
}
