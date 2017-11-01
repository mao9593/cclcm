function init(action,homeUrl)
{
	if (action=="success")
	{
		topGo(homeUrl);
	}	
}

function topGo(url)               
{
	var topWin;
	
	if (window.parent)
	{
		if (window.parent.name == "mainFrame")
		{
			topWin = window.parent
		}
		else
			topWin = window.parent.parent;
	}
	else
		topWin = window;
	topWin.location = url;
}