zul.fiddle.tagbox.Tagbox = zk.$extends(zk.Widget, {
	breakKeyCodes: [ 13, 44 ],
	$define: {
	},
	bind_: function () {
		this.$supers(zul.fiddle.tagbox.Tagbox,'bind_', arguments);

		var inp = jq(this.$n("new-input")),wgt = this;
		inp.autoGrowInput({comfortZone: 15, minWidth: 15, maxWidth: 20000});

		//A example for domListen_ , REMEMBER to do domUnlisten in unbind_.
		//this.domListen_(this.$n("cave"), "onClick", "_doItemsClick");

		this.domListen_(inp[0], "onBlur", "_doClearInput");
		this.domListen_(inp[0], "onKeypress", "_doKeypress");
	},
	_doKeypress: function(event){
		var code = event.keyCode > 0? event.keyCode : event.which;
		if($.inArray(code, this.breakKeyCodes) > -1) {
			if(this.$n("new-input").value.length > 0 ) {
				jq.alert("hi");
				this.fire("onAddTag",{
					value:this.value
				});
				this._doClearInput();
			}
			event.stop();
			return false;
		}
		return true;
	},
	_doClearInput: function(evt){
		this.$n("new-input").value ="";
	},
	doClick_: function(evt){
		if(evt.domTarget == this.$n()){
			this.$n("new-input").focus();
		}
	},
	unbind_: function () {

		jq("tester",this.$n()).remove();

		this.$supers(zul.fiddle.tagbox.Tagbox,'unbind_', arguments);
	},
	getZclass: function () {
		return this._zclass != null ? this._zclass: "z-tagbox";
	}
});
