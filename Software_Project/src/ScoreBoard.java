/*
 * ������ �����ϴ� ȭ��, ���� ���� ��Ȳ�� ������� ǥ��
 * �տ������� ���ȭ�� �� �� �ݸ�, ������ �ð��� ���Ǽ����� ���� ���ȭ�� �� ���� �ʰ� ���İ�Ƽ �ڵ尡 ��.
 * Ŭ������ �������� ������ ��ü�� �����ϰ� ������ �ְ� ���� �� �ֵ��� �޼ҵ带 �����ϰ��� �Ͽ����� ���� ����� �ϳ��� ū Ŭ���� �ȿ� ���� �ʵ����
 * �����ϴ� ������� ������ �����Ͽ����ϴ�.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ScoreBoard extends JPanel {

	public void paintComponent(Graphics g) {
		ImageIcon image = new ImageIcon(
				"C:\\Users\\gua05\\eclipse-workspace\\software\\Software_Project\\src\\image\\Bowling.jpg");
		g.drawImage(image.getImage(), 0, 0, 1200, 900, null);
	}

	JPanel gamePanel;
	JPanel scorePanel;
	JPanel controlPanel;
	JProgressBar progressBar; // ������ �� �ʵ�

	// �ٽ��ھ� �Լ��� �ʿ��� �ʵ�
	int turnOfPlayer1 = 0;
	int turnOfPlayer2 = 0;
	int score1[] = new int[20], score2[] = new int[20];
	int sum1[] = new int[10], sum2[] = new int[10];

	int hitPin = 0;
	int remainPin = 10; // ������ 10��
	int temp;
	// �������� �����ֱ� ���� �ʵ�
	JPanel scoreGrid2 = new JPanel(); // ���ھ� ���̺��� ���� 20ĭ¥�� �г�
	JLabel[] player1Score = new JLabel[20];
	JLabel[] player1Sum = new JLabel[10];
	
	JLabel[] player2Score = new JLabel[20];
	JLabel[] player2Sum = new JLabel[10];
	
	//���� ��� �������ǰ�� â
	JLabel result = new JLabel();

	public ScoreBoard() {
		// TODO Auto-generated constructor stub

		setBounds(0, 0, 1200, 900);
		setLayout(null);

		// ������ �ֻ��� �г�
		scorePanel = new JPanel();
		scorePanel.setBounds(50, 10, 900, 150);
		scorePanel.setBackground(Color.GRAY);
		scorePanel.setLayout(new GridLayout(6, 1));

		// �׵θ� ���ϱ�
		Border border = new LineBorder(Color.BLACK);

		// ������ 1��° ��
		JPanel scoreGrid1 = new JPanel();
		scoreGrid1.setLayout(new GridLayout(1, 10));
		scorePanel.add(scoreGrid1);

		JLabel[] player1count = new JLabel[10]; // ������ ���̺�
		String[] num1to10 = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }; // Ƚ�� ī��Ʈ

		for (int i = 0; i < 10; i++) { // Ƚ�� ����

			player1count[i] = new JLabel(num1to10[i]);
			player1count[i].setBorder(border);
			player1count[i].setHorizontalAlignment(JLabel.CENTER);
			scoreGrid1.add(player1count[i]);
		}

		// ������ 2��° ��

		scoreGrid2.setLayout(new GridLayout(1, 20));
		scorePanel.add(scoreGrid2);
		for (int i = 0; i < 20; i++) { // Ƚ�� ����

			player1Score[i] = new JLabel();
			player1Score[i].setBorder(border);
			player1Score[i].setHorizontalAlignment(JLabel.CENTER);
			player1Score[i].setForeground(Color.RED); 
			scoreGrid2.add(player1Score[i]);
		}
		// ������ 3��° ��

		JPanel scoreGrid3 = new JPanel();
		scoreGrid3.setLayout(new GridLayout(1, 10));
		scorePanel.add(scoreGrid3);
		for (int i = 0; i < 10; i++) { // Ƚ�� ����

			player1Sum[i] = new JLabel();
			player1Sum[i].setBorder(border);
			player1Sum[i].setHorizontalAlignment(JLabel.CENTER);
			player1Sum[i].setForeground(Color.RED); 
			scoreGrid3.add(player1Sum[i]);
		}

		// ������ 4��° ��
		JPanel scoreGrid4 = new JPanel();
		scoreGrid4.setLayout(new GridLayout(1, 10));
		scorePanel.add(scoreGrid4);

		JLabel[] player2count = new JLabel[10]; // ������ ���̺�
		// String[] num1to10 = new String[] {"1","2","3","4","5","6","7","8","9","10"};
		

		for (int i = 0; i < 10; i++) { // Ƚ�� ����

			player2count[i] = new JLabel(num1to10[i]);
			player2count[i].setBorder(border);
			player2count[i].setHorizontalAlignment(JLabel.CENTER);
			scoreGrid4.add(player2count[i]);
		}

		// ������ 5��° ��
		JPanel scoreGrid5 = new JPanel();
		scoreGrid5.setLayout(new GridLayout(1, 20));
		scorePanel.add(scoreGrid5);
		for (int i = 0; i < 20; i++) { // Ƚ�� ����

			player2Score[i] = new JLabel();
			player2Score[i].setBorder(border);
			player2Score[i].setHorizontalAlignment(JLabel.CENTER);
			player2Score[i].setForeground(Color.BLUE); 
			scoreGrid5.add(player2Score[i]);
		}

		// ������ 6��° ��
		JPanel scoreGrid6 = new JPanel();
		scoreGrid6.setLayout(new GridLayout(1, 10));
		scorePanel.add(scoreGrid6);
		for (int i = 0; i < 10; i++) { // Ƚ�� ����

			player2Sum[i] = new JLabel();
			player2Sum[i].setBorder(border);
			player2Sum[i].setHorizontalAlignment(JLabel.CENTER);
			player2Sum[i].setForeground(Color.BLUE); 
			scoreGrid6.add(player2Sum[i]);
		}

		// �������� ��ư�� �÷����� �г�
		JPanel controlPan = new JPanel();
		controlPan.setLayout(null);
		controlPan.setBounds(818, 382, 300, 270);
		controlPan.setBorder(border);

		TimerThread gauge = new TimerThread(this);
		ControlListener conListener = new ControlListener(this, gauge); // ��ư�� ���� ������

		JButton start = new JButton("1p.start!");
		start.setBounds(40, 120, 100, 40);
		start.addActionListener(conListener);
		start.setBackground(Color.red);
		controlPan.add(start);

		JButton stop = new JButton("1p.stop!");
		stop.setBounds(160, 120, 100, 40);
		controlPan.add(stop);
		stop.setBackground(Color.red);
		stop.addActionListener(conListener);

		//2p ��ư		
		JButton start2 = new JButton("2p.start!");
		start2.setBounds(40, 180, 100, 40);
		start2.addActionListener(conListener);
		start2.setBackground(Color.BLUE);
		controlPan.add(start2);

		JButton stop2 = new JButton("2p.stop!");
		stop2.setBounds(160, 180, 100, 40);
		controlPan.add(stop2);
		stop2.setBackground(Color.BLUE);
		stop2.addActionListener(conListener);
		//////////////////////////////////////////////////////////////////�Ʒ��� ������

		Border gageBorder = BorderFactory.createTitledBorder("Power Gauge..."); // ������ ����

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(35, 50, 230, 50);
		progressBar.setBorder(gageBorder);
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		// progressBar.setValue(0);

		// progressBar.setStringPainted(true);
		controlPan.add(progressBar);

		this.progressBar = progressBar;

		//���� 1ȸ�� ��� ���
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setBorder(border);
		gamePanel.setBounds(970, 10, 170, 150);
		
		Font font = new Font("Helvetica", Font.BOLD, 25);
		result.setFont(font);
		result.setBorder(border);
		result.setBounds(15, 10, 140, 130);
		result.setHorizontalAlignment(JLabel.CENTER);
		result.setForeground(Color.BLACK); 
		gamePanel.add(result);
		add(gamePanel);
		
		
		
		add(controlPan);

		/*
		 * String header[] = {"1","2","3","4","5","6","7","8","9","10"}; // panel�� ���̺�
		 * ����ֱ� 1���� JTable scoreTableNum = new JTable(1,10); scoreTableNum.set
		 * scorePanel.add(scoreTableNum); JTable scoreTable = new JTable(1, 20);
		 * scorePanel.add(scoreTable); JTable TotalScore = new JTable(1, 10);
		 * scorePanel.add(TotalScore);
		 * 
		 * // panel�� ���̺� ����ֱ� 2��° JTable scoreTableNum2 = new JTable(1, 10);
		 * scorePanel.add(scoreTableNum2); JTable scoreTable2 = new JTable(1, 20);
		 * scorePanel.add(scoreTable2); JTable TotalScore2 = new JTable(1, 10);
		 * scorePanel.add(TotalScore2);
		 */

		add(scorePanel);

		setVisible(true);

	}

	
	//������ �޾ƿ� ���̺� ��������ִ� �Լ�1
	void getScore(int value) {

	
		if(turnOfPlayer1 == 20) {
			return;
		}

		if (turnOfPlayer1 % 2 == 0) { // �������� ù��°�� ���

			hitPin = remainPin * value / 100; // �����߸� �� ���ϱ�
			
			
			remainPin = remainPin - hitPin; // ������ ���ϱ�
			
			if (remainPin == 0) { // ��Ʈ����ũ�� ���
				System.out.println("��Ʈ����ũ�Դϴ�.");
				
				System.out.println("������ 30��"); // ���� gui�� ����
				result.setText("��Ʈ����ũ!");
				
				score1[turnOfPlayer1] = 30;
				player1Score[turnOfPlayer1].setText((Integer.toString(score1[turnOfPlayer1])));// 30�� gui, ���̺� ����

				if(turnOfPlayer1 / 2>0) {
					sum1[turnOfPlayer1 / 2] += sum1[turnOfPlayer1 / 2 - 1]; // �ջ� ����
				}
				sum1[turnOfPlayer1 / 2] += 30;
				player1Sum[turnOfPlayer1 / 2].setText((Integer.toString(sum1[turnOfPlayer1 / 2])));//���̺� ����
				
				score1[turnOfPlayer1 + 1] = 0;
				player1Score[turnOfPlayer1 + 1].setText((Integer.toString(score1[turnOfPlayer1 + 1])));// 30�� gui,
																										// ���̺� ����

				turnOfPlayer1 += 2;
				hitPin = 0;
				remainPin = 10; // ������ ���� �ʱ�ȭ
				return;

			}
			if (remainPin != 0) { // ��Ʈ����ũ�� �ƴ� ���

				score1[turnOfPlayer1] = hitPin; // hitPin ���� ó�� ���� ���
				player1Score[turnOfPlayer1].setText((Integer.toString(score1[turnOfPlayer1])));// ���̺� ����

				System.out.println(hitPin + "�̸�ŭ �Ѿ�߸� ����Ϸ�");
				result.setText(hitPin + "��!");
				
				if(turnOfPlayer1 / 2>0) {
					sum1[turnOfPlayer1 / 2] += sum1[turnOfPlayer1 / 2 - 1]; // �ջ� ����
				}
				sum1[turnOfPlayer1 / 2] += hitPin; // �ջ� ����
				player1Sum[turnOfPlayer1 / 2].setText((Integer.toString(sum1[turnOfPlayer1 / 2])));//���̺� ����
				
				turnOfPlayer1++; // ���� ���ʷ�
				System.out.println("���� �ɼ���z"+remainPin);
				return;
			}

		}

		if (turnOfPlayer1 % 2 == 1) { // �������� �ι�°�� ��� �� ó���� ��Ʈ����ũ�� �ƴ� ���
			hitPin = (remainPin + 4) * value / 100; // �׽�Ʈ �� �� ��� �Ű�Ἥ �� ��� value�� 60�� ���� ���� ���԰�, 60 �ι��̸� ���� �����ϵ��� ����;
			System.out.println("���� �ɼ���"+remainPin);
			System.out.println(hitPin+"�̸�ŭ �Ѿ���� �ϴµ�");
			temp = remainPin;
			remainPin = remainPin - hitPin;

			if (remainPin <= 0) { // ����� ó��
				
				hitPin = temp;
				System.out.println(hitPin + "��ŭ �Ѿ�߸�");
				result.setText(hitPin + "��!\n\n�����!");
				score1[turnOfPlayer1] = hitPin;// hitPin ���� ���
				player1Score[turnOfPlayer1].setText((Integer.toString(score1[turnOfPlayer1])));
				sum1[(turnOfPlayer1) / 2] += ( 10);// ������ ���ھ ������ ���ϰ�, ���� ������ �ʰ�, 10�� ���ϱ�
				player1Sum[turnOfPlayer1 / 2].setText((Integer.toString(sum1[turnOfPlayer1 / 2])));//���̺� ����
				
				turnOfPlayer1++; // ���� ���ʷ�
				hitPin = 0;
				remainPin = 10; // ������ ���� �ʱ�ȭ
			}

			else { // ���� �ƴ� ���
				score1[turnOfPlayer1] = hitPin;// hitPin ���� ���
				System.out.println(hitPin + "��ŭ �Ѿ�߸�");
				result.setText(hitPin + "��!");
				player1Score[turnOfPlayer1].setText((Integer.toString(score1[turnOfPlayer1])));

				sum1[(turnOfPlayer1) / 2] += (hitPin);// ������ ���ھ ������ ���ϰ�, ���� ���ϰ�
				player1Sum[turnOfPlayer1 / 2].setText((Integer.toString(sum1[turnOfPlayer1 / 2])));//���̺� ����

				turnOfPlayer1++; // ���� ���ʷ�
				hitPin = 0;
				remainPin = 10; // ������ ���� �ʱ�ȭ
			}
		}
		this.setVisible(true);
	}
	
	//������ �޾ƿ� ���̺� ��������ִ� �Լ�2
	void getScore2(int value) {

		if(turnOfPlayer2 == 20) {
			return;
		}

		if (turnOfPlayer2 % 2 == 0) { // �������� ù��°�� ���

			hitPin = remainPin * value / 100; // �����߸� �� ���ϱ�

			remainPin = remainPin - hitPin; // ������ ���ϱ�
			
			if (remainPin == 0) { // ��Ʈ����ũ�� ���
				System.out.println("��Ʈ����ũ�Դϴ�.");
				System.out.println("������ 30��"); // ���� gui�� ����
				result.setText("��Ʈ����ũ!");

				score2[turnOfPlayer2] = 30;
				player2Score[turnOfPlayer2].setText((Integer.toString(score2[turnOfPlayer2])));// 30�� gui, ���̺� ����

				if(turnOfPlayer2 / 2>0) {
					sum2[turnOfPlayer2 / 2] += sum2[turnOfPlayer2 / 2 - 1]; // �ջ� ����
				}
				sum2[turnOfPlayer2 / 2] += 30;
				player2Sum[turnOfPlayer2 / 2].setText((Integer.toString(sum2[turnOfPlayer2 / 2])));//���̺� ����
				
				score2[turnOfPlayer2 + 1] = 0;
				player2Score[turnOfPlayer2 + 1].setText((Integer.toString(score2[turnOfPlayer2 + 1])));// 30�� gui,
																										// ���̺� ����

				turnOfPlayer2 += 2;
				hitPin = 0;
				remainPin = 10; // ������ ���� �ʱ�ȭ
				return;

			}
			if (remainPin != 0) { // ��Ʈ����ũ�� �ƴ� ���

				score2[turnOfPlayer2] = hitPin; // hitPin ���� ó�� ���� ���
				player2Score[turnOfPlayer2].setText((Integer.toString(score2[turnOfPlayer2])));// ���̺� ����

				System.out.println(hitPin + "�̸�ŭ �Ѿ�߸� ����Ϸ�");
				result.setText(hitPin + "��!");
				
				if(turnOfPlayer2 / 2>0) {
					sum2[turnOfPlayer2 / 2] += sum2[turnOfPlayer2 / 2 - 1]; // �ջ� ����
				}
				sum2[turnOfPlayer2 / 2] += hitPin; // �ջ� ����
				player2Sum[turnOfPlayer2 / 2].setText((Integer.toString(sum2[turnOfPlayer2 / 2])));//���̺� ����
				
				turnOfPlayer2++; // ���� ���ʷ�
				System.out.println("���� �ɼ���z"+remainPin);
				return;
			}

		}

		if (turnOfPlayer2 % 2 == 1) { // �������� �ι�°�� ��� �� ó���� ��Ʈ����ũ�� �ƴ� ���
			hitPin = (remainPin + 4) * value / 100; // �׽�Ʈ �� �� ��� �Ű�Ἥ �� ��� value�� 60�� ���� ���� ���԰�, 60 �ι��̸� ���� �����ϵ��� ����;
			System.out.println("���� �ɼ���"+remainPin);
			System.out.println(hitPin+"�̸�ŭ �Ѿ���� �ϴµ�");
			temp = remainPin;
			remainPin = remainPin - hitPin;

			if (remainPin <= 0) { // ����� ó��
				
				hitPin = temp;
				System.out.println(hitPin + "��ŭ �Ѿ�߸�");
				result.setText(hitPin + "��!\n�����!");
				score2[turnOfPlayer2] = hitPin;// hitPin ���� ���
				player2Score[turnOfPlayer2].setText((Integer.toString(score2[turnOfPlayer2])));
				sum2[(turnOfPlayer2) / 2] += ( 10);// ������ ���ھ ������ ���ϰ�, X���� ������ �ʰ�, 10�� ���ϱ�
				player2Sum[turnOfPlayer2 / 2].setText((Integer.toString(sum2[turnOfPlayer2 / 2])));//���̺� ����
				
				turnOfPlayer2++; // ���� ���ʷ�
				hitPin = 0;
				remainPin = 10; // ������ ���� �ʱ�ȭ
			}

			else { // ���� �ƴ� ���
				score2[turnOfPlayer2] = hitPin;// hitPin ���� ���
				System.out.println(hitPin + "��ŭ �Ѿ�߸�");
				result.setText(hitPin + "��!");
				player2Score[turnOfPlayer2].setText((Integer.toString(score2[turnOfPlayer2])));

				sum2[(turnOfPlayer2) / 2] += (hitPin);// ������ ���ھ ������ ���ϰ�, ���� ���ϰ�
				player2Sum[turnOfPlayer2 / 2].setText((Integer.toString(sum2[turnOfPlayer2 / 2])));//���̺� ����

				turnOfPlayer2++; // ���� ���ʷ�
				hitPin = 0;
				remainPin = 10; // ������ ���� �ʱ�ȭ
			}
		}
		this.setVisible(true);
	}
	
	
	//������ ������ gui�� ����
	class TimerThread extends Thread {
		ScoreBoard scoreBoard;
		int num;

		public TimerThread(ScoreBoard scoreBoard) {
			// TODO Auto-generated constructor stub
			this.scoreBoard = scoreBoard;
		}

		boolean flag = false;// false�� �ʱ�ȭ

		public void finish() {
			flag = true;
		}// finish�Լ��� ȣ��Ǹ� ����

		public void run() {
			while (true) {

				
				for (int i = 0; i < 101; i++) {

					try {
						progressBar.setValue(i);
						num = i;
						System.out.println(i);
						Thread.sleep(3);
						if (flag == true)
							return;// ������ ����
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for (int i = 100; i > 0; i--) {

					try {
						progressBar.setValue(i);
						num = i;
						System.out.println(i);
						Thread.sleep(3);
						if (flag == true)

							return;// ������ ����
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	class ControlListener implements ActionListener {

		ScoreBoard scoreBoard;
		JButton button;
		TimerThread gauge;
		TimerThread gauge2;

		public ControlListener(ScoreBoard scoreBoard, TimerThread gauge) {
			// TODO Auto-generated constructor stub
			this.scoreBoard = scoreBoard;
			this.gauge = gauge;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int value;
			this.button = (JButton) e.getSource();

			if (button.getText().equals("1p.start!")) {
				TimerThread gauge2 = new TimerThread(scoreBoard);
				this.gauge2 = gauge2;
				System.out.println("Ŭ��");
				gauge2.start();
			}

			if (button.getText().equals("1p.stop!")) {

				System.out.println("Ŭ��");
				value = gauge2.num;
				gauge2.finish();
				System.out.println("value ���� " + value);
				scoreBoard.getScore(value);
			}
			if (button.getText().equals("2p.start!")) {
				TimerThread gauge2 = new TimerThread(scoreBoard);
				this.gauge2 = gauge2;
				System.out.println("Ŭ��");
				gauge2.start();
			}

			if (button.getText().equals("2p.stop!")) {

				System.out.println("Ŭ��");
				value = gauge2.num;
				gauge2.finish();
				System.out.println("value ���� " + value);
				scoreBoard.getScore2(value);
			}
		}

	}

}
