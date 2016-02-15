import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;



public class Keys extends JPanel
{
	
	Font font = new Font("Arial", Font.BOLD, 25);
	public Rectangle character;
	public Rectangle ground;
	
	public int charW = 24;
	public int charH = 36;
	
	public long jumptime = 200;
	
	public boolean right = false;
	public boolean left = false;
	public boolean mouseActive = false;
	public boolean jumping = false;
	
	public Point mouse;

	
	public Keys(Display f, Images i)
	{
		character = new Rectangle(180, 180, charW, charH );
		ground = new Rectangle(0, 550, 9000,30);
		
		f.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_D)
				{
					right = true;
					mouseActive = false;
				}
				
				if (e.getKeyCode() == KeyEvent.VK_A)
				{
					left = true;
					mouseActive = false;
				}	
				
				if (e.getKeyCode() == KeyEvent.VK_M)
				{
					mouseActive = true;
				}
				
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					jumping = true;
					new Thread(new thread()).start();
				}
			}
			
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_D)
				{
					right = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_A)
				{
					left = false;
				}	
			}
		});
		f.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseMoved(MouseEvent e) 
			{
				mouse = new Point(e.getX(), e.getY() - 25);
				if (mouseActive && character.x != Main.w - charW)
				{
					character.x = mouse.x;
					character.y = mouse.y;
				}
				repaint();
			}
		});
		
		f.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				mouse = new Point(e.getX(), e.getY() - 25);
				
				if (e.getButton() == MouseEvent.BUTTON1 && !mouseActive)
				{
					character.x = mouse.x;
					character.y = mouse.y;
				}
				
			}
			
		});
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Point pt1 = new Point(character.x, character.y + character.height);
		
		if (!ground.contains(pt1) && !mouseActive)
		{
			character.y++;
		}
		this.setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		g.fillRect(character.x, character.y, character.width, character.height);
		g.fillRect(ground.x, ground.y, ground.width, ground.height); 
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("A = Right, D = Left, M = Toggle Mouse Control, A/D to cancel", 50, 50);
		
		if(right && character.x != Main.w - charW)
		{
			character.x += 1;
		}
		if(left && character.x != 0)
		{
			character.x -= 1;
		}
		if (jumping)
		{
			character.y--;
		}
		repaint();
	}
	
	public class thread implements Runnable
	{
		public void run() 
		{
			try
			{
				Thread.sleep(jumptime);
				jumping = false;
				
				Thread.sleep(5);
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);
			}
		}
		
	}

}
