package Graph.Controllers;

import Graph.Views.Login;
import Graph.Views.HomeView;

@SuppressWarnings("deprecation")
public class WindowController {
	Object model;
	Object view;
	
	public WindowController(Object model, Object view)
	{
		this.model = model;
		this.view = view;
	}
	
	public void viewLogin(Login view)
	{
		view.show();
	}
	
	public void viewHomePage(HomeView view)
	{
		view.show();
	}
}
