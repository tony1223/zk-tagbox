zul.fiddle.tagbox.Tagbox = zk.$extends(zk.Widget, {
	breakKeyCodes: [ 13, 44 ],
	_insertable:false,
	$define: {
		insertable:function(){
			if(this.desktop){
				if(this._insertable){
					jq(this.$n("new")).show();
				}else{
					jq(this.$n("new")).hide();
				}
			}
		}
	},
	getInputNode: function(){
		return this.$n("new-input");
	},
	bind_: function () {
		this.$supers(zul.fiddle.tagbox.Tagbox,'bind_', arguments);

		var inp = jq(this.getInputNode()),wgt = this;
		inp.autoGrowInput({comfortZone: 15, minWidth: 15, maxWidth: 20000});

		//A example for domListen_ , REMEMBER to do domUnlisten in unbind_.
		//this.domListen_(this.$n("cave"), "onClick", "_doItemsClick");

		this.domListen_(inp[0], "onBlur", "_doClearInput");
		this.domListen_(inp[0], "onKeypress", "_doKeypress");
		this.domListen_(inp[0], "onKeydown", "_doKeyDown");

	},
	_doKeyDown: function(event){
		var code = event.keyCode > 0? event.keyCode : event.which;
		switch(code) {
			case 8: // BACKSPACE
				if($(this).val().length == 0) {
					// delete Last Tag
					var lastchild = this.lastChild;
					while(lastchild){
						if(lastchild.isRemovable()){
							lastchild.fireRemove_();
							break;
						}
						lastchild = lastchild.previousSibling;
					}
					event.stop();
					return false;
				}
				break;
			case 9: // TAB
				var inp = this.getInputNode();
				if(inp.value.length > 0 ) {
					this.fireAddTag_(inp.value);
					this._doClearInput();
					event.stop();
					return false;
				}
				break;
		}
		return true;
	},
	_doKeypress: function(event){
		var code = event.keyCode > 0? event.keyCode : event.which;
		if($.inArray(code, this.breakKeyCodes) > -1) {
			var inp = this.getInputNode();
			if(inp.value.length > 0 ) {
				this.fireAddTag_(inp.value);
				this._doClearInput();
			}
			event.stop();
			return false;
		}
		return true;
	},
	fireAddTag_: function(tagName){
		this.fire("onTagInput", {
			value: tagName
		});
	},
	insertChildHTML_: function (child, before, desktop) {
		var out = [];
		child.redraw(out);
		if (before) {
			jq(child.$n()).before(out.join(""));
		} else {
			jq(this.$n("new")).before(out.join(""));
		}
		child.bind(desktop);
	},
	_doClearInput: function(evt){
		this.getInputNode().value ="";
	},
	doClick_: function(evt){
		if(evt.domTarget == this.$n()){
			this.getInputNode().focus();
		}
	},
	unbind_: function () {

		jq("tester",this.$n()).remove(); //coming from auto grow plugin

		this.$supers(zul.fiddle.tagbox.Tagbox,'unbind_', arguments);
	},
	getZclass: function () {
		return this._zclass != null ? this._zclass: "z-tagbox";
	}
});
