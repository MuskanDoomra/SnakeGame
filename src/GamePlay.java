import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener , ActionListener 
{
	
	private ImageIcon titleImage;
	private ImageIcon snakebg;
	private ImageIcon snakebody;
	private ImageIcon foodimage;
	private ImageIcon enemy;
	private ImageIcon brick;
	
	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];
	
	private boolean left = false;
	private boolean right = true;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int lengthofsnake = 3;
	
	private Timer timer;
	private int delay = 100;
		
	private int[] foodxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,
	                         675,700,725,750,775,800,825,850};
	private int[] foodypos= {125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525};
	
	private int[] enemyxpos = foodxpos;
	private int[] enemyypos = foodypos;
		
	private Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(17);
		
	private int score = 0;
	private int moves = 0;
	private int level = 1;
	private int flag = 1;
	private int var = 0;

	public GamePlay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer (delay, this);
		timer.start();
	}
	
	public void paint (Graphics g)
	{
		
		// starting position of snake
		if(level==1 && moves == 0)
		{
			snakexlength[2]=50;
			snakexlength[1]=75;
			snakexlength[0]=100;
			
			snakeylength[2]=150;
			snakeylength[1]=150;
			snakeylength[0]=150;
		}
		
		// draw the title image
		titleImage = new ImageIcon("heading.png");
		titleImage.paintIcon(this, g, 300, 5);
		
		// background image for the gameplay
		snakebg = new ImageIcon("bg.jpg");
		snakebg.paintIcon(this, g, 10, 95);
		
		// level information
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,20));
		g.drawString("Level: "+level, 430, 120);
		
		//draw border for gameplay
		g.setColor(Color.black);
		g.drawRect(24, 124, 851, 426);
		
		// draw background for the gameplay
		g.setColor(Color.white);
		g.fillRect(25, 125, 850, 425);
		
		// draw score
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,40));
		g.drawString("Scores: "+score, 950, 140);
		
		if(right)
		{
			rightmouth = new ImageIcon("rightmouth.png");
			rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		}
		
		if(score==5)
		{
			level=2;
			int[] newxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,375,400,425,450,475,500,550,575,600,625,650,
                            675,700,725,750,775,800,825,850};
			int[] newypos= {125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525};
			foodxpos = newxpos;
			foodypos = newypos;
		}
		
		if(score==10)
		{
			level=3;
			int[] newxpos= {50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,
	                        675,700,725,750,775,800,825};
			int[] newypos= {150,175,200,225,250,275,300,325,350,375,400,425,450,475,500};
			foodxpos = newxpos;
			foodypos = newypos;
		}
			
		if(score==15)
		{
			level=4;
			int[] newxpos= {50,75,100,125,150,175,200,225,275,300,325,350,375,400,425,450,475,500,525,550,575,625,650,
	                        675,700,725,750,775,800,825};
			int[] newypos= {150,175,200,225,250,275,300,325,350,375,400,425,450,475,500};
			foodxpos = newxpos;
			foodypos = newypos;
		}
		
		if(score==20)
		{
			level=5;
			int[] newxpos= {50,75,100,125,150,175,200,225,275,300,325,350,375,400,425,450,475,500,525,550,575,625,650,
	                        675,700,725,750,775,800,825};
			int[] newypos= {150,175,200,225,250,275,300,325,350,375,400,425,450,475,500};
			foodxpos = newxpos;
			foodypos = newypos;
		}
		
		if(score==25)
		{
			left=false;
			right=true;
    		up=false;
			down=false;
			
			g.setColor(Color.black);
			g.setFont(new Font("arial",Font.BOLD,50));
			g.drawString("YOU WIN", 300, 300);
			
			g.setFont(new Font("arial",Font.BOLD,20));
			g.drawString("Press space to start a new game", 260, 340);
			var=1;
			flag=0;
			moves=0;
		}
		
		// direction of the mouth of the snake
		for(int a=0; a< lengthofsnake; a++)
		{
			if(a==0 && right)
			{
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && left)
			{
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && up)
			{
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0 && down)
			{
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a!=0)
			{
				snakebody = new ImageIcon("snakebody.png");
				snakebody.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
		}
		
		// draw food image at a random position
		foodimage = new ImageIcon("food.png");
		foodimage.paintIcon(this, g, foodxpos[xpos], foodypos[ypos]);
        
		// food reaches the mouth of the snake
        if(foodxpos[xpos] == snakexlength[0] && foodypos[ypos] == snakeylength[0])
        {
        	lengthofsnake++;
        	score++;
        	xpos = random.nextInt(foodxpos.length);
        	ypos = random.nextInt(foodypos.length);
        	
        	if(score==5 || score==10 || score==15 || score==20)
        	{
    			g.setColor(Color.black);
    			g.setFont(new Font("arial",Font.PLAIN,40));
    			level++;
    			g.drawString("Level "+level+" starts", 300, 300);
    			g.setFont(new Font("arial",Font.PLAIN,20));
    			g.drawString("Press shift key", 350, 350);
    			
    			flag=0;
        	}
        }
		
        for(int b=1; b< lengthofsnake; b++)
        {
        	// collision
        	if(snakexlength[b] == snakexlength[0] && snakeylength[b] == snakeylength[0])
        	{
        		left=false;
    			right=true;
        		up=false;
    			down=false;
    			
    			g.setColor(Color.black);
    			g.setFont(new Font("arial",Font.BOLD,50));
    			g.drawString("Game Over !", 300, 300);
    			
    			g.setFont(new Font("arial",Font.BOLD,20));
    			g.drawString("Press space to RESTART", 315, 340);
    			var=1;
    			flag=0;
    			moves=0;
        	}
        }
        
        if(level==2)
		{
			//Level 1 finishes and level 2 starts
			// draw mines at random positions
        	enemy = new ImageIcon("enemy.png");
        	enemy.paintIcon(this, g, 350, 225);
        	enemy.paintIcon(this, g, 525, 350);
        	
        	//collision
        	if((snakexlength[0] == 350 && snakeylength[0] == 225) || (snakexlength[0] == 525 && snakeylength[0] == 350))
        	{
        		left=false;
    			right=true;
        		up=false;
    			down=false;
    			
    			g.setColor(Color.black);
    			g.setFont(new Font("arial",Font.BOLD,50));
    			g.drawString("Game Over !", 300, 300);
    			
    			g.setFont(new Font("arial",Font.BOLD,20));
    			g.drawString("Press space to RESTART", 315, 340);
    			var=1;
    			flag=0;
    			moves=0;
        	}
		}
		
		if(level==3)
		{
			//Level 2 finishes and level 3 starts
			// draw more mines at random positions
			enemy = new ImageIcon("enemy.png");
        	enemy.paintIcon(this, g, 350, 225);
        	enemy.paintIcon(this, g, 525, 350);
        	enemy.paintIcon(this, g, 100, 150);
        	enemy.paintIcon(this, g, 750, 300);
        	enemy.paintIcon(this, g, 150, 475);
        	
        	//collision
        	if((snakexlength[0] == 350 && snakeylength[0] == 225) || (snakexlength[0] == 525 && snakeylength[0] == 350)
        			|| (snakexlength[0] == 100 && snakeylength[0] == 150) || (snakexlength[0] == 750 && snakeylength[0] == 300)
        			|| (snakexlength[0] == 150 && snakeylength[0] == 475))
        	{
        		left=false;
    			right=true;
        		up=false;
    			down=false;
    			
    			g.setColor(Color.black);
    			g.setFont(new Font("arial",Font.BOLD,50));
    			g.drawString("Game Over !", 300, 300);
    			
    			g.setFont(new Font("arial",Font.BOLD,20));
    			g.drawString("Press space to RESTART", 315, 340);
    			var=1;
    			flag=0;
    			moves=0;
        	}
		}
		
		if(level==4)
		{
			//Level 3 finishes and level 4 starts
			// level 3 enemies only
			enemy = new ImageIcon("enemy.png");
        	enemy.paintIcon(this, g, 350, 225);
        	enemy.paintIcon(this, g, 525, 350);
        	enemy.paintIcon(this, g, 100, 150);
        	enemy.paintIcon(this, g, 750, 300);
        	enemy.paintIcon(this, g, 150, 475);
        	
        	// drawing boundary walls
        	brick = new ImageIcon("brick.png");
        	for(int i=125; i<526; i=i+25)
        	{
        		brick.paintIcon(this, g, 25, i);
        		brick.paintIcon(this, g, 850, i);
        	}
        	for(int i=25; i<851; i=i+25)
        	{
        		brick.paintIcon(this, g, i, 125);
        		brick.paintIcon(this, g, i, 526);
        	}
        	
        	//collision
        	if((snakexlength[0] == 350 && snakeylength[0] == 225) || (snakexlength[0] == 525 && snakeylength[0] == 350)
        			|| (snakexlength[0] == 100 && snakeylength[0] == 150) || (snakexlength[0] == 750 && snakeylength[0] == 300)
        			|| (snakexlength[0] == 150 && snakeylength[0] == 475) || snakeylength[0] < 150 || snakeylength[0] > 500 
        			|| snakexlength[0] < 50 || snakexlength[0] > 825)
        	{
        		left=false;
    			right=true;
        		up=false;
    			down=false;
    			
    			g.setColor(Color.black);
    			g.setFont(new Font("arial",Font.BOLD,50));
    			g.drawString("Game Over !", 300, 300);
    			
    			g.setFont(new Font("arial",Font.BOLD,20));
    			g.drawString("Press space to RESTART", 315, 340);
    			var=1;
    			flag=0;
    			moves=0;
        	}			
		}
		
		if(level==5)
		{
			//Level 4 finishes and level 5 starts
			// level 3 enemies only
			enemy = new ImageIcon("enemy.png");
        	enemy.paintIcon(this, g, 350, 225);
        	enemy.paintIcon(this, g, 525, 350);
        	enemy.paintIcon(this, g, 100, 150);
        	enemy.paintIcon(this, g, 750, 300);
        	enemy.paintIcon(this, g, 150, 475);
        	
        	brick = new ImageIcon("brick.png");
        	for(int i=200; i<451; i=i+25)
        	{
        		brick.paintIcon(this, g, 250, i);
        		brick.paintIcon(this, g, 600, i);
        	}
        	for(int i=125; i<526; i=i+25)
        	{
        		brick.paintIcon(this, g, 25, i);
        		brick.paintIcon(this, g, 850, i);
        	}
        	for(int i=25; i<851; i=i+25)
        	{
        		brick.paintIcon(this, g, i, 125);
        		brick.paintIcon(this, g, i, 526);
        	}
        	
        	//collision
        	if(snakexlength[0]==250 || snakexlength[0]==600)
        	{
        		if(snakeylength[0]==200 || snakeylength[0]==225 || snakeylength[0]==250 || snakeylength[0]==275 ||
        				snakeylength[0]==300 || snakeylength[0]==325 || snakeylength[0]==350 || snakeylength[0]==400 ||
        				snakeylength[0]==425 || snakeylength[0]==450)
        		{
        			left=false;
        			right=true;
            		up=false;
        			down=false;
        			
        			g.setColor(Color.black);
        			g.setFont(new Font("arial",Font.BOLD,50));
        			g.drawString("Game Over !", 300, 300);
        			
        			g.setFont(new Font("arial",Font.BOLD,20));
        			g.drawString("Press space to RESTART", 315, 340);
        			var=1;
        			flag=0;
        			moves=0;
        		}
        	}
        	
        	// collision
        	if((snakexlength[0] == 350 && snakeylength[0] == 225) || (snakexlength[0] == 525 && snakeylength[0] == 350)
        			|| (snakexlength[0] == 100 && snakeylength[0] == 150) || (snakexlength[0] == 750 && snakeylength[0] == 300)
        			|| (snakexlength[0] == 150 && snakeylength[0] == 475) || snakeylength[0] < 150 || snakeylength[0] > 500 
        			|| snakexlength[0] < 50 || snakexlength[0] > 825)
        	{
        		left=false;
    			right=true;
        		up=false;
    			down=false;
    			
    			g.setColor(Color.black);
    			g.setFont(new Font("arial",Font.BOLD,50));
    			g.drawString("Game Over !", 300, 300);
    			
    			g.setFont(new Font("arial",Font.BOLD,20));
    			g.drawString("Press space to RESTART", 315, 340);
    			var=1;
    			flag=0;
    			moves=0;
        	}
        	
		}
        
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(right && flag==1)
		{
			for(int r = lengthofsnake-1; r>=0; r--)
			{
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r>=0; r--)
			{
				if(r==0)
				{
					snakexlength[r] = snakexlength[r] + 25;
				}
				else
				{
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] > 850 && level<4)
					snakexlength[r] = 25;
			}
			repaint();
		}
		if(left && flag==1)
		{
			for(int r = lengthofsnake-1; r>=0; r--)
			{
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r = lengthofsnake; r>=0; r--)
			{
				if(r==0)
				{
					snakexlength[r] = snakexlength[r] - 25;
				}
				else
				{
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r] < 25  && level<4)
					snakexlength[r] = 850;
			}
			repaint();
		}
		if(up && flag==1)
		{
			for(int r = lengthofsnake-1; r>=0; r--)
			{
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r>=0; r--)
			{
				if(r==0)
				{
					snakeylength[r] = snakeylength[r] - 25;
				}
				else
				{
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] < 125  && level<4)
					snakeylength[r] = 525;
			}
			repaint();
		}
		if(down && flag==1)
		{
			for(int r = lengthofsnake-1; r>=0; r--)
			{
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r = lengthofsnake; r>=0; r--)
			{
				if(r==0)
				{
					snakeylength[r] = snakeylength[r] + 25;
				}
				else
				{
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r] > 525  && level<4)
					snakeylength[r] = 125;
			}
			repaint();
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			flag=1;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			score=0;
			moves=0;
			lengthofsnake=3;
			level=1;
			flag=1;
			right=true;
			left=false;
			up=false;
			down=false;
			repaint();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right=true;
			if(!left)
			{
				right=true;
			}
			else
			{
				left=true;
				right=false;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left=true;
			if(!right)
			{
				left=true;
			}
			else
			{
				right=true;
				left=false;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up=true;
			if(!down)
			{
				up=true;
			}
			else
			{
				down=true;
				up=false;
			}
			left=false;
			right=false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down=true;
			if(!up)
			{
				down=true;
			}
			else
			{
				up=true;
				down=false;
			}
			left=false;
			right=false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
