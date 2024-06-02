/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
import java.io.*;
/**
 *
 * @author cohen
 */
public class VirtualPet {
    
    //initialization
    public static Scanner in = new Scanner(System.in);
    public static Random rng = new Random();
    public static String user;
    public static String password = "";
        
    public static String changeChar(String var, int pos, String replace)
    {
        var = var.substring(0,pos)+replace+var.substring(pos+1,var.length());
        return var;
    }
    
    public static int mainMenu(boolean isSaved)
    {
        String menuSelect;
        int menuNum = 0;
        System.out.println("");
        if (isSaved)
            System.out.println("1.Play/Interact");
        else
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
            if (0 > menuNum || menuNum > 3)
            {
                System.out.println("\nInvalid input");
            }
        }
        else if (!isSaved && menuSelect.equals("start"))
        {
            menuNum = 1;
        }
        else if (isSaved && (menuSelect.equals("play") || menuSelect.equals("interact")))
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
        else
        {
            System.out.println("\nInvalid input");
        }
        //switch statement based on user input
        return menuNum;
    }
    
    public static boolean login()
    {
        System.out.print("Enter username(no special characters): ");
        user = in.nextLine();
        System.out.print("Enter password(no special characters): ");
        password = in.nextLine();
        System.out.println("");
        if (!user.equals("") && !password.equals(""))
        {
            try {
                File f = new File(user+".txt");
                //if the user already has a saved file
                if (f.exists())
                {
                    Scanner read = new Scanner(f);
                    //if the password is not the correct password
                    if (!password.equals(read.nextLine()))
                    {
                        System.out.println("Incorrect password\n");
                        password = "";
                    }
                    read.close();
                }
                else
                {
                    return true;
                }
            }
            catch (Exception e) {
                System.out.println("Error! Cannot access file\n");
                System.exit(0);
            }
        }
        return false;
    }
    
    public static String namePet(String petName)
    {
        int petNameLen = rng.nextInt(5)+4;
        final String vowels = "aeiou";
        final String consonant = "bcdfghjklmnpqrstvwxyz";
        for (int i=0; i<petNameLen; i++)
        {
            if (i%2 == 0)
            {
                petName += vowels.charAt(rng.nextInt(5));
            }
            else
            {
                petName += consonant.charAt(rng.nextInt(21));
            }
            if (rng.nextInt(petNameLen) == 0)
            {
                int doubleLetter = rng.nextInt(petName.length());
                petName = changeChar(petName,doubleLetter,""+petName.charAt(doubleLetter)+petName.charAt(doubleLetter));
            }
        }
        return petName;
    }
    
    public static int guessingGame()
    {
        //variables
        int randNum;
        int guessNum;
        int earned = 0;
        //start game
        System.out.println("\nGuess the number between 1 and 100! You have 10 guesses left");
        randNum = rng.nextInt(100)+1;
        guessNum = in.nextInt();
        for (int i=9; i>=0; i--)
        {
            //ran out of guesses
            if (i == 0)
            {
                earned = rng.nextInt(9)+1;
                System.out.println("\nOut of Guesses. You earned $"+earned);
            }
            else
            {
                //tell user if the guessed number is too high/low
                if (guessNum > randNum)
                {
                    System.out.println("\nToo high! You have "+i+" guess(es) left");
                }
                else if (guessNum < randNum)
                {
                    System.out.println("\nToo low! You have "+i+" guess(es) left");
                }
                //user guesses number
                guessNum = in.nextInt();
                //if user guesses correctly
                if (guessNum == randNum)
                {
                    earned = i*20+rng.nextInt(10);
                    System.out.println("\nCorrect! You guessed the number! You earned $"+earned+"!");
                    break;
                }
            }
        }
        return earned;
    }
    
    public static int matchingGame()
    {
        //uninitialized variables
        int randNum;
        int earned = 0;
        int revealed;
        int selected;
        //game setup
        int matches = 0;
        String cards = "JJQQKKAA$$";
        String hiddenCards = "XXXXXXXXXX";
        String shuffledCards = "";
        //shuffle deck
        for (int i=0; i<10; i++)
        {
            randNum = rng.nextInt(cards.length());
            shuffledCards += cards.charAt(randNum);
            cards = cards.substring(0,randNum)+cards.substring(randNum+1,cards.length());
        }
        //start game
        System.out.println("\nTry to find all the matching pairs! Guess cards by entering it's position in the deck\n");
        for (int i=10; i>=0; i--)
        {
            if (i == 0)
            {
                earned = rng.nextInt(10)+15;
                System.out.println("Out of guesses. You earned $"+earned);
            }
            else
            {
                //user guesses first card
                System.out.println("Guesses left: "+i+"\n");
                System.out.println(hiddenCards);
                selected = in.nextInt();
                if (0 <= selected && selected < shuffledCards.length() && hiddenCards.charAt(selected) == 'X')
                {
                    //user guesses second card, first card is revealed
                    hiddenCards = changeChar(hiddenCards,selected,""+shuffledCards.charAt(selected));
                    revealed = selected;
                    System.out.println(hiddenCards);
                    selected = in.nextInt();
                    if (0 <= selected && selected < shuffledCards.length() && hiddenCards.charAt(selected) == 'X')
                    {
                        hiddenCards = changeChar(hiddenCards,selected,""+shuffledCards.charAt(selected));
                        System.out.println(hiddenCards);
                        System.out.println();
                        //if the cards guessed by the user match
                        if (hiddenCards.charAt(selected) == hiddenCards.charAt(revealed))
                        {
                            System.out.print("Correct! ");
                            matches++;
                            if (matches == 5)
                            {
                                earned = i*40+rng.nextInt(10);
                                System.out.println("You matched all pairs! You earned $"+earned);
                                break;
                            }
                        }
                        //if the cards guessed by the user dont match
                        else
                        {
                            hiddenCards = changeChar(hiddenCards,selected,"X");
                            hiddenCards = changeChar(hiddenCards,revealed,"X");
                        }
                    }
                    //if user guesses a card that is out of range or already matched
                    else
                    {
                        System.out.println("\nInvalid guess");
                        //if an unmatched card was already revealed before an invalid guess, hide card
                        if (0 > selected || selected >= shuffledCards.length() || hiddenCards.charAt(selected) != 'X')
                        {
                            hiddenCards = changeChar(hiddenCards,revealed,"X");
                        }
                        i++;
                    }
                }
                //if user guesses a card that is out of range or already matched
                else
                {
                    System.out.println("\nInvalid guess");
                    i++;
                }
            }
        }
        return earned;
    }
    
    public static int toyPet(int max, int energy)
    {
        System.out.println("You gave your pet a toy. Your pet gains energy!");
        energy += 5;
        if (energy > max)
        {
            energy = max;
        }
        return energy;
    }
    
    public static int feedPet(int max, int food)
    {
        System.out.println("You fed your pet. Your pet gains saturation!");
        food += 10;
        if (food > max)
        {
            food = max;
        }
        return food;
    }
    
    public static int groomPet(int max, int health)
    {
        System.out.println("You groomed your pet. Your pet is now clean and at full health!");
        health = max;
        return health;
    }
    
    public static int netBeansStupid(int intPos)
    {
        int nextInt;
        int correctInt;
        try {
            //iterate through a file(10 lines long) for a specific int variable
            File f = new File(user+".txt");
            Scanner read = new Scanner(f);
            read.nextLine();
            read.nextLine();
            read.nextLine();
            for (int i=0; i<10; i++)
            {
                nextInt = read.nextInt();
                if (i == intPos)
                {
                    correctInt = nextInt;
                    read.close();
                    return correctInt;
                }
            }
        }
        catch (Exception e) {
            //if an exception occurs, do nothing.
        }
        return 0;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //declare final variables (constants)
        final int[] maxStats = new int[3]; //[MAXHEALTH,MAXFOOD,MAXENERGY]
        //declare variables
        int menuNum;
        int statPool = 40;
        int money = 0;
        int[] currentStats = new int[3]; //[health,food,energy]
        int[] actions = {0,0,0}; //[groom,feed,toy]
        boolean inMenu = true;
        boolean newFile = true;
        String petType;
        String petName;
        //splash screen
        System.out.println("");
        System.out.println("Pet Simulator");
        System.out.println("");
        System.out.println("    |\\__/,|   ('\\");
        System.out.println("  _ |o  o |_   ) )");
        System.out.println("-(((---(((--------");
        System.out.println("");
        //login
        while (password.equals(""))
            newFile = login();
        //start menu
        System.out.println("Welcome to Pet Simulator!");
        if (newFile)
        {
            while (inMenu)
            {
                menuNum = mainMenu(false);
                //switch statement based on user input
                switch (menuNum)
                {
                    case 3: System.exit(0);
                    case 2: System.out.println("\nHow to start:\ntype \"Start\" to create your pet if you havent already, and if you have,\nyou will be taken directly to your pet simulator!"); break;
                    case 1: inMenu = false; break;
                }
            }
            //if user decides to start the game (pet selection)
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
            //prompt user for pet name
            System.out.println("\nSelect a name for your pet(or write nothing for a randomly generated name)");
            petName = in.nextLine();
            //randomly generated name if user decides not to enter a name
            if (petName.equals(""))
            {
                petName = namePet(petName);
            }
            System.out.println("\nYour pet, named "+petName+", has been born!");
            //pet stats
            maxStats[0] = rng.nextInt(9)+10;
            statPool -= maxStats[0];
            maxStats[1] = rng.nextInt(statPool-20)+10;
            statPool -= maxStats[1];
            maxStats[2] = statPool;
            currentStats[0] = maxStats[0];
            currentStats[1] = maxStats[1];
            currentStats[2] = maxStats[2];
            try {
                //write a file for all of the user's pet information
                File f = new File(user+".txt");
                PrintWriter write = new PrintWriter(f);
                write.println(password);
                write.println(petType);
                write.println(petName);
                write.println(maxStats[0]);
                write.println(currentStats[0]);
                write.println(maxStats[1]);
                write.println(currentStats[1]);
                write.println(maxStats[2]);
                write.println(currentStats[2]);
                write.println(money);
                write.close();
            }
            catch (Exception e) {
                System.out.println("\nError! Could not save data");
                System.exit(0);
            }
        }
        else
        {
            try {
                //read a file containing all of the user's saved pet information
                File f = new File(user+".txt");
                Scanner read = new Scanner(f);
                password = read.nextLine();
                petType = read.nextLine();
                petName = read.nextLine();
                read.nextInt();
                currentStats[0] = read.nextInt();
                read.nextInt();
                currentStats[1] = read.nextInt();
                read.nextInt();
                currentStats[2] = read.nextInt();
                money = read.nextInt();
                read.close();
            }
            catch (Exception e) {
                System.out.println("\nError! Could not load data");
                //variables need to be pointlessly assigned otherwise program crashes  :/
                petType = "";
                petName = "";
                currentStats[0] = 0;
                currentStats[1] = 0;
                currentStats[2] = 0;
                System.exit(0);
            }
            //these final variables need to be assigned seperately from the rest otherwise netBeans crashes  :/
            maxStats[0] = netBeansStupid(0);
            maxStats[1] = netBeansStupid(2);
            maxStats[2] = netBeansStupid(4);
        }
        //return to main menu (but with saved information)
        while (inMenu)
        {
            menuNum = mainMenu(true);
            //switch statement based on user input
            switch (menuNum)
            {
                case 3: System.exit(0);
                case 2: System.out.println("\nHow to play:\nearn money by playing minigames!\nbuy stuff with your money to make your pet happy!\nmaintain your pet for as long as possible!"); break;
                case 1: inMenu = false; break;
            }
        }
        while (true)
        {
            //user decides what they want to do with pet
            System.out.println("\nSelect an option(enter a number as your choice)");
            System.out.println("");
            System.out.println("1.Play with "+petName);
            System.out.println("2.Earn money (Minigames)");
            System.out.println("3.Leave game");
            System.out.println("");
            menuNum = in.nextInt();
            //pet's stats lower after every decision
            for (int i=0; i<currentStats.length; i++)
            {
                System.out.println(currentStats[i]);
                if (currentStats[i] > 0)
                    currentStats[i]--;
                else
                    System.out.println("\nYour pet needs attention!");
                    break;
            }
            if (menuNum == 1)
            {
                //if user decides to play with pet (select action)
                System.out.println("\nWhat would you like to do with your pet?(enter a number as your choice)");
                System.out.println("");
                System.out.println("1.Give "+petName+" a toy ($100)");
                System.out.println("2.Feed "+petName+" ($800)");
                System.out.println("3.Groom "+petName);
                System.out.println("");
                menuNum = in.nextInt();
                //switch statement for the user's action
                switch (menuNum)
                {
                    case 1: if (money<100) {System.out.println("Not enough money");} else {currentStats[2] = toyPet(maxStats[2],currentStats[2]); actions[2]++;} break;
                    case 2: if (money<800) {System.out.println("Not enough money");} else {currentStats[1] = feedPet(maxStats[1],currentStats[1]); actions[1]++;} break;
                    case 3: currentStats[0] = groomPet(maxStats[0],currentStats[0]); actions[0]++; break;
                }
            }
            else if (menuNum == 2)
            {
                //if user decides to earn money (game selection)
                System.out.println("\nSelect a game(type \"1\" or \"2\")");
                System.out.println("");
                System.out.println("1.Guess the number");
                System.out.println("2.Matching game");
                System.out.println("");
                menuNum = in.nextInt();
                //number guessing game
                if (menuNum == 1)
                {
                    money += guessingGame();
                }
                //matching game
                else if (menuNum == 2)
                {
                    money += matchingGame();
                }
                else
                {
                    System.out.println("Invalid input\n");
                }
            }
            else if (menuNum == 3)
            {
                //if user decides to leave the game
                try {
                    //save user's new pet information
                    File f = new File(user+".txt");
                    PrintWriter write = new PrintWriter(f);
                    write.println(password);
                    write.println(petType);
                    write.println(petName);
                    write.println(maxStats[0]);
                    write.println(currentStats[0]);
                    write.println(maxStats[1]);
                    write.println(currentStats[1]);
                    write.println(maxStats[2]);
                    write.println(currentStats[2]);
                    write.println(money);
                    write.close();
                }
                catch (Exception e) {
                    System.out.println("\nError! Could not save data");
                }
                //list user's actions
                System.out.println("");
                System.out.println("You have groomed your pet "+actions[0]+" times!");
                System.out.println("You have fed your pet "+actions[1]+" times!");
                System.out.println("You have given your pet a toy "+actions[2]+" times!");
                //assign awards based on actions
                if (actions[0]+actions[1]+actions[2] >= 100)
                {
                    System.out.println("\nCongratulations! You earned Video Game Addict!");
                }
                else if (actions[0]+actions[1]+actions[2] >= 50)
                {
                    System.out.println("\nCongratulations! You earned Procrastinator!");
                }
                else if (actions[0]+actions[1]+actions[2] >= 20)
                {
                    System.out.println("\nCongratulations! You earned Time Waster!");
                }
                //exit game
                System.exit(0);
            }
            else
            {
                System.out.println("Invalid input\n");
            }
        }
    }
    
}
