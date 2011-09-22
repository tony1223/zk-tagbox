zul.fiddle.tagbox.Tag = zk.$extends(zk.Widget, {
	$define: {
		label:function(val){
			if(this.desktop){
				jq(this.$n("label")).html(val);
			}
		}
	},
	bind_: function () {
		this.$supers(zul.fiddle.tagbox.Tag ,'bind_', arguments);

	},
	unbind_: function () {
		this.$supers(zul.fiddle.tagbox.Tag ,'unbind_', arguments);
	},

	getZclass: function () {
		return this._zclass != null ? this._zclass: "z-tag";
	}
});
