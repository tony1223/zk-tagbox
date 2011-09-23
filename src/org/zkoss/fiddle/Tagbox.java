package org.zkoss.fiddle;

import java.lang.reflect.InvocationTargetException;

import org.zkoss.fiddle.tagbox.event.TagInputEvent;
import org.zkoss.lang.Classes;
import org.zkoss.lang.Objects;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.event.ListDataListener;
import org.zkoss.zul.impl.XulElement;

public class Tagbox extends XulElement {

	private static final long serialVersionUID = 4763925276120091179L;

	private static final String ATTR_ON_INIT_RENDER_POSTED = "org.zkoss.zul.onInitLaterPosted";

	static {
		addClientEvent(Tagbox.class, TagInputEvent.ON_TAG_INPUT, 0);
	}

	private TagRenderer _render;

	private ListModel _model;

	private transient ListDataNotifyListener _dataListener;

	public TagRenderer getRender() {
		return _render;
	}

	public void setRenderer(TagRenderer render) {
		this._render = render;
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

	public void onInitRender(Event e) {
		removeAttribute(ATTR_ON_INIT_RENDER_POSTED);
		getChildren().clear();
		evaluteRender(this, getRender(), _model);
		invalidate();
	}

	// super//
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer) throws java.io.IOException {
		super.renderProperties(renderer);
	}

	public Object getRenderer() {
		return _render == null ? _defRend : _render;
	}

	public void setRenderer(String clsnm) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		if (clsnm != null)
			setRenderer((TagRenderer) Classes.newInstanceByThread(clsnm));
	}

	private static final TagRenderer _defRend = new TagRenderer() {
		public Tag render(Tagbox box, Object data, int index) {
			return new Tag(Objects.toString(data));
		}
	};

	public Object getDefaultRenderer() {
		return _defRend;
	}

	private void evaluteRender(Component target, TagRenderer renderer, ListModel _model) {
		if (_model == null) {
			return;
		}
		getChildren().clear();
		for (int i = 0; i < _model.getSize(); ++i) {
			appendChild(renderer.render(this, _model.getElementAt(i), i));
		}
	}

	private class ListDataNotifyListener implements ListDataListener {

		private Component _target;

		public ListDataNotifyListener(Component target) {
			_target = target;
		}

		public void notifyTarget() {
			if (_target.getAttribute(ATTR_ON_INIT_RENDER_POSTED) == null) {
				_target.setAttribute(ATTR_ON_INIT_RENDER_POSTED, Boolean.TRUE);
				Events.postEvent("onInitRender", _target, null);
			}
		}

		public void onChange(ListDataEvent event) {
			notifyTarget();
		}
	}

	/**
	 * The default zclass is "z-tagbox"
	 */
	public String getZclass() {
		return (this._zclass != null ? this._zclass : "z-tagbox");
	}
}
