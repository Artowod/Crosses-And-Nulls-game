package crossesAndNulls;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CrossAndNull extends JFrame{
	private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
	private JButton jButtonWinner1,jButtonWinner2,jButtonWinner3;

	private JTextField tField;
	private JPanel topPanel,mainPanel, bottomPanel;
	private JLabel jLabelForResult,jLabelForParameters;
	private double operand1,operand2,result; 
	private int actIndex=0; //Индекс показывающий что любая кнопка действия уже была нажата (1) или еще ни разу не нажата (0)
	private int sw=0; // переключаель кот показывает нажата уже была кнопка действия(* / - + ) или нет 0 - нет , 1 - да
	private int step=2, stepsCount=0;	
	private int[][] mas=new int[3][3];
	
	CrossAndNull(){
		super("Crosses&Nulls");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		
		ActionListener bAction = new action(); //остальные кнопки такие как Result, Clear
				
		b1=new JButton();
		b2=new JButton();
		b3=new JButton();
		b4=new JButton();
		b5=new JButton();
		b6=new JButton();
		b7=new JButton();
		b8=new JButton();
		b9=new JButton();

		
		b1.addActionListener(bAction);		
		b2.addActionListener(bAction);
		b3.addActionListener(bAction);
		b4.addActionListener(bAction);
		b5.addActionListener(bAction);
		b6.addActionListener(bAction);
		b7.addActionListener(bAction);
		b8.addActionListener(bAction);
		b9.addActionListener(bAction);

		jLabelForResult=new JLabel("Please put \"X\" ");
		jLabelForResult.setFont(new Font("Arial Bold",0,16));
		
		jLabelForParameters=new JLabel("Step: ");
		
		topPanel=new JPanel();
		//topPanel.setMinimumSize(new Dimension(300,50));
		mainPanel=new JPanel();
		GridLayout mainGL=new GridLayout(3,3);
		mainGL.setHgap(2);
		mainGL.setVgap(2);
		mainPanel.setLayout(mainGL);
		
		topPanel.add(jLabelForResult);		

		mainPanel.add(b1);
		mainPanel.add(b2);
		mainPanel.add(b3);
		mainPanel.add(b4);
		mainPanel.add(b5);
		mainPanel.add(b6);
		mainPanel.add(b7);
		mainPanel.add(b8);
		mainPanel.add(b9);

		getContentPane().add(topPanel,"North");
		getContentPane().add(mainPanel);		

		setResizable(false); // Запретить изменять размер фрейма
		setSize(300,350); // задаём размер фрейма
		setVisible(true); //отобразить фрейм
		setLocationRelativeTo(null); //расположение фрейма в центре окна
		
	}

	private void mainPerforming(JButton activeButton, int crossOrNull)
	{
		if (activeButton==b1) mas[0][0]=crossOrNull;
		if (activeButton==b2) mas[1][0]=crossOrNull;
		if (activeButton==b3) mas[2][0]=crossOrNull;
		if (activeButton==b4) mas[0][1]=crossOrNull;
		if (activeButton==b5) mas[1][1]=crossOrNull;
		if (activeButton==b6) mas[2][1]=crossOrNull;
		if (activeButton==b7) mas[0][2]=crossOrNull;
		if (activeButton==b8) mas[1][2]=crossOrNull;
		if (activeButton==b9) mas[2][2]=crossOrNull;
	}
	
	public class action implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton activeButton= (JButton)arg0.getSource();
			activeButton.setFont(new Font("Arial Bold",0,40));
			if(activeButton.getText()==""){
				stepsCount++;
			if(step==2){
				activeButton.setText("X");
				mainPerforming(activeButton,step);
				jLabelForResult.setText("Please put \"O\" ");
				step=1; 
			}
			else{
				activeButton.setText("O");
				mainPerforming(activeButton,step);
				jLabelForResult.setText("Please put \"X\" ");
				step=2;
			}
			}
			System.out.println(stepsCount+"---------------");
			sop();
			if(getResult()){
				System.out.println(getResult());
				if(step==1){
					jLabelForResult.setText("X is WINNER !");
		    		jButtonWinner1.setBackground(Color.GREEN);
		    		jButtonWinner2.setBackground(Color.GREEN); 
		    		jButtonWinner3.setBackground(Color.GREEN);	
				}
				if(step==2){
					jLabelForResult.setText("O is WINNER !");
		    		jButtonWinner1.setBackground(Color.GREEN);
		    		jButtonWinner2.setBackground(Color.GREEN); 
		    		jButtonWinner3.setBackground(Color.GREEN);					
				}
			} else{
				if(stepsCount==9){
					jLabelForResult.setText("No winners. Lets try again.");
					b1.setBackground(Color.GRAY);
					b2.setBackground(Color.GRAY);
					b3.setBackground(Color.GRAY);
					b4.setBackground(Color.GRAY);
					b5.setBackground(Color.GRAY);
					b6.setBackground(Color.GRAY);
					b7.setBackground(Color.GRAY);
					b8.setBackground(Color.GRAY);
					b9.setBackground(Color.GRAY);				
				}	
			}
			
			
		}
	}

	
	private void sop() //выводим все поле
	{ String q,w,e;
		for(int y=0;y<3;y++){
		if (mas[0][y]==1) q="O"; else if (mas[0][y]==2) q="X"; else q=" ";
		if (mas[1][y]==1) w="O"; else if (mas[1][y]==2) w="X"; else w=" ";
		if (mas[2][y]==1) e="O"; else if (mas[2][y]==2) e="X"; else e=" ";
			System.out.println(q+"|"+w+"|"+e);
		}
	}

	
	private boolean getResult()
	{ boolean b=false;
		for (int x=0; x<3;x++){
	    if (mas[x][0]==1 && mas[x][1]==1 && mas[x][2]==1) {  //X - проверка столбцов
	    	if(x==0){
	    		jButtonWinner1=b1;
	    		jButtonWinner2=b4; 
	    		jButtonWinner3=b7;
	    		} 
	    	if(x==1){
	    		jButtonWinner1=b2;
	    		jButtonWinner2=b5; 
	    		jButtonWinner3=b8;	    		
	    	}
	    	if(x==2){
	    		jButtonWinner1=b3;
	    		jButtonWinner2=b6; 
	    		jButtonWinner3=b9;	    		
	    	}	    	
	    	b=true;
	    }
		if (mas[x][0]==2 && mas[x][1]==2 && mas[x][2]==2) {//O - проверка столбцов
	    	if(x==0){
	    		jButtonWinner1=b1;
	    		jButtonWinner2=b4; 
	    		jButtonWinner3=b7;
	    	} 
	    	if(x==1){
	    		jButtonWinner1=b2;
	    		jButtonWinner2=b5; 
	    		jButtonWinner3=b8;	    		
	    	}
	    	if(x==2){
	    		jButtonWinner1=b3;
	    		jButtonWinner2=b6; 
	    		jButtonWinner3=b9;	    		
	    	}	    	
			b=true;
		}
		if (mas[0][x]==1 && mas[1][x]==1 && mas[2][x]==1) { //X - проверка строк
	    	if(x==0){
	    		jButtonWinner1=b1;
	    		jButtonWinner2=b2; 
	    		jButtonWinner3=b3;
	    		} 
	    	if(x==1){
	    		jButtonWinner1=b4;
	    		jButtonWinner2=b5; 
	    		jButtonWinner3=b6;	    		
	    	}
	    	if(x==2){
	    		jButtonWinner1=b7;
	    		jButtonWinner2=b8; 
	    		jButtonWinner3=b9;	    		
	    	}	
			b=true;
		}
		if (mas[0][x]==2 && mas[1][x]==2 && mas[2][x]==2){ //O - проверка строк
	    	if(x==0){
	    		jButtonWinner1=b1;
	    		jButtonWinner2=b2; 
	    		jButtonWinner3=b3;
	    	} 
	    	if(x==1){
	    		jButtonWinner1=b4;
	    		jButtonWinner2=b5; 
	    		jButtonWinner3=b6;	    		
	    	}
	    	if(x==2){
	    		jButtonWinner1=b7;
	    		jButtonWinner2=b8; 
	    		jButtonWinner3=b9;	    		
	    	}				
			b=true;
		} 
		if (mas[0][0]==1 && mas[1][1]==1 && mas[2][2]==1){ //X - проверка диагонали
			b=true;
	    		jButtonWinner1=b1;
	    		jButtonWinner2=b5; 
	    		jButtonWinner3=b9;
	    }
		if (mas[0][0]==2 && mas[1][1]==2 && mas[2][2]==2){ //O - проверка диагонали
			b=true;
    		jButtonWinner1=b1;
    		jButtonWinner2=b5; 
    		jButtonWinner3=b9;
		}
		if (mas[2][0]==1 && mas[1][1]==1 && mas[0][2]==1){ //X - проверка диагонали
			b=true;
    		jButtonWinner1=b3;
    		jButtonWinner2=b5; 
    		jButtonWinner3=b7;
		}
		if (mas[2][0]==2 && mas[1][1]==2 && mas[0][2]==2){ //O - проверка диагонали
			b=true;
			jButtonWinner1=b3;
			jButtonWinner2=b5; 
			jButtonWinner3=b7;
		}
		}
		return b;
	}

	public static void main(String[] args){
		new CrossAndNull();
		
	}

}
