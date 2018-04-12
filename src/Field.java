import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Field {
	//ゲームシステムを宣言
	int point=500;
	int time = 1000*60*5;//5分
	ImageIcon nomalicon = new ImageIcon("nomal.png");
	ImageIcon safeicon = new ImageIcon("safe.png");
	ImageIcon bombicon = new ImageIcon("bomb.png");

	//BufferedImage img = ImageIO.read(new File("nomal.png"));


	//定数を宣言
	final int length=10;
	final int  buttonWidth=50,buttonHeight=buttonWidth;
	int  xi=700,yi=300;


	int width=0,height=0;

	//コンポーネントを宣言
	Panel[][] panels =	 new Panel[length][length];

	/*JTextArea[] area_columns = new JTextArea[length] ;
	JTextArea[] area_row = new JTextArea[length] ;
	 */

	JEditorPane[] area_columns = new JEditorPane[length] ;
	JEditorPane[] area_row = new JEditorPane[length] ;

	JFrame mainFrame= new JFrame("frame");
	JPanel mainPanel = new JPanel();
	JPanel parent= new JPanel();

	JPanel startpanel = new JPanel();
	JButton startbutton = new JButton("start");





	public void main() {
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		mainPanel.setLayout(null);
		setPanels();

		setAreas();

		makeHint();

		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);

	}

	public void setAreas() {
		int l=150;
		int xc,yc,xr,yr;
		xc=xi;yc=yi-l;
		xr=xi-l;yr=yi;
		int textsize = 40;
		for(int i=0;i<length;i++) {
			//	area_columns[i]= new JTextArea();
			area_columns[i]= new JEditorPane();
			area_columns[i].setBounds(xc, yc, buttonWidth, l);
			area_columns[i].setFont(new Font("ＭＳ ゴシック", Font.BOLD, textsize));
			area_columns[i].setFocusable(false);

			//	area_row[i]= new JTextArea();
			area_row[i]= new JEditorPane();
			area_row[i].setBounds(xr, yr, l, buttonWidth);
			area_row[i].setFont(new Font("ＭＳ ゴシック", Font.BOLD, textsize));
			area_row[i].setFocusable(false);

			mainPanel.add(area_columns[i]);
			mainPanel.add(area_row[i]);

			xc+=buttonWidth;
			yr+=buttonHeight;
		}

	}

	public void makeHint() {
		makeHint_Column();
		makeHint_Row();
	}
	public void makeHint_Column() {
		String string="";
		int num=0;

		for(int j=0;j<length;j++) {
			for(int i=0;i<length;i++) {
				if(!panels[i][j].Bomb) {
					num++;
				}
				else {
					if(num!=0) {
						string+=String.valueOf(num)+"\r\n";
						num=0;
					}
				}

			}
			if(string =="") {
				area_columns[j].setText("0");
			}else {

				area_columns[j].setText(string);
			}
			num=0;
			string="";

		}
	}

	public void makeHint_Row() {
		String string="";
		int num=0;

		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				if(!panels[i][j].Bomb) {
					num++;
				}
				else {
					if(num!=0) {
						string+=String.valueOf(num)+" ";
						num=0;
					}
				}

			}if(string =="") {
				area_row[i].setText("0");
			}else {

				area_row[i].setText(string);
			}
			num=0;
			string="";

		}

	}


	public void setPanels() {

		int x=xi,y=yi;
		Random random = new Random();
		Clicked clicked = new Clicked();
		System.out.println("hello");
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				panels[i][j]=new Panel(nomalicon);

				panels[i][j].setBounds(x,y,buttonWidth, buttonHeight);
				panels[i][j].setBorderPainted(true);
				panels[i][j].setForeground(Color.yellow);
				panels[i][j].addActionListener(clicked);
				//panels[i][j].setIcon(nomalicon);

				if(random.nextInt() % 100 >60) {
					panels[i][j].Bomb=true;
				}
				mainPanel.add(panels[i][j]);
				x+=buttonWidth;
			}
			y+=buttonHeight;
			x=xi;

		}
	}

	class  Clicked  extends Panel implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			Panel temp = (Panel) e.getSource();
			if(!temp.clicked) {
				if(temp.Bomb) {
					point =point -100;
				}else {
					point =point +50;
				}
				temp.clicked=true;
			}

		}

	}
	public class timer implements Runnable {

		@Override
		public void run() {
			time=time-1;
			point =point -1;
		}
	}


}
