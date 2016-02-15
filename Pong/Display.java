import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Display extends JFrame
{
	public JPanel gp = (JPanel) getGlassPane();
	public Images i;
	public Keys k;
	
	public Display()
	{
		i = new Images();
		gp.setVisible(true);
		k = new Keys(this, i);
		
		gp.setLayout(new GridLayout());
		this.setLayout(new GridLayout(1, 1, 0, 0));
		
		gp.add(k);
		this.add(i);
	}

}
