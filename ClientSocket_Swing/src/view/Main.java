package view;

import valueObject.VAccount;

public class Main
{
	private PLoginDialog loginDialog;

	public Main()
	{

	}

	public void initialize()
	{
		this.loginDialog = new PLoginDialog();
		this.loginDialog.setVisible(true);
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.initialize();
//		main.finish();	
	}
}