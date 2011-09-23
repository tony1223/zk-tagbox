package org.zkoss.fiddle.tagbox.event;


import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;


public class TagInputEvent extends Event {
	/**
	 *
	 */
	private static final long serialVersionUID = 4089730952036165519L;
	public static final String ON_TAG_INPUT = "onTagInput";

	public TagInputEvent(String name, Component target, Object data) {
		super(name, target, data);
	}

	public TagInputEvent(String name, Component target) {
		super(name, target);
	}

	public TagInputEvent(String name) {
		super(name);
	}


	private String tagName;

	public static TagInputEvent getAddTagEvent(AuRequest request) {
		String value = (String) request.getData().get("value");

		TagInputEvent evt = new TagInputEvent(
			ON_TAG_INPUT,
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
