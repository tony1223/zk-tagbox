function (out) {

	var zcls = this.getZclass(),
		uuid = this.uuid;
	out.push('<ul ', this.domAttrs_(), '>');

	for (var w = this.firstChild; w; w = w.nextSibling)
		w.redraw(out);

	out.push('<li id="',uuid,'-new" ',
			(this._insertable ?  '' : ' style="display:none;" '),
			' class="z-tag-new">',
			'<input id="',uuid,'-new-input" type="text" value=""  autocomplete="off">',
		'</li>'
	);

	out.push('</ul>');

}