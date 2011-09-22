package org.zkoss.fiddle;

import java.io.IOException;

import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.XulElement;

public class Tag extends XulElement {

	/**
	 *
	 */
	private static final long serialVersionUID = -6866817817288793708L;
	private String label;

	private boolean closable;

	//Not planing to implement this now.
	//private boolean editable;

	private boolean disable;

	public Tag() {

	}

	public Tag(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		if(!Objects.equals(this.label, label)){
			this.label = label;
			smartUpdate("label", label);
		}
	}

	protected void renderProperties(ContentRenderer renderer) throws IOException {
		super.renderProperties(renderer);

		render(renderer, "label", label);
	}

	/**
	 * The default zclass is "z-tagbox"
	 */
	public String getZclass() {
		return (this._zclass != null ? this._zclass : "z-tagbox");
	}
}
