/*
 * 프로그램의 프레임을 생성할 클래스
 */
import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

	Graphics g;
	Container lobbyPage;

	

	public MyFrame() {
		// TODO Auto-generated constructor stub
		setTitle("Bowling Adventure : BEAT YOUR FRIEND"); // 제목, 크기 설정
		setSize(1200, 1200*3/4);
		//setResizable(false);

		setLocationRelativeTo(null);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lobbyPage = getContentPane(); // 프레임 받아오기
		this.setLayout(null);
		
		
		this.add(new LobbyPage(this));	//로비페이이지 객체 생성해서 띄워주기
		
		setVisible(true);

	}
	void exit() {
		dispose();
	}
	

}
