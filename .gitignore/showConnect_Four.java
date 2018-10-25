package connect_Gui;

import gui_simple.MyFirstJFrame;

public class showConnect_Four {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater( new Runnable(){
			@Override
			public void run() {
				Connect_Four gui = new Connect_Four();
			}
		});
	}
}
