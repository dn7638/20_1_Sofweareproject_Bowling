
/*
 * 로비 페이지, 로비페이지의 3개의 버튼에 대한 함수
 * 앞의 MyFrame 에 add해줄 첫 페이지
 */
import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class LobbyPage extends JPanel {
	MyFrame myFrame;
	ScoreBoard gamePage;
	Image background = new ImageIcon(
			"C:\\Users\\gua05\\eclipse-workspace\\software\\Software_Project\\src\\image\\lobby.png").getImage();

	public LobbyPage(MyFrame myFrame) {
		// TODO Auto-generated constructor stub
		
		this.myFrame = myFrame;
		
		setSize(1200, 1200 * 3 / 4);

		this.setLayout(null);

		ActionListener lobbyListener = new LobbyListener(this, myFrame);

		JButton start = new JButton("Game Start!");
		start.setLocation(300, 600);
		start.setSize(180, 60);
		start.addActionListener(lobbyListener);

		JButton howtoplay = new JButton("How to play?");
		howtoplay.setLocation(510, 600);
		howtoplay.setSize(180, 60);
		howtoplay.addActionListener(lobbyListener);

		JButton exit = new JButton("EXIT");
		exit.setLocation(720, 600);
		exit.setSize(180, 60);
		exit.addActionListener(lobbyListener);

		this.add(start);
		this.add(howtoplay);
		this.add(exit);
		setVisible(true);

	}

	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}
	


}

class LobbyListener implements ActionListener {
	JFrame explain;
	ScoreBoard ScoreBoard;
	LobbyPage lobby;
	MyFrame myFrmae;
	
	
	public LobbyListener(LobbyPage lobby, MyFrame myFrame) {
		this.lobby = lobby;
		this.myFrmae = myFrame;
		this.myFrmae.getContentPane();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Game Start!")) {
			lobby.setVisible(false);
			
			ScoreBoard = new ScoreBoard();
			
			this.myFrmae.getContentPane().add(ScoreBoard);	
			
				
			
		} else if (b.getText().equals("How to play?")) {
			explain = new HowToPlay();
			
		} else if (b.getText().equals("EXIT")) {
			System.exit(0);
		}

	}

}
