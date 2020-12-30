import java.awt.Color;
import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame obj = new JFrame();
		GamePlay gameplay = new GamePlay();
		
		obj.setBounds(10, 10, 1225, 630);
		obj.setBackground(Color.white);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);	
	}

}
