var ua		= window.navigator.userAgent;
var opera	= /opera [56789]|opera\/[56789]/i.test(ua);
var ie		= !opera && /msie [56789]/i.test(ua);
var moz		= !opera && /mozilla\/[56789]/i.test(ua);

if (moz) {
	Event.prototype.__defineSetter__("returnValue", function (b) {
		if (!b) this.preventDefault();
	});

	Event.prototype.__defineSetter__("cancelBubble", function (b) {
		if (b) this.stopPropagation();
	});

	Event.prototype.__defineGetter__("srcElement", function () {
		var node = this.target;
		while (node.nodeType != 1) node = node.parentNode;
		return node;
	});

	Event.prototype.__defineGetter__("fromElement", function () {
		var node;
		if (this.type == "mouseover")
			node = this.relatedTarget;
		else if (this.type == "mouseout")
			node = this.target;
		if (!node) return;
		while (node.nodeType != 1) node = node.parentNode;
		return node;
	});

	Event.prototype.__defineGetter__("toElement", function () {
		var node;
		if (this.type == "mouseout")
			node = this.relatedTarget;
		else if (this.type == "mouseover")
			node = this.target;
		if (!node) return;
		while (node.nodeType != 1) node = node.parentNode;
		return node;
	});

	Event.prototype.__defineGetter__("offsetX", function () {
		return this.layerX;
	});

	Event.prototype.__defineGetter__("offsetY", function () {
		return this.layerY;
	});

	window.attachEvent =
	HTMLDocument.prototype.attachEvent = 
	HTMLElement.prototype.attachEvent = function (sType, fHandler) {
		var shortTypeName = sType.replace(/on/, "");
		this.addEventListener(shortTypeName, fHandler, false);
	};

	window.detachEvent = 
	HTMLDocument.prototype.detachEvent = 
	HTMLElement.prototype.detachEvent = function (sType, fHandler) {
		var shortTypeName = sType.replace(/on/, "");
		if (typeof fHandler == "function")
			this.removeEventListener(shortTypeName, fHandler, false);
		else
			this.removeEventListener(shortTypeName, fHandler, true);
	};

	function convertTextToHTML(s) {
		s = s.replace(/\&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\n/g, "<BR>");
		while (/\s\s/.test(s))
			s = s.replace(/\s\s/, "&nbsp; ");
		return s.replace(/\s/g, " ");
	}

	HTMLElement.prototype.insertAdjacentHTML = function (sWhere, sHTML) {
		var df;
		var r = this.ownerDocument.createRange();	
		switch (String(sWhere).toLowerCase()) {
			case "beforebegin":
				r.setStartBefore(this);
				df = r.createContextualFragment(sHTML);
				this.parentNode.insertBefore(df, this);
				break;
				
			case "afterbegin":
				r.selectNodeContents(this);
				r.collapse(true);
				df = r.createContextualFragment(sHTML);
				this.insertBefore(df, this.firstChild);
				break;
				
			case "beforeend":
				r.selectNodeContents(this);
				r.collapse(false);
				df = r.createContextualFragment(sHTML);
				this.appendChild(df);
				break;
				
			case "afterend":
				r.setStartAfter(this);
				df = r.createContextualFragment(sHTML);
				this.parentNode.insertBefore(df, this.nextSibling);
				break;
		}	
	};

	HTMLElement.prototype.__defineSetter__("outerHTML", function (sHTML) {
	   var r = this.ownerDocument.createRange();
	   r.setStartBefore(this);
	   var df = r.createContextualFragment(sHTML);
	   this.parentNode.replaceChild(df, this);
	   
	   return sHTML;
	});

	HTMLElement.prototype.__defineGetter__("canHaveChildren", function () {
		switch (this.tagName) {
			case "AREA":
			case "BASE":
			case "BASEFONT":
			case "COL":
			case "FRAME":
			case "HR":
			case "IMG":
			case "BR":
			case "INPUT":
			case "ISINDEX":
			case "LINK":
			case "META":
			case "PARAM":
				return false;
		}
		return true;
	});

	HTMLElement.prototype.__defineGetter__("outerHTML", function () {
		var attr, attrs = this.attributes;
		var str = "<" + this.tagName;
		for (var i = 0; i < attrs.length; i++) {
			attr = attrs[i];
			if (attr.specified)
				str += " " + attr.name + '="' + attr.value + '"';
		}
		if (!this.canHaveChildren)
			return str + ">";
		
		return str + ">" + this.innerHTML + "</" + this.tagName + ">";
	});


	HTMLElement.prototype.__defineSetter__("innerText", function (sText) {
		this.innerHTML = convertTextToHTML(sText);
		return sText;		
	});

	var tmpGet;
	HTMLElement.prototype.__defineGetter__("innerText", tmpGet = function () {
		var r = this.ownerDocument.createRange();
		r.selectNodeContents(this);
		return r.toString();
	});

	XMLDocument.prototype.__defineSetter__("onreadystatechange",function(f) {
		if(this._onreadystatechange)
			this.removeEventListener("load",this._onreadystatechange,false);
		this._onreadystatechange=f;
		if(f) this.addEventListener("load",f,false);
		return f;
	});
	
	XMLDocument.prototype.__defineGetter__("onreadystatechange",function(){
		return this._onreadystatechange;
	});
	
	XMLDocument.prototype.loadXML=function(s){
		var doc2=(new DOMParser).parseFromString(s,"text/xml");
		while(this.hasChildNodes())
			this.removeChild(this.lastChild);
		var cs=doc2.childNodes;
		var l=cs.length;
		for(var i=0;i<l;i++)
		this.appendChild(this.importNode(cs[i],true));
	};

	Node.prototype.__defineGetter__("xml",function() {
		return(new XMLSerializer).serializeToString(this);
	});

	Node.prototype.selectNodes=function(sExpr) {
		var doc=this.nodeType==9?this:this.ownerDocument;
		var nsRes=doc.createNSResolver(this.nodeType==9?this.documentElement:this);
		var xpRes=doc.evaluate(sExpr,this,nsRes,5,null);
		var res=[];
		var item;
		while((item=xpRes.iterateNext()))
			res.push(item);
		return res;
	};
	
	Node.prototype.selectSingleNode=function(sExpr){
		var doc=this.nodeType==9?this:this.ownerDocument;
		var nsRes=doc.createNSResolver(this.nodeType==9?this.documentElement:this);
		var xpRes=doc.evaluate(sExpr,this,nsRes,9,null);
		return xpRes.singleNodeValue;
	};
	
	Node.prototype.transformNode=function(oXsltNode){
		var doc=this.nodeType==9?this:this.ownerDocument;
		var processor=new XSLTProcessor();
		processor.importStylesheet(oXsltNode);
		var df=processor.transformToFragment(this,doc);
		return df.xml;
	};
}

