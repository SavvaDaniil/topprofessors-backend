package com.topprofessors.ViewModel.Message;

import java.util.ArrayList;

public class PanelListOfMessageParentPreviews {

	private ArrayList<MessageParentPreviewViewModel> messageParentPreviewViewModels;

	public ArrayList<MessageParentPreviewViewModel> getMessageParentPreviewViewModels() {
		return messageParentPreviewViewModels;
	}

	public void setMessageParentPreviewViewModels(ArrayList<MessageParentPreviewViewModel> messageParentPreviewViewModels) {
		this.messageParentPreviewViewModels = messageParentPreviewViewModels;
	}

	public PanelListOfMessageParentPreviews(ArrayList<MessageParentPreviewViewModel> messageParentPreviewViewModels) {
		super();
		this.messageParentPreviewViewModels = messageParentPreviewViewModels;
	}
	
}
