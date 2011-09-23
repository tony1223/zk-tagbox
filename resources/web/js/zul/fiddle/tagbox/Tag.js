zul.fiddle.tagbox.Tag = zk.$extends(zk.Widget, {
	_removable: false,
	$define: {
		label: function(val) {
			if (this.desktop) {
				jq(this.$n("label")).html(val);
			}
		},
		removable: function(){
			this._updateRemovable();
		}
	},
	_updateRemovable: function() {
		if (this.desktop) {
			if (this._removable) {
				jq(this.$n("remove")).show();
			} else {
				jq(this.$n("remove")).hide();
			}
		}
	},
	bind_: function() {
		this.$supers(zul.fiddle.tagbox.Tag, 'bind_', arguments);
		this._updateRemovable();
		this.domListen_(this.$n("remove"), "onClick", "_doRemove");
	},
	_doRemove: function(evt) {
		this.fireRemove_();
		evt.stop();
	},
	unbind_: function() {
		this.$supers(zul.fiddle.tagbox.Tag, 'unbind_', arguments);
	},
	fireRemove_: function() {
		if (this._removable) {
			this.fire("onRemoveTag");
		}
	},
	getZclass: function() {
		return this._zclass != null ? this._zclass : "z-tag";
	}
});
