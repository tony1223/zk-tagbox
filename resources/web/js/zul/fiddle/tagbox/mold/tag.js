function (out) {

	var zcls = this.getZclass(),
		uuid = this.uuid;

	out.push('<li ', this.domAttrs_(), '>',
			'<span id="',uuid,'-label" class="z-tag-label">',
				this._label,
			'</span>',
			'<a id="',uuid,'-close"  class="z-tag-close">x</a>',
		'</li>'
	);

}