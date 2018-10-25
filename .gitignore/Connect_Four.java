package connect_Gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Connect_Four extends JFrame{
	private JPanel mainPanel;
	ConnectBoard jpBoard;
	
	private JPanel scorePanel;
	private JLabel scoreBoard;
	
	private Player currPlayer;
	private Player playerOne;
	private Player playerTwo;
	
	
	public Connect_Four() {
	
		
		playerOne= new Player ("PLAYER 1", "O");
		playerTwo= new Player ("PLAYER 2", "X");
		currPlayer = playerOne;
		
		mainPanel= new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		jpBoard= new ConnectBoard();
		
		scorePanel= new JPanel();
		scoreBoard= new JLabel("Player One= "+ playerOne.getNumWins()+ "     "+
				"Player Two= "+ playerTwo.getNumWins() + "     "+ " Total Matches Played= " + 0);
		scorePanel.add(scoreBoard);
		add(scorePanel, BorderLayout.NORTH);
		scorePanel.setBackground(Color.green);
		scorePanel.setOpaque(true);
		scorePanel.setLayout(new GridLayout(3,1));
		

		
		mainPanel.add(BorderLayout.CENTER, jpBoard);
		
		add(mainPanel);
		setSize(600,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	private class ConnectBoard extends JPanel implements PlayerInterface, BoardInterface, ActionListener{
		private JLabel [] [] board;
		private final int ROWS= 6;
		private final int COLS= 7;
		
		public String [] topButton= {""};
		public JButton[] topButtons;
		public int[] tracker= {5,5,5,5,5,5,5};
		
		Border labelBorder= BorderFactory.createLineBorder(Color.black);
		Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		public ConnectBoard () {
			topButtons= new JButton [7];
			
			for(int i=0; i <topButtons.length; i++) {
			topButtons[i]= new JButton(topButton[0]);
			topButtons[i].setBackground(Color.white);
			topButtons[i].setFont(bigFont);
			topButtons[i].setText("Click Me");
			topButtons[i].setBorder(labelBorder);
			topButtons[i].setEnabled(true);
			topButtons[i].addActionListener(this);
			add(topButtons[i]);
			}
			
			
			
			setLayout(new GridLayout(ROWS+1,COLS));
			board= new JLabel [ROWS] [COLS];
			displayBoard();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int rowCol;
			for(int i=0; i< board.length+1; i++) {
				
			if(topButtons[i]== e.getSource()) {
				
				rowCol= tracker[i];
				board[rowCol][i].setText(currPlayer.getSymbol());
				tracker[i]--;
				
				if (tracker[i]<0) {
					topButtons[i].setEnabled(false);
				}
			} 
			
			}
			int matchesPlayed=0;
			int drawMatch=0;
			if(isWinner()== true) {
				JOptionPane.showMessageDialog(null, "WINNER IS "+currPlayer.getName());
				currPlayer.addNumWins();
				JOptionPane.showMessageDialog(null, "PLAY AGAIN?");
				clearBoard();
			} else if(isFull()){  
				drawMatch++;
				clearBoard();
				JOptionPane.showMessageDialog(null,"BOARD IS FULL... DRAW");
				
			} 
			matchesPlayed = drawMatch+ playerOne.getNumGames() + playerTwo.getNumGames();
			
			scoreBoard.setText("Player One= "+ playerOne.getNumWins()+ "     "+
					"Player Two= "+ playerTwo.getNumWins() + "     "+ 
					" Total Matches Played= " + matchesPlayed);
			takeTurn();
		}

		@Override
		public void displayBoard() {
			
			Border labelBorder= BorderFactory.createLineBorder(Color.black);
			Font bigFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
			
//			for(int col=0; col <topButtons.length; col++) {
//				topButtons[col]= new JButton();
//				topButtons[col].setBackground(Color.white);
//				topButtons[col].setFont(bigFont);
//				topButtons[col].setText("");
//				topButtons[col].setBorder(labelBorder);
//				topButtons[col].setHorizontalAlignment((int) CENTER_ALIGNMENT);
//				topButtons[col].addActionListener(this);
//				topButtons[col].setEnabled(true);
//				add(topButtons[col]);
//				
//			}
			
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col] = new JLabel();
					board[row][col].setFont(bigFont);
					board[row][col].setOpaque(true);
					board[row][col].setBackground(Color.orange);
					board[row][col].setEnabled(true);
					board[row][col].setBorder(labelBorder);
					board[row][col].setHorizontalAlignment((int) CENTER_ALIGNMENT);
					add(board[row][col]);	
				}
			}
			
		}

		@Override
		public void clearBoard() {
			
			for(int row=0; row<board.length; row++){
				for(int col=0; col<board[row].length; col++){
					board[row][col].setText("");
					board[row][col].setEnabled(true);
					
					
					
					for(int i=0; i< board.length+1; i++) {
						tracker[i]=5;
						topButtons[i].setEnabled(true);
					}
					
				}
			}
			
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean isFull() {
			for(int row=0; row < board.length; row++){
				for(int col=0; col< board[row].length; col++){
					String cellContent = board[row][col].getText().trim();
					if(cellContent.isEmpty()){
						return false;
					}
				}
			}
			
			return true;
		}
		
		public boolean isWinnerInRow(){
			String symbol = currPlayer.getSymbol();
			for(int row=0; row < board.length; row++){
				int numMatchesInRow = 0;
				for(int col=0; col< board[row].length; col++){
					if( board[row][col].getText().trim().equalsIgnoreCase(symbol)){
						if(numMatchesInRow == 3){
							return true;
				} 
						numMatchesInRow++;
					} else {
						numMatchesInRow=0;
					}
					
						}
							}
			return false;
		
									}
		
		public boolean isWinnerInCol(){
			String symbol = currPlayer.getSymbol();
			for(int col=0; col < COLS; col++){
				int numMatchesInRow = 0;
				for(int row=0; row< ROWS; row++){
					if( board[row][col].getText().trim().equalsIgnoreCase(symbol)){
						if(numMatchesInRow == 3){
							return true;
				} 
						numMatchesInRow++;
					} else {
						numMatchesInRow=0;
					}
					
						}
							}
			return false;

									}
			
		
		public boolean isWinnerUpDiag() {
			String symbol = currPlayer.getSymbol();
			int row= 0;
			int col=0;
			int matchesUp=0;
			for(int startRow= 3; startRow < 6; startRow++) {
				row= startRow;
				col= 0;
				matchesUp=0;
				while( row >=0  && col< 7) {
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matchesUp ++;
					if(matchesUp==4) {
						return true;
						}
					}else {
							matchesUp=0;
						}
						row--;
						col++;
					}
				}
			row=0;
			for(int startCol=1; startCol< 4; startCol++) {
				row=5;
				col= startCol;
				matchesUp=0;
				while( row >= 0 && col< 7) {
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matchesUp ++; 
					if(matchesUp==4) {
						return true;
						}
					}else {
						matchesUp=0;
						}
						row--;
						col++;
		
					}
				}
					
			return false;
		}
		
		public boolean isWinnerDownDiag() {
			String symbol = currPlayer.getSymbol();
			int row=0;
			int col=0;
			int matchesDown=0;
			for(int startRow=0 ; startRow < board.length; startRow++) {
			    row= startRow;
				col= 0;
				matchesDown=0;
				while( row < board.length && col< board[row].length) {
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matchesDown ++;
						} else {
							matchesDown=0;
						}
					if(matchesDown==4) {
						return true;
						}
						row++;
						col++;
					}
				}
			row=0;
			for(int startCol=0; startCol< board[row].length; startCol++) {
				row=0;
				col= startCol;
				matchesDown=0;
				while( row < board.length && col< board[row].length) {
					if(board[row][col].getText().trim().equalsIgnoreCase(symbol)) {
						matchesDown ++;
						} else {
							matchesDown=0;
						}
					if(matchesDown==4) {
						return true;
						}
						row++;
						col++;
						if (row> 5) {
							row=5;
							break;
						}
					}
				}
					
			return false;
			
		}
		
		@Override
		public boolean isWinner() {
			if( isWinnerInRow() || isWinnerInCol() || isWinnerUpDiag() || isWinnerDownDiag() ) {
				return true;
			}
			return false;
		}
		
		@Override
		public void takeTurn() {
			if(currPlayer.equals(playerOne)){
				currPlayer = playerTwo;
			
			}
			else{
				currPlayer = playerOne;
			
		}
		
	}
}
}
