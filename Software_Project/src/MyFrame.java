/*
 * ���α׷��� �������� ������ Ŭ����
 */
import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

	Graphics g;
	Container lobbyPage;

	

	public MyFrame() {
		// TODO Auto-generated constructor stub
		setTitle("Bowling Adventure : BEAT YOUR FRIEND"); // ����, ũ�� ����
		setSize(1200, 1200*3/4);
		//setResizable(false);

		setLocationRelativeTo(null);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		lobbyPage = getContentPane(); // ������ �޾ƿ���
		this.setLayout(null);
		
		
		this.add(new LobbyPage(this));	//�κ��������� ��ü �����ؼ� ����ֱ�
		
		setVisible(true);

	}
	void exit() {
		dispose();
	}
	

}
