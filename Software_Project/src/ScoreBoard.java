/*
 * 게임을 진행하는 화면, 게임 진행 상황과 결과역시 표시
 * 앞에까지는 모듈화가 잘 된 반면, 부족한 시간과 편의성으로 인해 모듈화가 잘 되지 않고 스파게티 코드가 됨.
 * 클래스를 여러개로 나누어 객체를 생성하고 정보를 주고 받을 수 있도록 메소드를 구현하고자 하였으나 급한 관계로 하나의 큰 클래스 안에 많은 필드들을
 * 조절하는 방식으로 게임을 구현하였습니다.
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
	JProgressBar progressBar; // 게이지 바 필드

	// 겟스코어 함수에 필요한 필드
	int turnOfPlayer1 = 0;
	int turnOfPlayer2 = 0;
	int score1[] = new int[20], score2[] = new int[20];
	int sum1[] = new int[10], sum2[] = new int[10];

	int hitPin = 0;
	int remainPin = 10; // 볼링핀 10개
	int temp;
	// 점수판을 보여주기 위한 필드
	JPanel scoreGrid2 = new JPanel(); // 스코어 레이블을 담을 20칸짜리 패널
	JLabel[] player1Score = new JLabel[20];
	JLabel[] player1Sum = new JLabel[10];
	
	JLabel[] player2Score = new JLabel[20];
	JLabel[] player2Sum = new JLabel[10];
	
	//우측 상단 볼링한판결과 창
	JLabel result = new JLabel();

	public ScoreBoard() {
		// TODO Auto-generated constructor stub

		setBounds(0, 0, 1200, 900);
		setLayout(null);

		// 점수판 최상위 패널
		scorePanel = new JPanel();
		scorePanel.setBounds(50, 10, 900, 150);
		scorePanel.setBackground(Color.GRAY);
		scorePanel.setLayout(new GridLayout(6, 1));

		// 테두리 정하기
		Border border = new LineBorder(Color.BLACK);

		// 점수판 1번째 줄
		JPanel scoreGrid1 = new JPanel();
		scoreGrid1.setLayout(new GridLayout(1, 10));
		scorePanel.add(scoreGrid1);

		JLabel[] player1count = new JLabel[10]; // 점수판 레이블
		String[] num1to10 = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }; // 횟수 카운트

		for (int i = 0; i < 10; i++) { // 횟수 삽입

			player1count[i] = new JLabel(num1to10[i]);
			player1count[i].setBorder(border);
			player1count[i].setHorizontalAlignment(JLabel.CENTER);
			scoreGrid1.add(player1count[i]);
		}

		// 점수판 2번째 줄

		scoreGrid2.setLayout(new GridLayout(1, 20));
		scorePanel.add(scoreGrid2);
		for (int i = 0; i < 20; i++) { // 횟수 삽입

			player1Score[i] = new JLabel();
			player1Score[i].setBorder(border);
			player1Score[i].setHorizontalAlignment(JLabel.CENTER);
			player1Score[i].setForeground(Color.RED); 
			scoreGrid2.add(player1Score[i]);
		}
		// 점수판 3번째 줄

		JPanel scoreGrid3 = new JPanel();
		scoreGrid3.setLayout(new GridLayout(1, 10));
		scorePanel.add(scoreGrid3);
		for (int i = 0; i < 10; i++) { // 횟수 삽입

			player1Sum[i] = new JLabel();
			player1Sum[i].setBorder(border);
			player1Sum[i].setHorizontalAlignment(JLabel.CENTER);
			player1Sum[i].setForeground(Color.RED); 
			scoreGrid3.add(player1Sum[i]);
		}

		// 점수판 4번째 줄
		JPanel scoreGrid4 = new JPanel();
		scoreGrid4.setLayout(new GridLayout(1, 10));
		scorePanel.add(scoreGrid4);

		JLabel[] player2count = new JLabel[10]; // 점수판 레이블
		// String[] num1to10 = new String[] {"1","2","3","4","5","6","7","8","9","10"};
		

		for (int i = 0; i < 10; i++) { // 횟수 삽입

			player2count[i] = new JLabel(num1to10[i]);
			player2count[i].setBorder(border);
			player2count[i].setHorizontalAlignment(JLabel.CENTER);
			scoreGrid4.add(player2count[i]);
		}

		// 점수판 5번째 줄
		JPanel scoreGrid5 = new JPanel();
		scoreGrid5.setLayout(new GridLayout(1, 20));
		scorePanel.add(scoreGrid5);
		for (int i = 0; i < 20; i++) { // 횟수 삽입

			player2Score[i] = new JLabel();
			player2Score[i].setBorder(border);
			player2Score[i].setHorizontalAlignment(JLabel.CENTER);
			player2Score[i].setForeground(Color.BLUE); 
			scoreGrid5.add(player2Score[i]);
		}

		// 점수판 6번째 줄
		JPanel scoreGrid6 = new JPanel();
		scoreGrid6.setLayout(new GridLayout(1, 10));
		scorePanel.add(scoreGrid6);
		for (int i = 0; i < 10; i++) { // 횟수 삽입

			player2Sum[i] = new JLabel();
			player2Sum[i].setBorder(border);
			player2Sum[i].setHorizontalAlignment(JLabel.CENTER);
			player2Sum[i].setForeground(Color.BLUE); 
			scoreGrid6.add(player2Sum[i]);
		}

		// 게이지와 버튼을 올려놓을 패널
		JPanel controlPan = new JPanel();
		controlPan.setLayout(null);
		controlPan.setBounds(818, 382, 300, 270);
		controlPan.setBorder(border);

		TimerThread gauge = new TimerThread(this);
		ControlListener conListener = new ControlListener(this, gauge); // 버튼에 대한 리스너

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

		//2p 버튼		
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
		//////////////////////////////////////////////////////////////////아래는 게이지

		Border gageBorder = BorderFactory.createTitledBorder("Power Gauge..."); // 게이지 보더

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(35, 50, 230, 50);
		progressBar.setBorder(gageBorder);
		progressBar.setMaximum(100);
		progressBar.setMinimum(0);
		// progressBar.setValue(0);

		// progressBar.setStringPainted(true);
		controlPan.add(progressBar);

		this.progressBar = progressBar;

		//볼링 1회당 결과 출력
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
		 * String header[] = {"1","2","3","4","5","6","7","8","9","10"}; // panel에 테이블
		 * 집어넣기 1번쨰 JTable scoreTableNum = new JTable(1,10); scoreTableNum.set
		 * scorePanel.add(scoreTableNum); JTable scoreTable = new JTable(1, 20);
		 * scorePanel.add(scoreTable); JTable TotalScore = new JTable(1, 10);
		 * scorePanel.add(TotalScore);
		 * 
		 * // panel에 테이블 집어넣기 2번째 JTable scoreTableNum2 = new JTable(1, 10);
		 * scorePanel.add(scoreTableNum2); JTable scoreTable2 = new JTable(1, 20);
		 * scorePanel.add(scoreTable2); JTable TotalScore2 = new JTable(1, 10);
		 * scorePanel.add(TotalScore2);
		 */

		add(scorePanel);

		setVisible(true);

	}

	
	//점수를 받아와 레이블에 저장시켜주는 함수1
	void getScore(int value) {

	
		if(turnOfPlayer1 == 20) {
			return;
		}

		if (turnOfPlayer1 % 2 == 0) { // 프레임의 첫번째일 경우

			hitPin = remainPin * value / 100; // 쓰러뜨릴 핀 정하기
			
			
			remainPin = remainPin - hitPin; // 남은핀 구하기
			
			if (remainPin == 0) { // 스트라이크일 경우
				System.out.println("스트라이크입니다.");
				
				System.out.println("점수는 30점"); // 추후 gui로 구현
				result.setText("스트라이크!");
				
				score1[turnOfPlayer1] = 30;
				player1Score[turnOfPlayer1].setText((Integer.toString(score1[turnOfPlayer1])));// 30점 gui, 레이블에 삽입

				if(turnOfPlayer1 / 2>0) {
					sum1[turnOfPlayer1 / 2] += sum1[turnOfPlayer1 / 2 - 1]; // 합산 점수
				}
				sum1[turnOfPlayer1 / 2] += 30;
				player1Sum[turnOfPlayer1 / 2].setText((Integer.toString(sum1[turnOfPlayer1 / 2])));//레이블에 삽입
				
				score1[turnOfPlayer1 + 1] = 0;
				player1Score[turnOfPlayer1 + 1].setText((Integer.toString(score1[turnOfPlayer1 + 1])));// 30점 gui,
																										// 레이블에 삽입

				turnOfPlayer1 += 2;
				hitPin = 0;
				remainPin = 10; // 볼링핀 변수 초기화
				return;

			}
			if (remainPin != 0) { // 스트라이크가 아닐 경우

				score1[turnOfPlayer1] = hitPin; // hitPin 으로 처음 점수 기록
				player1Score[turnOfPlayer1].setText((Integer.toString(score1[turnOfPlayer1])));// 레이블에 삽입

				System.out.println(hitPin + "이만큼 넘어뜨림 저장완료");
				result.setText(hitPin + "핀!");
				
				if(turnOfPlayer1 / 2>0) {
					sum1[turnOfPlayer1 / 2] += sum1[turnOfPlayer1 / 2 - 1]; // 합산 점수
				}
				sum1[turnOfPlayer1 / 2] += hitPin; // 합산 점수
				player1Sum[turnOfPlayer1 / 2].setText((Integer.toString(sum1[turnOfPlayer1 / 2])));//레이블에 삽입
				
				turnOfPlayer1++; // 다음 차례로
				System.out.println("남은 핀수는z"+remainPin);
				return;
			}

		}

		if (turnOfPlayer1 % 2 == 1) { // 프레임의 두번째일 경우 즉 처음에 스트라이크가 아닐 경우
			hitPin = (remainPin + 4) * value / 100; // 테스트 해 본 결과 신경써서 할 경우 value는 60이 가장 많이 나왔고, 60 두번이면 스페어가 가능하도록 구현;
			System.out.println("남은 핀수는"+remainPin);
			System.out.println(hitPin+"이만큼 넘어떠려야 하는데");
			temp = remainPin;
			remainPin = remainPin - hitPin;

			if (remainPin <= 0) { // 스페어 처리
				
				hitPin = temp;
				System.out.println(hitPin + "만큼 넘어뜨림");
				result.setText(hitPin + "핀!\n\n스페어!");
				score1[turnOfPlayer1] = hitPin;// hitPin 점수 기록
				player1Score[turnOfPlayer1].setText((Integer.toString(score1[turnOfPlayer1])));
				sum1[(turnOfPlayer1) / 2] += ( 10);// 프레임 스코어에 앞점수 더하고, 힛핀 더하지 않고, 10점 더하기
				player1Sum[turnOfPlayer1 / 2].setText((Integer.toString(sum1[turnOfPlayer1 / 2])));//레이블에 삽입
				
				turnOfPlayer1++; // 다음 차례로
				hitPin = 0;
				remainPin = 10; // 볼링핀 변수 초기화
			}

			else { // 스페어가 아닐 경우
				score1[turnOfPlayer1] = hitPin;// hitPin 점수 기록
				System.out.println(hitPin + "만큼 넘어뜨림");
				result.setText(hitPin + "핀!");
				player1Score[turnOfPlayer1].setText((Integer.toString(score1[turnOfPlayer1])));

				sum1[(turnOfPlayer1) / 2] += (hitPin);// 프레임 스코어에 앞점수 더하고, 힛핀 더하고
				player1Sum[turnOfPlayer1 / 2].setText((Integer.toString(sum1[turnOfPlayer1 / 2])));//레이블에 삽입

				turnOfPlayer1++; // 다음 차례로
				hitPin = 0;
				remainPin = 10; // 볼링핀 변수 초기화
			}
		}
		this.setVisible(true);
	}
	
	//점수를 받아와 레이블에 저장시켜주는 함수2
	void getScore2(int value) {

		if(turnOfPlayer2 == 20) {
			return;
		}

		if (turnOfPlayer2 % 2 == 0) { // 프레임의 첫번째일 경우

			hitPin = remainPin * value / 100; // 쓰러뜨릴 핀 정하기

			remainPin = remainPin - hitPin; // 남은핀 구하기
			
			if (remainPin == 0) { // 스트라이크일 경우
				System.out.println("스트라이크입니다.");
				System.out.println("점수는 30점"); // 추후 gui로 구현
				result.setText("스트라이크!");

				score2[turnOfPlayer2] = 30;
				player2Score[turnOfPlayer2].setText((Integer.toString(score2[turnOfPlayer2])));// 30점 gui, 레이블에 삽입

				if(turnOfPlayer2 / 2>0) {
					sum2[turnOfPlayer2 / 2] += sum2[turnOfPlayer2 / 2 - 1]; // 합산 점수
				}
				sum2[turnOfPlayer2 / 2] += 30;
				player2Sum[turnOfPlayer2 / 2].setText((Integer.toString(sum2[turnOfPlayer2 / 2])));//레이블에 삽입
				
				score2[turnOfPlayer2 + 1] = 0;
				player2Score[turnOfPlayer2 + 1].setText((Integer.toString(score2[turnOfPlayer2 + 1])));// 30점 gui,
																										// 레이블에 삽입

				turnOfPlayer2 += 2;
				hitPin = 0;
				remainPin = 10; // 볼링핀 변수 초기화
				return;

			}
			if (remainPin != 0) { // 스트라이크가 아닐 경우

				score2[turnOfPlayer2] = hitPin; // hitPin 으로 처음 점수 기록
				player2Score[turnOfPlayer2].setText((Integer.toString(score2[turnOfPlayer2])));// 레이블에 삽입

				System.out.println(hitPin + "이만큼 넘어뜨림 저장완료");
				result.setText(hitPin + "핀!");
				
				if(turnOfPlayer2 / 2>0) {
					sum2[turnOfPlayer2 / 2] += sum2[turnOfPlayer2 / 2 - 1]; // 합산 점수
				}
				sum2[turnOfPlayer2 / 2] += hitPin; // 합산 점수
				player2Sum[turnOfPlayer2 / 2].setText((Integer.toString(sum2[turnOfPlayer2 / 2])));//레이블에 삽입
				
				turnOfPlayer2++; // 다음 차례로
				System.out.println("남은 핀수는z"+remainPin);
				return;
			}

		}

		if (turnOfPlayer2 % 2 == 1) { // 프레임의 두번째일 경우 즉 처음에 스트라이크가 아닐 경우
			hitPin = (remainPin + 4) * value / 100; // 테스트 해 본 결과 신경써서 할 경우 value는 60이 가장 많이 나왔고, 60 두번이면 스페어가 가능하도록 구현;
			System.out.println("남은 핀수는"+remainPin);
			System.out.println(hitPin+"이만큼 넘어떠려야 하는데");
			temp = remainPin;
			remainPin = remainPin - hitPin;

			if (remainPin <= 0) { // 스페어 처리
				
				hitPin = temp;
				System.out.println(hitPin + "만큼 넘어뜨림");
				result.setText(hitPin + "핀!\n스페어!");
				score2[turnOfPlayer2] = hitPin;// hitPin 점수 기록
				player2Score[turnOfPlayer2].setText((Integer.toString(score2[turnOfPlayer2])));
				sum2[(turnOfPlayer2) / 2] += ( 10);// 프레임 스코어에 앞점수 더하고, X힛핀 더하지 않고, 10점 더하기
				player2Sum[turnOfPlayer2 / 2].setText((Integer.toString(sum2[turnOfPlayer2 / 2])));//레이블에 삽입
				
				turnOfPlayer2++; // 다음 차례로
				hitPin = 0;
				remainPin = 10; // 볼링핀 변수 초기화
			}

			else { // 스페어가 아닐 경우
				score2[turnOfPlayer2] = hitPin;// hitPin 점수 기록
				System.out.println(hitPin + "만큼 넘어뜨림");
				result.setText(hitPin + "핀!");
				player2Score[turnOfPlayer2].setText((Integer.toString(score2[turnOfPlayer2])));

				sum2[(turnOfPlayer2) / 2] += (hitPin);// 프레임 스코어에 앞점수 더하고, 힛핀 더하고
				player2Sum[turnOfPlayer2 / 2].setText((Integer.toString(sum2[turnOfPlayer2 / 2])));//레이블에 삽입

				turnOfPlayer2++; // 다음 차례로
				hitPin = 0;
				remainPin = 10; // 볼링핀 변수 초기화
			}
		}
		this.setVisible(true);
	}
	
	
	//게이지 조절을 gui로 구현
	class TimerThread extends Thread {
		ScoreBoard scoreBoard;
		int num;

		public TimerThread(ScoreBoard scoreBoard) {
			// TODO Auto-generated constructor stub
			this.scoreBoard = scoreBoard;
		}

		boolean flag = false;// false로 초기화

		public void finish() {
			flag = true;
		}// finish함수가 호출되면 종료

		public void run() {
			while (true) {

				
				for (int i = 0; i < 101; i++) {

					try {
						progressBar.setValue(i);
						num = i;
						System.out.println(i);
						Thread.sleep(3);
						if (flag == true)
							return;// 스레드 종료
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

							return;// 스레드 종료
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
				System.out.println("클릭");
				gauge2.start();
			}

			if (button.getText().equals("1p.stop!")) {

				System.out.println("클릭");
				value = gauge2.num;
				gauge2.finish();
				System.out.println("value 값은 " + value);
				scoreBoard.getScore(value);
			}
			if (button.getText().equals("2p.start!")) {
				TimerThread gauge2 = new TimerThread(scoreBoard);
				this.gauge2 = gauge2;
				System.out.println("클릭");
				gauge2.start();
			}

			if (button.getText().equals("2p.stop!")) {

				System.out.println("클릭");
				value = gauge2.num;
				gauge2.finish();
				System.out.println("value 값은 " + value);
				scoreBoard.getScore2(value);
			}
		}

	}

}
