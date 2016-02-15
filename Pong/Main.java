import javax.swing.JFrame;


public class Main 
{
	public static Display f = new Display();
	public static int w = 800;
	public static int h = 600;
	
	public static void main (String[] args)
	{
		f.setSize(w, h);
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Java Game");
		f.setLocationRelativeTo(null);
		f.setAlwaysOnTop(true);
		
		
	}

}
