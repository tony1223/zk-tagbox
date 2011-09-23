package org.zkoss.fiddle.tagbox.event;


import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;


public class AddTagEvent extends Event {
	public AddTagEvent(String name, Component target, Object data) {
		super(name, target, data);
	}

	public AddTagEvent(String name, Component target) {
		super(name, target);
	}

	public AddTagEvent(String name) {
		super(name);
	}


	private String tagName;
	public static final String ON_ADD_TAG = "onAddTag";

	
	public static AddTagEvent getAddTagEvent(AuRequest request) {
		String value = (String) request.getData().get("value");
		
		AddTagEvent evt = new AddTagEvent(
			ON_ADD_TAG,
			request.getComponent()		
		);
		evt.setTagName(value);
		return evt;
	}

	
	public String getTagName() {
		return tagName;
	}

	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
