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
        Random rng = new Random();
        //declare variables
        String menuSelect;
        int menuNum = 0;
        String petType;
        String petName;
        String user;
        String password;
        //splash screen
        System.out.println("");
        System.out.println("Pet Simulator");
        System.out.println("");
        System.out.println("    |\\__/,|   ('\\");
        System.out.println("  _ |o  o |_   ) )");
        System.out.println("-(((---(((--------");
        System.out.println("");
        //login
        System.out.print("Enter username: ");
        user = in.nextLine();
        System.out.print("Enter password: ");
        password = in.nextLine();
        System.out.println("");
        if (!user.equals("snoopy") || !password.equals("toto"))
        {
            System.exit(0);
        }
        //start menu
        System.out.println("Welcome to Pet Simulator!");
        System.out.println("");
        System.out.println("1.Start");
        System.out.println("2.Instructions");
        System.out.println("3.Exit");
        System.out.println("");
        //check user input
        menuSelect = in.nextLine();
        menuSelect = menuSelect.toLowerCase();
        if (menuSelect.length() == 1)
        {
            menuNum = menuSelect.charAt(0)-48;
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
        //switch statement based on user input
        System.out.println(menuSelect.charAt(0)-48);
        switch (menuNum)
        {
            case 3: System.exit(0);
            case 2: break;
            case 1:
                //if user decides to start the game (pet selection & info)
                System.out.println("\nSelect your pet(dog/cat):");
                petType = in.nextLine();
                petType = petType.toLowerCase();
                if (!petType.equals("dog") && !petType.equals("cat"))
                {
                    System.exit(0);
                }
                System.out.println("\nYou selected "+petType+". Type "+petType+" again to confirm your choice.");
                if (!petType.equals(in.nextLine().toLowerCase()))
                {
                    System.out.println("\nConfirmation failed.");
                    System.exit(0);
                }
                System.out.println("\nSelect a name for your pet(or write nothing for a randomly generated name)");
                petName = in.nextLine();
                if (petName.equals(""))
                {
                    int petNameLen = rng.nextInt(6);
                    final String vowels = "aeiou";
                    final String consonant = "bcdfghjklmnpqrstvwxyz";
                    petName += consonant.charAt(rng.nextInt(22)-1);
                    petName += vowels.charAt(rng.nextInt(6)-1);
                    petName += consonant.charAt(rng.nextInt(22)-1);
                    petName += vowels.charAt(rng.nextInt(6)-1);
                    if (petNameLen > 1)
                    {
                        petName += consonant.charAt(rng.nextInt(22)-1);
                        if (petNameLen > 2)
                        {
                            petName += vowels.charAt(rng.nextInt(6)-1);
                            if (petNameLen > 3)
                            {
                                petName += consonant.charAt(rng.nextInt(22)-1);
                                if (petNameLen > 4)
                                {
                                    petName += vowels.charAt(rng.nextInt(6)-1);
                                }
                            }
                        }
                    }
                }
                System.out.println("\nYour pet, named "+petName+", has been born!");
                break;
        }
    }
    
}
