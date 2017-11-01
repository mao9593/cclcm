/**
 * @description relative some operations of window
 * @author 		zhhuang
 * @date   		2008-1-4
 */
function openMaxWindow(url)
{
	height = window.screen.availHeight-60;
	width = window.screen.availWidth -10;
	win = window.open(url,"maxWin","directorys=no,toolbar=no,status=yes,menubar=no,scrollbars=yes,resizable=yes,width="+width+",height="+height+",left=-10,top=-10;");	
	win.focus();
}