var Icon = {
	root :				"root.gif",
	folderopen:			"folderopen.gif",
	folderclosed:		"folderclosed.gif",
	leaf:				"file.gif",
	Rminus:				"Rminus.gif",
	Rplus:				"Rplus.gif",
	minusbottom:		"Lminus.gif",
	plusbottom:			"Lplus.gif",
	minus:				"Tminus.gif",
	plus:				"Tplus.gif",
	join:				"T.gif",
	joinbottom:			"L.gif",
	blank:				"blank.gif",
	line:				"I.gif"
}

var Tree = {
	counter:	0,
	getId:		function(o){
		var id = this.counter;
		this[id] = o;
		this.counter++;
		return id;
	},
	toggle:		function(id, nid){ this[id].toggle(nid); },
	select:		function(id, nid){ this[id].setSelected(nid); }
};

function TreeView() {
	this.id = Tree.getId(this);
	this.target = "_self";
	this.closeOtherLeafs = false;
	this.root = null;
	this.Nodes = { "0" : { "ID" : 0, "ParentID" : -1, "text" : null, "href" : null, "image" : null, "childNodes": new Array(),"isLoad": false } };
};

var tv = TreeView.prototype;
tv.setTarget = function(v) {
	this.target = v;
};


tv.closeOtherLeafs = function(v) {
	this.closeOtherLeafs = v;
};

tv.setImagePath = function(sPath) {
	for(o in Icon){
		tmp = sPath + Icon[o];
		Icon[o] = new Image();
		Icon[o].src = tmp;
	}
};

tv.add = function(iD,ParentiD,sText,sHref,sTarget,sImage) {
	this.Nodes[iD] = { "ID" : iD, "ParentID" : ParentiD, "text" : sText, "href" : sHref, "target" : sTarget, "image" : sImage , "childNodes": new Array() , "open" : false ,"isLast" : true, "isLoad": false};
	var ch = this.getNode(ParentiD).childNodes;
	ch[ch.length] = this.Nodes[iD];
	if (ch.length>1) { ch[ch.length-2].isLast = false;}
	if (ParentiD == 0) this.root = this.Nodes[iD];
};

tv.getNode = function(sKey) {
	if (typeof this.Nodes[sKey] != "undefined") {
		return this.Nodes[sKey];
	}
	return null;
};

tv.getNodeText = function(sKey) {
	if (typeof this.Nodes[sKey] != "undefined") {
		return this.Nodes[sKey].text;
	}
	return null;
};

tv.getParentNode = function(ID) {
	var key = this.getNode(ID).ParentID;
	if(this.getNode(key) == null) return null;
	return this.getNode(key);
};

tv.hasChildNodes = function(sKey) {
	return this.getNode(sKey).childNodes.length > 0;
};

tv.getRoot = function() {
	return this.root;
};

tv.drawNode = function(ID) {
	var html = "";
	var node = this.getNode(ID);
	var rootid = this.getRoot().ID;
	var hc = this.hasChildNodes(ID);
	html += '<div class="TreeNode" nowrap>'+this.drawIndent(ID)+
			'<img id="icon'+ID+'" src="'+( node.image ? node.image : (hc ? Icon.folderclosed.src : Icon.leaf.src))+'" align="absmiddle">'+
			'<a id="node' + ID + '" class="normal" href="' + node.href + '" target="'+(node.target ? node.target : this.target)+'" onclick="Tree.select(' + this.id + ',' + ID + ')">'+ node.text +'</a></div>\n'
	if (hc) {
		var io = ID ==  rootid;
		node.open = io;
		html += ('<div id="container'+ID+'" style="display:'+(io ? '' : 'none')+'">\n');
		html += '</div>\n';
	}
	return html;
};

tv.addNode = function(ID) {
	var node = this.getNode(ID);
	var html = [];
	for (var i = 0; i<node.childNodes.length; i++)
		html[i] = this.drawNode(node.childNodes[i].ID);
	return html.join('');
};

tv.drawIndent = function(ID) {
	var s = ''
	var r = this.getRoot();
	var ir = r.ID == ID;
	var hc = this.hasChildNodes(ID);
	var iL = this.getNode(ID).isLast;
	if (this.getParentNode(ID) != null && !ir)
		s += ((hc ? '<a href="javascript:void Tree.toggle(' + this.id + ',' + ID + ')" target="_self">':'')+'<img id="handler'+ID+'" src="'+ (this.hasChildNodes(ID) ? (iL ? Icon.plusbottom.src : Icon.plus.src) : (iL ? Icon.joinbottom.src : Icon.join.src)) + '" align="absmiddle">'+(hc?'</a>':''));
	if (ir) return s;
	var p = this.getParentNode(ID);
	while (p != r){
		s = ('<img src="'+(p.isLast ? Icon.blank.src : Icon.line.src) + '" align="absmiddle">') + s;
		p = this.getParentNode(p.ID);
	}
	return s;
};

tv.setSelected = function(ID) {
	if(this.selectedID) { document.getElementById("node" + this.selectedID).className = "normal";}
	this.selectedID = ID;
	var node = document.getElementById("node" + ID);
	node.className = "normal selected";
	node.focus();
};

tv.toggle = function(ID) {
	if (this.hasChildNodes(ID)) {
		if (this.getNode(ID).open) {
			this.collapse(ID);
		} else {
			this.expand(ID);
		}
	}
};

tv.expand = function(ID) {
	var oNode = this.getNode(ID);
	var handler = document.getElementById("handler" + ID);
	var container = document.getElementById("container" + ID);
	var folder = document.getElementById("icon" + ID);
	if (handler) handler.src = oNode.isLast ? Icon.minusbottom.src : Icon.minus.src;
	if (container) container.style.display = '';
	if (this.hasChildNodes(ID)) {
		if(oNode.image == null) folder.src = Icon.folderopen.src;
	}
	oNode.open = true;
	if(this.getRoot().ID != ID && this.closeOtherLeafs) {
		var ch = this.getParentNode(ID).childNodes;
		for (var i=0; i<ch.length; i++) {
			if(oNode != ch[i] && this.hasChildNodes(ch[i].ID) && ch[i].open) this.collapse(ch[i].ID);
		}
	}
	if (!oNode.isLoad) {
		container.innerHTML = this.addNode(ID);
		oNode.isLoad = true;
	}
};

tv.collapse = function(ID) {
	var oNode = this.getNode(ID);
	if (!oNode.isLoad) return;
	var handler = document.getElementById("handler"+ID);
	var container = document.getElementById("container"+ID);
	var folder = document.getElementById("icon" + ID);
	if (handler) handler.src = oNode.isLast ? Icon.plusbottom.src : Icon.plus.src;
	if (container) container.style.display = 'none';
	if (this.hasChildNodes(ID)) {
		if(oNode.image == null) folder.src = Icon.folderclosed.src;
	}
	oNode.open = false;
};

tv.expandAll = function() { this.All(1); };

tv.collapseAll = function() { this.All(0); };

tv.All = function(v) {
	for (var i in this.Nodes) {
		var id = this.Nodes[i].ID, pid = this.Nodes[i].ParentID;
		if (id == 0 || pid == 0) continue;
		if (this.hasChildNodes(id)) {
			if (v) {
				this.expand(id);
			} else {
				this.collapse(id);
			}
		}
	}
};

tv.write = function() {
	document.write(this.addNode(0));
	var r = this.getRoot();
	if (r) {
		this.expand(r.ID);
	}
}