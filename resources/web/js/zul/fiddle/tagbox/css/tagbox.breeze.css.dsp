<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>

/*
* Referenced from http://tagedit.webwork-albrecht.de/css/jquery.tagedit.css
*/
.z-tagbox {margin: 0; padding: 5px 5px 0 5px; border: 1px solid #c6c6c6; overflow: auto; font-size: 11px; min-height: 25px; }
.z-tagbox li.z-tag,.z-tagbox li.z-tag-new {list-style-type: none; float: left; margin: 0 5px 5px 0; padding: 0; }
/* New Item input */
.z-tagbox li.z-tag-new input {border: 0; height: 100%; padding: 2px 5px; width: 15px; background: #fff;}
.z-tagbox li.z-tag-new input:focus {outline: none;}
.z-tagbox li.z-tag-new input.tagedit-input-disabled {display: none;}
/* Item that is put to the List */
.z-tagbox li.z-tag {background: #DEE7F8 url(${c:encodeURL('~./fiddle/img/tagbox/disc.png')}) 4px center no-repeat; border: 1px solid #CAD8F3; padding: 2px 0 2px 13px; -moz-border-radius: 8px 4px 4px 8px; -webkit-border-radius: 8px 4px 4px 8px; border-radius: 8px 4px 4px 8px;}
.z-tagbox li.z-tag:hover {background-color: #D6E4FF;}
.z-tagbox li.z-tag a.z-tag-close,
.z-tagbox li.z-tag a.tagedit-break,
.z-tagbox li.z-tag a.tagedit-delete,
.z-tagbox li.z-tag a.tagedit-save {font-weight: bold; text-indent: -2000px; display: inline-block; width: 15px; height: 100%; cursor: pointer; border-left: 1px dotted #7E9DD6; margin: 0 2px 0 5px; background: url(${c:encodeURL('~./fiddle/img/tagbox/cross.png')}) center center no-repeat;}
.z-tagbox li.z-tag a.tagedit-save {background-image: url(${c:encodeURL('~./fiddle/img/tagbox/tick.png')}); margin-right: 0;}
.z-tagbox li.z-tag a.tagedit-break {background-image: url(${c:encodeURL('~./fiddle/img/tagbox/undo.png')}); margin-right: 0;}
.z-tagbox li.z-tag a.tagedit-delete {background-image: url(${c:encodeURL('~./fiddle/img/tagbox/delete.png')}); margin: 0 5px 0 2px; background-position: right center;}
/* Items that are edited */
.z-tagbox li.z-tag-edit input.tagedit-edit-input {border: 0; background: transparent; font-size: 11px; color: #666; padding: 0;}
.z-tagbox li.z-tag-edit input:focus {outline: none;}
.z-tagbox li.z-tag-edit a.tagedit-break {border: 0; margin-left: 0;}
.z-tagbox li.z-tag-edit a.z-tag-close,
.z-tagbox li.z-tag-edit span {display: none;}
/* Item is marked as disabled */
.z-tagbox li.z-tag-deleted,
.z-tagbox li.z-tag-deleted:hover {padding-right: 5px; background-color: #FFD6D6; border-color: #FFB7B7; border-style: dashed; }
.z-tagbox li.z-tag-deleted span {text-decoration: line-through;}
.z-tagbox li.z-tag-deleted a.z-tag-close {margin-right: 0;}