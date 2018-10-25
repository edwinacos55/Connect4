package gui_simple;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFirstJP extends JPanel implements ActionListener{
	
	private JLabel jl1;
	private JButton jbUp, jbDown;
	private int counter = 0;
	
	public MyFirstJP(){
		setLayout(new GridLayout(3, 1));
		jl1 = new JLabel();
		jl1.setText("HELLOOOOO");//jl1 = new JLabel("HELLOOOO");

		jbUp = new JButton(" + ");
		jbUp.addActionListener(this);//listen for clicks
		
		jbDown = new JButton(" - ");
		jbDown.addActionListener(this);//listen for clicks
		
		add(jl1);
		add(jbUp);
		add(jbDown);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		if(btnClicked.equals(jbUp)){
			incrementCounterLabel();
		}
		else if(btnClicked.equals(jbDown)){
			decrementCounterLabel();
		}
		else{
			jl1.setText("DUNNO");
		}
		
	}

	private void decrementCounterLabel() {
		counter--;
		jl1.setText("count= "+counter);
	}

	private void incrementCounterLabel() {
		counter++;
		jl1.setText("count= "+counter);
	}
}
