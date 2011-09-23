package org.zkoss.fiddle.tagbox;

import java.io.IOException;

import org.zkoss.fiddle.tagbox.event.RemoveTagEvent;
import org.zkoss.lang.Objects;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.XulElement;

public class Tag extends XulElement {

	/**
	 *
	 */
	private static final long serialVersionUID = -6866817817288793708L;

	static {
		addClientEvent(Tag.class, RemoveTagEvent.ON_REMOVE_TAG, CE_IMPORTANT | CE_NON_DEFERRABLE);
	}

	private String label;

	private boolean removable = false;

	// Not planing to implement this now.
	// private boolean editable;

	// private boolean disable;

	public Tag() {

	}

	public Tag(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		if (!Objects.equals(this.label, label)) {
			this.label = label;
			smartUpdate("label", label);
		}
	}

	public void onRemoveTag(Event e) {
		this.detach();
	}

	public boolean isRemovable() {
		return removable;
	}

	/**
	 * Note:if you use model to handle the tag rendering in tagbox and enable the closable, 
	 * it's required always handle the onRemoveTag for the model by yourself.
	 * 
	 * Since we have no way to know how to update your listmodel to meet the state.
	 * @param removable
	 */
	public void setRemovable(boolean removable) {
		this.removable = removable;
	}

	protected void renderProperties(ContentRenderer renderer) throws IOException {
		super.renderProperties(renderer);

		render(renderer, "label", label);
		render(renderer, "removable", removable);
		
	}

	public void service(AuRequest request, boolean everError) {
		if (RemoveTagEvent.ON_REMOVE_TAG.equals(request.getCommand())) {
			RemoveTagEvent evt = RemoveTagEvent.getRemoveTagEvent(request);
			Events.postEvent(evt);
		} else
			super.service(request, everError);
	}

	/**
	 * The default zclass is "z-tag"
	 */
	public String getZclass() {
		return (this._zclass != null ? this._zclass : "z-tag");
	}

}
