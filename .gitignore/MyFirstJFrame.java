package gui_simple;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class MyFirstJFrame extends JFrame{
	public MyFirstJFrame(){
		JPanel mainJP = new JPanel();
//		mainJP.setLayout(new GridLayout(1,2));
		
		MyFirstJP jp1 = new MyFirstJP();
		jp1.setBackground(Color.GREEN);
		mainJP.add(jp1);
		
		MyFirstJP jp2= new MyFirstJP();
		jp2.setBackground(Color.YELLOW);
		mainJP.add(jp2);
		
		
		add(mainJP);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);
	}

}
