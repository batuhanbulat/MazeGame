package snippet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import enigma.console.TextAttributes;
import enigma.core.Enigma;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Game {
	public enigma.console.Console cn = Enigma.getConsole("Treasure Maze");
	public KeyListener klis;
	static char[][] b = new char[21][55];
	private long time;
	Queue que16=new Queue(9999999);
	Queue que8=new Queue(9999999);
Queue tempQue=new Queue(9999999);
    int[] numbers={1,1,1,1,1,1,1,1,2,2,2,2,3,3,4};
    Random rnd = new Random();
    int queCounter=0;
    int numberCounter=0;
    boolean firstRound=true;
    Score score=new Score(0);
    Game() throws Exception { // --- Contructor

		// ------ Standard code for mouse and keyboard ------ Do not change

		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}

			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
		ReadFromFile();
		
		
		for (int u = 0; u < 8; u++) {
			que8.Enqueue(numbers[rnd.nextInt(15)]);
			
		}	
		while(numberCounter!=8){
			int nx = rnd.nextInt(20);
			int ny = rnd.nextInt(20);
			while (!(b[ny][nx] != '#')) {
				ny = rnd.nextInt(20);
				nx = rnd.nextInt(20);
			}
			Integer myIntegerObject = new Integer((Integer) que8.Dequeue());
			String myString = myIntegerObject.toString();
			char ch=myString.charAt(0);
			b[ny][nx]=ch;				
			numberCounter++;
		}
		
		
		
		
		writeScreen();

		int px = rnd.nextInt(20);
		int py = rnd.nextInt(20);
		while (!(b[py][px] != '#')) {
			py = rnd.nextInt(20);
			px = rnd.nextInt(20);

		}
		// int px = 5, py = 5;
		int epx, epy;
		TextAttributes attrs = new TextAttributes(Color.BLUE, Color.CYAN);
		cn.setTextAttributes(attrs);
		cn.getTextWindow().output(px, py, 'P', attrs);
		time = System.currentTimeMillis();
			
		while (true) {
			long time1=(System.currentTimeMillis()-time)/1000;
			cn.getTextWindow().setCursorPosition(58, 10);
			System.out.print("Queue :");
			while ((Integer)que16.size()!=16||firstRound==true) {
				int number1=numbers[rnd.nextInt(15)];
				que16.Enqueue(number1);
				if(firstRound==true&&que16.size()<9){
				if(que16.size()<8)
					System.out.print(number1);
				else
					System.out.print(number1);
				}
				queCounter++;
				if(queCounter>7){
				while((Integer)que8.size()!=8){
					System.out.print(que16.Peek());
					que8.Enqueue(que16.Dequeue());
					queCounter--;
				    }
				}
			if((Integer)que16.size()!=16)
	            firstRound=false;
			}

			if(numberCounter<8&&time1%10==0)
			{
				int nx1 = rnd.nextInt(20);
				int ny1 = rnd.nextInt(20);
				while (b[ny1][nx1]=='#') {
					ny1 = rnd.nextInt(20);
					nx1 = rnd.nextInt(20);
				}
				
				que8.Enqueue(numbers[rnd.nextInt(15)]);
				Integer myIntegerObject1 = new Integer((Integer) que8.Dequeue());
				String myString1 = myIntegerObject1.toString();
				char ch1=myString1.charAt(0);
				b[ny1][nx1]=ch1;
				cn.getTextWindow().output(nx1, ny1, ch1);

				numberCounter++;
				
			}
			cn.getTextWindow().setCursorPosition(58, 10);
			System.out.print("Queue :              ");
			cn.getTextWindow().setCursorPosition(65, 10);
            for (int o = 0; o < que8.size(); o++) {
            	if(o!=(Integer) que8.size()-1)
            	System.out.print(que8.Peek()+" ");
            	else
            		System.out.print(que8.Peek());
            	tempQue.Enqueue(que8.Dequeue());
            	que8.Enqueue(tempQue.Dequeue());
			}
			epx = px;
			epy = py;
			if (keypr == 1) { // if keyboard button pressed
				if (rkey == KeyEvent.VK_LEFT && (b[py][px - 1] != '#')) {
					px--;
					changeScore(px, py);
				}
				if (rkey == KeyEvent.VK_RIGHT && (b[py][px + 1] != '#')){
					px++;
					changeScore(px, py);
				}
				if (rkey == KeyEvent.VK_UP && (b[py - 1][px] != '#')){
					py--;
					changeScore(px, py);
				}
				if (rkey == KeyEvent.VK_DOWN && (b[py + 1][px] != '#')){
					py++;
					changeScore(px, py);
				}
				writeScore();
				cn.getTextWindow().output(epx, epy, ' ');

				cn.getTextWindow().output(px, py, 'P', attrs); // VK kullanmadan
																// test
				// teknigi
				keypr = 0; // last action
			}
			Thread.sleep(20);
			boolean flag=true;
			while (flag){
				TextAttributes attrp = new TextAttributes(Color.WHITE, Color.BLACK);
				cn.setTextAttributes(attrp);
				writeTimer();
				flag=false;
				}
		}
		
	}
	public void changeScore(int px, int py) {
		if(b[py][px] == '1'){
			score.setScore(score.getScore()+2);
			numberCounter--;
		}
		else if(b[py][px] == '2'){
			score.setScore(score.getScore()+4);
			numberCounter--;
		}
		else if(b[py][px] == '3'){
			score.setScore(score.getScore()+8);
			numberCounter--;
		}
		else if(b[py][px] == '4'){
			score.setScore(score.getScore()+16);
			numberCounter--;
		}
		b[py][px]=' ';
	}
	public void writeScore() {
		cn.getTextWindow().setCursorPosition(58, 14);
		System.out.print("Score:          ");
		cn.getTextWindow().setCursorPosition(58, 14);
		System.out.print("Score:"+score.getScore());
	}
	public void writeTimer() {
		cn.getTextWindow().setCursorPosition(58, 6);
		System.out.println("Time: "+((System.currentTimeMillis() - time) / 1000));

	}

	public void ReadFromFile() throws IOException {
		FileReader in = new FileReader("mazelock.txt");
		BufferedReader br = new BufferedReader(in);
		String a = "";

		int line = 0;
		while (true) {
			if (!br.ready()) {
				break;

			}
			a = br.readLine();

			for (int i = 0; i < a.length(); i++) {

				b[line][i] = a.charAt(i);
			}
			line++;
		}
		in.close();
		

	}

	public void writeScreen(){
		for (int i = 0; i < b.length; i++) {

			for (int j = 0; j < b[i].length; j++) {
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
	
		cn.getTextWindow().setCursorPosition(58, 2);
		System.out.println("Input: ");
		cn.getTextWindow().setCursorPosition(58, 14);
		System.out.println("Score: ");
	}

	// Reader rd = new Reader();

	public int keypr; // key pressed?
	public int rkey; // key (for press/release)



}
