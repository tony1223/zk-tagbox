package org.zkoss.fiddle.tagbox.event;


import org.zkoss.fiddle.tagbox.Tag;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

public class RemoveTagEvent extends Event {

	/**
	 *
	 */
	private static final long serialVersionUID = 804101433933981462L;

	public static final String ON_REMOVE_TAG = "onRemoveTag";

	private Tag tag;

	public RemoveTagEvent(String name, Component target, Object data) {
		super(name, target, data);
	}

	public RemoveTagEvent(String name, Component target) {
		super(name, target);
	}

	public RemoveTagEvent(String name) {
		super(name);
	}

	public static RemoveTagEvent getRemoveTagEvent(AuRequest request) {
		RemoveTagEvent evt = new RemoveTagEvent(
			ON_REMOVE_TAG,
			request.getComponent()
		);
		evt.setTag((Tag)request.getComponent());
		return evt;
	}

	public Tag getTag() {
		return tag;
	}


	public void setTag(Tag tag) {
		this.tag = tag;
	}
}
