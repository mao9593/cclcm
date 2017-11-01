/*----------------------------------------------------------------------------\
|                               XLoadTree 1.11                                |
|-----------------------------------------------------------------------------|
|                         Created by Erik Arvidsson                           |
|                  (http://webfx.eae.net/contact.html#erik)                   |
|                      For WebFX (http://webfx.eae.net/)                      |
|-----------------------------------------------------------------------------|
| An extension to xTree that allows sub trees to be loaded at runtime by      |
| reading XML files from the server. Works with IE5+ and Mozilla 1.0+         |
|-----------------------------------------------------------------------------|
|                   Copyright (c) 1999 - 2002 Erik Arvidsson                  |
|-----------------------------------------------------------------------------|
| This software is provided "as is", without warranty of any kind, express or |
| implied, including  but not limited  to the warranties of  merchantability, |
| fitness for a particular purpose and noninfringement. In no event shall the |
| authors or  copyright  holders be  liable for any claim,  damages or  other |
| liability, whether  in an  action of  contract, tort  or otherwise, arising |
| from,  out of  or in  connection with  the software or  the  use  or  other |
| dealings in the software.                                                   |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| This  software is  available under the  three different licenses  mentioned |
| below.  To use this software you must chose, and qualify, for one of those. |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| The WebFX Non-Commercial License          http://webfx.eae.net/license.html |
| Permits  anyone the right to use the  software in a  non-commercial context |
| free of charge.                                                             |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| The WebFX Commercial license           http://webfx.eae.net/commercial.html |
| Permits the  license holder the right to use  the software in a  commercial |
| context. Such license must be specifically obtained, however it's valid for |
| any number of  implementations of the licensed software.                    |
| - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - |
| GPL - The GNU General Public License    http://www.gnu.org/licenses/gpl.txt |
| Permits anyone the right to use and modify the software without limitations |
| as long as proper  credits are given  and the original  and modified source |
| code are included. Requires  that the final product, software derivate from |
| the original  source or any  software  utilizing a GPL  component, such  as |
| this, is also licensed under the GPL license.                               |
|-----------------------------------------------------------------------------|
| 2001-09-27 | Original Version Posted.                                       |
| 2002-01-19 | Added some simple error handling and string templates for      |
|            | reporting the errors.                                          |
| 2002-01-28 | Fixed loading issues in IE50 and IE55 that made the tree load  |
|            | twice.                                                         |
| 2002-10-10 | (1.1) Added reload method that reloads the XML file from the   |
|            | server.                                                        |
/ 2003-05-06 | Added support for target attribute                             |
|-----------------------------------------------------------------------------|
| Dependencies: xtree.js - original xtree library                             |
|               xtree.css - simple css styling of xtree                       |
|               xmlextras.js - provides xml http objects and xml document     |
|                              objects                                        |
|-----------------------------------------------------------------------------|
| Created 2001-09-27 | All changes are in the log above. | Updated 2003-05-06 |
\----------------------------------------------------------------------------*/
var xmlHttp = false;
var json="";


webFXTreeConfig.loadingText = "Loading...";
webFXTreeConfig.loadErrorTextTemplate = "Error loading \"%1%\"";
webFXTreeConfig.emptyErrorTextTemplate = "Error \"%1%\" does not contain any tree items";

/*
 * WebFXLoadTree class
 */

function WebFXLoadTree(sText, sId, sXmlSrc, sAction, sBehavior, sIcon, sOpenIcon, form) {
	// call super
	this.WebFXTree = WebFXTree;
	this.WebFXTree(sText, sId, sAction, sBehavior, sIcon, sOpenIcon);

	// setup default property values
	this.src = sXmlSrc;
	this.loading = false;
	this.loaded = false;
	this.errorText = "";

	// check start state and load if open
	if (this.open){
		_startLoadXmlTree(this.src, this, form);
	}
	else {
		// and create loading item if not
		this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
		this.add(this._loadingItem);
	}
}

WebFXLoadTree.prototype = new WebFXTree;

// override the expand method to load the xml file
WebFXLoadTree.prototype._webfxtree_expand = WebFXTree.prototype.expand;
WebFXLoadTree.prototype.expand = function() {
	if (!this.loaded && !this.loading) {
		// load
		_startLoadXmlTree(this.src, this);
	}
	this._webfxtree_expand();
};

/*
 * WebFXLoadTreeItem class
 */
// ����sId
function WebFXLoadTreeItem(sText, sId, sXmlSrc, sAction, eParent, sIcon, sOpenIcon) {
	// call super
	this.WebFXTreeItem = WebFXTreeItem;
	this.WebFXTreeItem(sText, sId, sAction, eParent, sIcon, sOpenIcon);

	// setup default property values
	this.src = sXmlSrc;
	this.loading = false;
	this.loaded = false;
	this.errorText = "";

	// check start state and load if open
	if (this.open){
		_startLoadXmlTree(this.src, this);
		}
	else {
		// and create loading item if not
		this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
		this.add(this._loadingItem);
	}
}

WebFXLoadTreeItem.prototype = new WebFXTreeItem;

// override the expand method to load the xml file
WebFXLoadTreeItem.prototype._webfxtreeitem_expand = WebFXTreeItem.prototype.expand;
WebFXLoadTreeItem.prototype.expand = function() {
	if (!this.loaded && !this.loading) {
		// load
		_startLoadXmlTree(this.src, this);
	}
	this._webfxtreeitem_expand();
};

// reloads the src file if already loaded
WebFXLoadTree.prototype.reload =
WebFXLoadTreeItem.prototype.reload = function () {
	// if loading do nothing
	if (this.loaded) {
		var open = this.open;
		// remove
		while (this.childNodes.length > 0)
			this.childNodes[this.childNodes.length - 1].remove();

		this.loaded = false;

		this._loadingItem = new WebFXTreeItem(webFXTreeConfig.loadingText);
		this.add(this._loadingItem);

		if (open)
			this.expand();
	}
	else if (this.open && !this.loading)
		_startLoadXmlTree(this.src, this);
};

/*
 * Helper functions
 */

// creates the xmlhttp object and starts the load of the xml document

function _startLoadXmlTree(sSrc, jsNode, form) {
	if (jsNode.loading || jsNode.loaded)
		return;
	jsNode.loading = true;
	//var xmlHttp = XmlHttp.create();
	try {
		  xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
		  try {
		    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		  } catch (e2) {
		    xmlHttp = false;
		  }
		}
		if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		  xmlHttp = new XMLHttpRequest();
		}
	try{
/*		xmlHttp.open("POST", sSrc, false);	// async
		xmlHttp.setRequestHeader("Content-type","text/xml");
		// xmlHttp.setRequestHeader("Content-Type","gb2312");
		// modified by renmingfei 2013-07-08
        xmlHttp.setRequestHeader("Content-Type","UTF-8");
		xmlHttp.setRequestHeader("Depth","1");
//		xmlHttp.onreadystatechange = getAjaxResult;
		xmlHttp.send(serialize(form));
		*/
		xmlHttp.open("POST", sSrc, false);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
		xmlHttp.setRequestHeader("Content-Type","UTF-8");
		xmlHttp.setRequestHeader("Depth","1");
		xmlHttp.send(serialize(form));
		if (xmlHttp!=null && xmlHttp.readyState == 4 ){
			var tempDoc=new ActiveXObject("MSXML2.DOMDocument");
			tempDoc.async = false ;
			tempDoc.validateOnParse = true ;
			if(xmlHttp.status==200){
				if(tempDoc.xml=="")
				{
					var returnXml=xmlHttp.responseText;
					tempDoc.loadXML(returnXml);
				}
				_xmlFileLoaded(tempDoc, jsNode);
			}
		}
	}catch(ex){
		alert(ex.getMessage());
	}
}


// Converts an xml tree to a js tree. See article about xml tree format
// ���Ӷ�sid�Ľ���
function _xmlTreeToJsTree(oNode) {
	// retreive attributes
	var text = oNode.getAttribute("text");
	var action = oNode.getAttribute("action");
	var parent = null;
	var icon = oNode.getAttribute("icon");
	var openIcon = oNode.getAttribute("openIcon");
	var src = "";
	if(oNode.getAttribute("src")!=null){
		src = METAR_WEB_ROOT + oNode.getAttribute("src");
	}
	var target = oNode.getAttribute("target");
	var sid = oNode.getAttribute("sid");
	// create jsNode
	var jsNode;
	if (src != null && src != "")
		jsNode = new WebFXLoadTreeItem(text,sid, src, action, parent, icon, openIcon);
	else
		jsNode = new WebFXTreeItem(text, sid, action, parent, icon, openIcon);

	jsNode.selected=(oNode.getAttribute("selected")=="true");
	jsNode.nodeType=(oNode.getAttribute("nodeType"));

	if (target != "")
		jsNode.target = target;

	// go through childNOdes
	var cs = oNode.childNodes;
	var l = cs.length;
	for (var i = 0; i < l; i++) {
		if (cs[i].tagName == "tree")
			jsNode.add( _xmlTreeToJsTree(cs[i]), true );
	}
	return jsNode;
}

// Inserts an xml document as a subtree to the provided node
function _xmlFileLoaded(oXmlDoc, jsParentNode) {
	if (jsParentNode.loaded)
		return;
	var bIndent = false;
	var bAnyChildren = false;
	jsParentNode.loaded = true;
	jsParentNode.loading = false;

	// check that the load of the xml file went well

	if( oXmlDoc == null || oXmlDoc.documentElement == null) {
		jsParentNode.errorText = parseTemplateString(webFXTreeConfig.loadErrorTextTemplate,
							jsParentNode.src);
	}
	else {
		// there is one extra level of tree elements
		var root = oXmlDoc.documentElement;

		// loop through all tree children
		var cs = root.childNodes;
		var l = cs.length;
		for (var i = 0; i < l; i++) {
			if (cs[i].tagName == "tree") {
				bAnyChildren = true;
				bIndent = true;
				jsParentNode.add( _xmlTreeToJsTree(cs[i]), true);
			}
		}

		// if no children we got an error
		if (!bAnyChildren)
			jsParentNode.errorText = parseTemplateString(webFXTreeConfig.emptyErrorTextTemplate,
										jsParentNode.src);
	}
	// remove dummy
	if (jsParentNode._loadingItem != null) {
		jsParentNode._loadingItem.remove();
		bIndent = true;
	}
	if (bIndent) {
		// indent now that all items are added
		jsParentNode.indent();
	}
	// show error in status bar
	if (jsParentNode.errorText != "")
		window.status = jsParentNode.errorText;
}

// parses a string and replaces %n% with argument nr n
function parseTemplateString(sTemplate) {
	var args = arguments;
	var s = sTemplate;

	s = s.replace(/\%\%/g, "%");

	for (var i = 1; i < args.length; i++)
		s = s.replace( new RegExp("\%" + i + "\%", "g"), args[i] )

	return s;
}

/* 表单序列化函数，用于使用ajax提交表单的情况 */
function serialize(form){
	var parts = [],
	field = null,
	i,
	len,
	j,
	optLen,
	option,
	optValue;
	for(i=0,len=form.elements.length;i<len;i++){
		field = form.elements[i];
//		alert(field.type + " " + field.name + "  " + field.value);
		switch(field.type){
			case "select-one":
			case "select-multiple":
				if(field.name.length){
					for(j=0,optLen=field.options.length;j<optLen;j++){
						option=field.options[j];
						if(option.selected){
							optValue = "";
							if(option.hasAttribute){
								optValue = (option.hasAttribute("value")?option.value:option.text);
							}else{
								optValue = (option.attributes["value"].specified?option.value:option.text);
							}
							parts.push(encodeURIComponent(field.name) + "=" + encodeURIComponent(optValue));
						}
					}
				}
				break;
			case undefined:   // 字段集
			case "file":      // 文件输入
			case "submit":    // 提交按钮
			case "reset":     // 重置按钮
			case "button":    // 自定义按钮
				break;
			case "radio":     // 单选按钮
			case "checkbox":  // 复选框
				if(!field.checked){
					break;
				}
				/* 执行默认操作 */
				
			default:
				// 不包含没有名字的表单字段
				if(field.name.length){
					parts.push(encodeURIComponent(field.name) + "=" + encodeURIComponent(field.value));
				}
		}
	}
	return parts.join("&");
}
