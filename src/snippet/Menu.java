package snippet;
import java.util.Scanner;
public class Menu {
    private Scanner sc=new Scanner(System.in);
    private int selection;
    private String[] menuStatements;


    public Menu(String[] menuStatements) {
        this.menuStatements = menuStatements;
    }


    public int writeMenu() {
    	int b=0;
    	do{try{
    		b=0;
        System.out.println("----------------------------------------o--------------------------------------");
        for (int i = 1; i <=menuStatements.length ; i++) {
            System.out.println(i+" - "+menuStatements[i-1]);
        }
        System.out.println("----------------------------------------o--------------------------------------");
        System.out.println("Enter Your Choice :");
        selection=sc.nextInt();
        }catch(Exception e){System.out.println("Wrong Entry!!!"); b=1;sc.nextLine();}
    	}while(b==1);
        return selection;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public String[] getMenuStatements() {
        return menuStatements;
    }

    public void setMenuStatements(String[] menuStatements) {
        this.menuStatements = menuStatements;
    }
}
