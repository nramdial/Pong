import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
public class Pong extends JFrame 
{
    Image dbImage;
    Graphics dbg;
    
    static Ball b = new Ball(193, 143);
    
    int GWIDTH = 400;
    int GHEIGHT = 300;

    Dimension screenSize = new Dimension(GWIDTH, GHEIGHT);
    
    Font font = new Font("Arial", Font.BOLD, 25);
    
    public Pong()
    {
        this.setTitle("Pong");
        this.setSize(screenSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new Key());
        this.setAlwaysOnTop(true);
    }
    public static void main(String[] args)
    {
        Pong m = new Pong();

        Thread ball = new Thread(b);
        ball.start();
        
        Thread p1 = new Thread(b.p1);
        Thread p2 = new Thread(b.p2);
        
        p1.start();
        p2.start();
    }
    public void paint(Graphics g)
    {
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        draw(dbg);
        g.drawImage(dbImage, 0, 0, this);
        
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("Pong", 160, 50);
    }
    public void draw(Graphics g)
    {
        b.draw(g);
        b.p1.draw(g);
        b.p2.draw(g);
        
        g.setColor(Color.WHITE);
        g.drawString(""+b.p1Score, 15, 50);
        g.drawString(""+b.p2Score, 370, 50);
        
        repaint();
    }
    public class Key extends KeyAdapter 
    {
        public void keyPressed(KeyEvent e)
        {
            b.p1.keyPressed(e);
            b.p2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e)
        {
            b.p1.keyReleased(e);
            b.p2.keyReleased(e);
        }
    }
}