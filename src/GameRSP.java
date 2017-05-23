import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.StyledEditorKit.ItalicAction;

//1.http://photohistory.tistory.com/4616
// 	이걸 참고해서 게임을 업그레이드 하세요
//2.	유저랑 컴퓨터가 낸 패가 지금은 문자열로 보이지만. 
//   그림으로 보이게 업그레이드 하세요 (imageView) component 참고
//3.	이밖에 더 이쁘게 할수 있으면 재량껏 하세요.
//	텍스트 정렬, 색깔, 각종크기, 기능추가 등등

@SuppressWarnings("serial")
public class GameRSP extends Frame{
	public static final int CARD_ROCK = 0;
	public static final int CARD_LIZARD = 1;
	public static final int CARD_SPOCK = 2;
	public static final int CARD_SCISSORS = 3;
	public static final int CARD_PAPER = 4;
	
	private int pointUser;
	private int pointComputer;
	
	private Panel upperGroup;
	private Panel centerGroup;
	private Panel lowerGroup;
	private Label scoreUser;
	private Label scoreComputer;
	private JLabel cardUser;
	private JLabel cardComputer;
	private Label gameStatus;
	private JButton cardRock;
	private JButton cardLizard;
	private JButton cardSpock;
	private JButton cardScissors;
	private JButton cardPaper;
	private JButton resetGame;
	
	private ImageIcon Rock;
	private ImageIcon Lizard;
	private ImageIcon Spock;
	private ImageIcon Scissors;
	private ImageIcon Paper;
	private ImageIcon Error;
	
	
	public void initImage(){
		this.Rock = new ImageIcon("Rock.jpg");
		this.Lizard = new ImageIcon("Lizard.jpg");
		this.Spock = new ImageIcon("Spock.jpg");
		this.Scissors = new ImageIcon("Scissors.jpg");
		this.Paper = new ImageIcon("Paper.jpg");
		this.Error = new ImageIcon("Error.jpg");
	}
	
	private myUIListener UIL;
	
	//function for initialize frame
	public void initFrame(){
		this.setTitle("RLSSP Game Version 1.0");
		this.setSize(640,480);
	}

	//function for initialize components
	public void initComponents(){
		this.upperGroup = new Panel();
		this.centerGroup = new Panel();
		this.lowerGroup = new Panel();
		this.resetGame = new JButton(new ImageIcon("ResetGame.jpg"));
		this.gameStatus = new Label("Game Started!!",Label.CENTER);
		this.scoreComputer = new Label("0",Label.CENTER);
		this.scoreUser = new Label("0",Label.CENTER);
		this.cardUser = new JLabel("Ready",JLabel.CENTER);
		this.cardComputer = new JLabel("Ready",JLabel.CENTER);
		this.cardRock = new JButton(new ImageIcon("RockB.jpg"));
		this.cardLizard = new JButton(new ImageIcon("LizardB.jpg"));
		this.cardSpock = new JButton(new ImageIcon("SpockB.jpg"));
		this.cardScissors = new JButton(new ImageIcon("ScissorsB.jpg"));
		this.cardPaper = new JButton(new ImageIcon("PaperB.jpg"));

		this.setLayout(new BorderLayout());
		this.add(upperGroup, BorderLayout.NORTH);
		this.add(centerGroup, BorderLayout.CENTER);
		this.add(lowerGroup, BorderLayout.SOUTH);
		this.upperGroup.setLayout(new GridLayout(2,3));
		this.centerGroup.setLayout(new GridLayout(1,3));
		this.lowerGroup.setLayout(new GridLayout(1,3));
		this.upperGroup.add(new JLabel(new ImageIcon("User.jpg")));
		this.upperGroup.add(resetGame);
		this.upperGroup.add(new JLabel(new ImageIcon("Computer.jpg")));
		this.upperGroup.add(scoreUser);
		this.upperGroup.add(gameStatus);
		this.upperGroup.add(scoreComputer);
		this.centerGroup.add(cardUser);
		this.centerGroup.add(new JLabel(new ImageIcon("Versus.jpg")));
		this.centerGroup.add(cardComputer);
		this.lowerGroup.add(cardRock);
		this.lowerGroup.add(cardLizard);
		this.lowerGroup.add(cardSpock);
		this.lowerGroup.add(cardScissors);
		this.lowerGroup.add(cardPaper);
	}

	public void initActionListeners() {
		this.UIL = new myUIListener();
		//Click listener for close main frame
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
		
		this.resetGame.setActionCommand("resetButtonClicked");
		this.resetGame.addActionListener(this.UIL);
		this.cardRock.setActionCommand("dealRock");
		this.cardRock.addActionListener(this.UIL);
		this.cardLizard.setActionCommand("dealLizard");
		this.cardLizard.addActionListener(this.UIL);
		this.cardSpock.setActionCommand("dealSpock");
		this.cardSpock.addActionListener(this.UIL);
		this.cardScissors.setActionCommand("dealScissors");
		this.cardScissors.addActionListener(this.UIL);
		this.cardPaper.setActionCommand("dealPaper");
		this.cardPaper.addActionListener(this.UIL);
	}
	
	public void newGame(){
		this.pointUser = 0;
		this.pointComputer = 0;
		this.gameStatus.setText("GameStarted");
		this.cardUser.setText("Ready");
		this.cardComputer.setText("Ready");
		this.scoreUser.setText(String.valueOf(pointUser));
		this.scoreComputer.setText(String.valueOf(pointComputer));
	}
	
	public void dealGame(int userCard){
		int computerCard = (int)((Math.random()*7)%5);
		if(userCard == computerCard){
			//when draw
			this.gameStatus.setText("Draw!!");
		}
		else if(((userCard - computerCard + 5)%5)%2 == 1){
			//when computer win
			this.pointComputer = this.pointComputer+1;
			this.gameStatus.setText("Computer Win!!");
		}
		else{
			//when user win
			this.pointUser = this.pointUser+1;
			this.gameStatus.setText("You Win!!");
		}
		this.scoreUser.setText(String.valueOf(pointUser));
		this.scoreComputer.setText(String.valueOf(pointComputer));
		this.cardUser.setText("");
		this.cardComputer.setText("");
		cardUser.setIcon(codeToImg(userCard));
		cardComputer.setIcon(codeToImg(computerCard));
	}
	
	public String codeToCard(int card){
		switch (card) {
		case GameRSP.CARD_ROCK:
			return "Rock";
		case GameRSP.CARD_LIZARD:
			return "Lizard";
		case GameRSP.CARD_SPOCK:
			return "Spock";
		case GameRSP.CARD_SCISSORS:
			return "Scissors";
		case GameRSP.CARD_PAPER:
			return "Paper";

		default:
			return "Error";
		}
	}
	
	public ImageIcon codeToImg(int card){
		switch (card) {
		case GameRSP.CARD_ROCK:
			return this.Rock;
		case GameRSP.CARD_LIZARD:
			return this.Lizard;
		case GameRSP.CARD_SPOCK:
			return this.Spock;
		case GameRSP.CARD_SCISSORS:
			return this.Scissors;
		case GameRSP.CARD_PAPER:
			return this.Paper;

		default:
			return this.Error;
		}
	}
	
	public GameRSP() throws HeadlessException {
		super();
		this.initImage();
		this.initFrame();
		this.initComponents();
		this.initActionListeners();
		this.newGame();
	}
	
	class myUIListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command.equals("resetButtonClicked")){
				newGame();
				System.out.println("newgame button clicked!!!");
			}
			else if(command.equals("dealRock")){
				dealGame(GameRSP.CARD_ROCK);
			}
			else if(command.equals("dealLizard")){
				dealGame(GameRSP.CARD_LIZARD);
			}
			else if(command.equals("dealSpock")){
				dealGame(GameRSP.CARD_SPOCK);
			}
			else if(command.equals("dealScissors")){
				dealGame(GameRSP.CARD_SCISSORS);
			}
			else if(command.equals("dealPaper")){
				dealGame(GameRSP.CARD_PAPER);
			}
		}
		
	}

}
