package snippet;

import java.util.Scanner;

import enigma.core.Enigma;

public class MainMet {

	public static enigma.console.Console console = Enigma
			.getConsole("TRESAURE MAZE");
	static Scanner selection = new Scanner(System.in);

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int choice;
        String[] menuItems={"New Game","Load Maze","Exit"};
        Menu menu=new Menu(menuItems);
		while (true) {
			do{
				choice=menu.writeMenu();
			}while(choice!=1&&choice!=2&&choice!=3);

			switch (choice) {
			case 1:
			{	console.getTextWindow().setCursorPosition(0, 0);
				Game myGame = new Game();
				break;
			}

			case 2:{
				
				break;
			}
			
			case 3:
			{
				System.exit(0);
			}

		}
	}
}
}
