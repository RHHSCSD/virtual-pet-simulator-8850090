/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
/**
 *
 * @author cohen
 */
public class VirtualPet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //initialization
        Scanner in = new Scanner(System.in);
        //declare variables
        String menuSelect;
        int menuNum = 0;
        //splash screen
        System.out.println("");
        System.out.println("Pet Simulator");
        System.out.println("");
        System.out.println("    |\\__/,|   ('\\");
        System.out.println("  _ |o  o |_   ) )");
        System.out.println("-(((---(((--------");
        System.out.println("");
        System.out.println("1.Start");
        System.out.println("2.Instructions");
        System.out.println("3.Exit");
        System.out.println("");
        menuSelect = in.next();
        menuSelect = menuSelect.toLowerCase();
        if (menuSelect.length() == 1)
        {
            menuNum = menuSelect.charAt(0);
        }
        else if (menuSelect.equals("start"))
        {
            menuNum = 1;
        }
        else if (menuSelect.equals("instructions"))
        {
            menuNum = 2;
        }
        else if (menuSelect.equals("exit"))
        {
            menuNum = 3;
        }
        System.out.print(menuSelect);
        switch (menuNum)
        {
            case 1: break;
            case 2: break;
            case 3: System.exit(0);
        }
    }
    
}
