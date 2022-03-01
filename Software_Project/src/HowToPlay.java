import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;

/*
 * how to play�� Ŭ���� ���, �� �����ӿ��� ������ �������� ���ش�.
 */

public class HowToPlay extends JFrame {
	HowToPlay a;
	Image background = new ImageIcon(
			"C:\\Users\\gua05\\eclipse-workspace\\software\\Software_Project\\src\\image\\howtoplay.png").getImage();

	public HowToPlay() {

		setTitle("How to Play?"); // ����, ũ�� ����
		setSize(400, 800);
		setResizable(false);

		setLocationRelativeTo(null);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(null);
		Container explain = getContentPane();

		JButton comprehend = new JButton("Ȯ��");
		comprehend.setBounds(150, 600, 100, 50);
		a = this;
		CompreListener compreListen = new CompreListener(a);
		comprehend.addActionListener(compreListen);
		explain.add(comprehend);

		setVisible(true);

	}

	public void exit() {
		this.dispose();
	}

	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
	}
}

class CompreListener implements ActionListener {
	HowToPlay a;

	public CompreListener(HowToPlay a) {
		this.a = a;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("Ȯ��")) {
			a.dispose();
		}
	}

}