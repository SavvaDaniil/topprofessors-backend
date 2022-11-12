package com.topprofessors.DTO.Message;

import com.topprofessors.Interface.ValidatedObject;

public class MessageChatNewFromUserDTO implements ValidatedObject {

	private int parentMessageId;
	private String messageContent;
	
	public int getParentMessageId() {
		return parentMessageId;
	}
	public void setParentMessageId(int parentMessageId) {
		this.parentMessageId = parentMessageId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public boolean isValid() {
		return parentMessageId != 0 && messageContent != null;
	}
	
	
}
