package org.zkoss.fiddle.tagbox;

import java.lang.reflect.InvocationTargetException;

import org.zkoss.fiddle.tagbox.event.TagInputEvent;
import org.zkoss.lang.Classes;
import org.zkoss.lang.Objects;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.event.ListDataListener;
import org.zkoss.zul.impl.XulElement;

public class Tagbox extends XulElement {

	private class ListDataNotifyListener implements ListDataListener {

		private static final String ON_INIT_RENDER = "onInitRender";
		private Component _target;

		public ListDataNotifyListener(Component target) {
			_target = target;
		}

		public void notifyTarget() {
			if (_target.getAttribute(ATTR_ON_INIT_RENDER_POSTED) == null) {
				_target.setAttribute(ATTR_ON_INIT_RENDER_POSTED, Boolean.TRUE);
				Events.postEvent(ON_INIT_RENDER, _target, null);
			}
		}

		public void onChange(ListDataEvent event) {
			notifyTarget();
		}
	}

	private static final TagRenderer _defRend = new TagRenderer() {
		public Tag render(Tagbox box, Object data, int index) {
			return new Tag(Objects.toString(data));
		}
	};

	private static final String ATTR_ON_INIT_RENDER_POSTED = "org.zkoss.zul.onInitLaterPosted";

	private static final long serialVersionUID = 4763925276120091179L;

	static {
		addClientEvent(Tagbox.class, TagInputEvent.ON_TAG_INPUT, CE_IMPORTANT | CE_NON_DEFERRABLE);
	}

	private transient ListDataNotifyListener _dataListener;

	private boolean _insertable;

	private ListModel _model;

	private TagRenderer _render;

	private void evaluteRender(Component target, TagRenderer renderer, ListModel _model) {
		if (_model == null) {
			return;
		}
		getChildren().clear();
		for (int i = 0; i < _model.getSize(); ++i) {
			appendChild(renderer.render(this, _model.getElementAt(i), i));
		}
	}

	public Object getDefaultRenderer() {
		return _defRend;
	}

	public ListModel getModel(){
		return _model;
	}

	public TagRenderer getRender() {
		return _render;
	}

	public Object getRenderer() {
		return _render == null ? _defRend : _render;
	}

	/**
	 * The default zclass is "z-tagbox"
	 */
	public String getZclass() {
		return (this._zclass != null ? this._zclass : "z-tagbox");
	}

	public boolean isInsertable() {
		return _insertable;
	}

	public void onAddTag(TagInputEvent event){
		Tag t = new Tag(event.getTagName());
		t.setRemovable(true);
		appendChild(t);
	}

	public void onInitRender(Event e) {
		removeAttribute(ATTR_ON_INIT_RENDER_POSTED);
		getChildren().clear();
		evaluteRender(this, getRender(), _model);
		invalidate();
	}

	// super//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer) throws java.io.IOException {
		super.renderProperties(renderer);
		render(renderer,"insertable",_insertable);

	}

	public void service(AuRequest request, boolean everError) {
		if(TagInputEvent.ON_TAG_INPUT.equals(request.getCommand())){
			TagInputEvent evt = TagInputEvent.getAddTagEvent(request);
			Events.postEvent(evt);
		}else
		 	super.service(request, everError);
	}

	public void setInsertable(boolean insertable) {
		this._insertable = insertable;
	}

	public void setModel(ListModel model) {
		if (model != null) {
			if (_model != model) {
				// ListDataNotifyListener
				if (_model != null) {
					_model.removeListDataListener(_dataListener);
				}
				_model = model;
				_dataListener = new ListDataNotifyListener(this);
				_model.addListDataListener(_dataListener);
				_dataListener.notifyTarget();
			}
		} else if (_model != null) {
			_model.removeListDataListener(_dataListener);
			_model = null;
			getChildren().clear();
			invalidate();
		}
	}

	public void setRenderer(String clsnm) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		if (clsnm != null)
			setRenderer((TagRenderer) Classes.newInstanceByThread(clsnm));
	}
	public void setRenderer(TagRenderer render) {
		this._render = render;
	}
}
