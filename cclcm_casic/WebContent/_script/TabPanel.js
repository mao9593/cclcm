/*
本文件用于处理多页标签，只做了简单封装，功能有待于完善。共包含两个JS对象：TabPage多页标签标签页、TabPanel多页标签面板。
 */

//多页标签标签页
function TabPage(_tabObj, _pageObj ){
	this.tabObj = _tabObj ;
	this.pageObj = _pageObj ;
	this.activeTab;
}

// 多页标签面板
function TabPanel(_selectedStyleClass, _otherStyleClass, _debug, _debugContainer){
	this.debug = new Boolean(_debug).valueOf();
	this.debugContainer = _debugContainer;
	this.log("debug = " + this.debug);
	this.log("debugContainer = " + ((this.debugContainer != null && this.debugContainer != undefined) ? this.debugContainer.tagName : "undefined"));
	this.tabArray = new Array();
	this.selectedStyleClass = _selectedStyleClass;
	this.log("selectedStyleClass = " + this.selectedStyleClass);
	this.otherStyleClass = _otherStyleClass;
	this.log("otherStyleClass = " + this.otherStyleClass);
}

TabPanel.prototype.setDebugMsgContainer = function(debugContainer){
	this.debugContainer = debugContainer;
}

/**
 * tab和page都必须是div
 */
TabPanel.prototype.addPage = function(tabObj, pageObj){
	this.log("Execute method addPage(tabObj, pageObj)");
	this.log(tabObj + ", " + pageObj);
	this.log(tabObj.id + ", " + pageObj.id);

	this.tabArray.push(new TabPage(tabObj, pageObj));
	
	this.log("Finish method addPage(tabObj, pageObj)");
}

TabPanel.prototype.getActivePage = function(){
	return this.activeTab;
}

TabPanel.prototype.getActivePageID = function(){
	return this.activeTab.id;
}

TabPanel.prototype.activePage = function(activeTab){
	this.log("Execute method activePage(activeTab)");
	this.log(activeTab);
	this.log(activeTab.id);

	for (i in this.tabArray)
	{
		var tabObj = this.tabArray[i].tabObj;
		var pageObj = this.tabArray[i].pageObj;

		this.log(tabObj + ", " + pageObj);
		this.log(tabObj.id + ", " + pageObj.id);

		if (tabObj.id == activeTab.id)
		{
			tabObj.className = this.selectedStyleClass;
			this.log("Set " + tabObj.id + " 's class to " + this.selectedStyleClass);
			pageObj.style.display = "";
			this.log("Show " + tabObj.id);
		}
		else
		{
			tabObj.className = this.otherStyleClass;
			this.log("Set " + tabObj.id + " 's class to " + this.otherStyleClass);
			pageObj.style.display = "none";
			this.log("Hidden " + tabObj.id);
		}
	}

	this.activeTab = activeTab;
	this.log("Finish method activePage(activeTab)");
}

TabPanel.prototype.log = function(msg){
	// alert(this.debug && this.debugContainer != null && this.debugContainer !=
	// undefined && this.debugContainer.value != undefined);
	if (this.debug && this.debugContainer != null && this.debugContainer != undefined && this.debugContainer.value != undefined)
	{
		this.debugContainer.value += msg + "\n";
	}
}