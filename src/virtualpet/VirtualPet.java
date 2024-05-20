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
        //declare final variables
        
        //declare variables
        String menuSelect;
        int menuNum = 0;
        int statPool = 40;
        int randNum;
        int guessNum;
        int money = 0;
        int earned = 0;
        int revealed;
        int selected;
        int matches = 0;
        boolean inMenu = true;
        String cards;
        String shuffledCards;
        String hiddenCards;
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
        for (int i=3; i>=0; i--)
        {
            if (i == 0)
            {
                System.exit(0);
            }
            System.out.print("Enter username: ");
            user = in.nextLine();
            System.out.print("Enter password: ");
            password = in.nextLine();
            System.out.println("");
            if (user.equals("snoopy") && password.equals("toto"))
            {
                break;
            }
            else
            {
                System.out.println("Incorrect. You have "+(i-1)+" try/tries left.");
            }
        }
        //start menu
        System.out.println("Welcome to Pet Simulator!");
        while (inMenu)
        {
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
                if (0 > menuNum || menuNum > 3)
                {
                    System.out.println("\nInvalid input");
                }
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
            else
            {
                System.out.println("\nInvalid input");
            }
            //switch statement based on user input
            switch (menuNum)
            {
                case 3: System.exit(0);
                case 2: break;
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
            int petNameLen = rng.nextInt(5);
            final String vowels = "aeiou";
            final String consonant = "bcdfghjklmnpqrstvwxyz";
            petName += consonant.charAt(rng.nextInt(21));
            petName += vowels.charAt(rng.nextInt(5));
            petName += consonant.charAt(rng.nextInt(21));
            petName += vowels.charAt(rng.nextInt(5));
            if (petNameLen > 0)
            {
                petName += consonant.charAt(rng.nextInt(21));
                if (petNameLen > 1)
                {
                    petName += vowels.charAt(rng.nextInt(5));
                    if (petNameLen > 2)
                    {
                        petName += consonant.charAt(rng.nextInt(21));
                        if (petNameLen > 3)
                        {
                            petName += vowels.charAt(rng.nextInt(5));
                        }
                    }
                }
            }
            if (rng.nextInt(5) == 0)
            {
                int doubleLetter = rng.nextInt(petName.length());
                petName = petName.substring(0,doubleLetter)+petName.charAt(doubleLetter)+petName.charAt(doubleLetter)+petName.substring(doubleLetter+1,petName.length());
            }
            if (rng.nextInt(5) == 0)
            {
                int doubleLetter = rng.nextInt(petName.length());
                petName = petName.substring(0,doubleLetter)+petName.charAt(doubleLetter)+petName.charAt(doubleLetter)+petName.substring(doubleLetter+1,petName.length());
            }
        }
        System.out.println("\nYour pet, named "+petName+", has been born!");
        //pet stats
        final int MAXHEALTH = rng.nextInt(9)+10;
        statPool -= MAXHEALTH;
        final int MAXFOOD = rng.nextInt(statPool-20)+10;
        statPool -= MAXFOOD;
        final int MAXENERGY = statPool;
        //return to main menu
        inMenu = true;
        while (inMenu)
        {
            System.out.println("");
            System.out.println("1.Play/Interact");
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
            else if (menuSelect.equals("play") || menuSelect.equals("interact"))
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
            switch (menuNum)
            {
                case 3: System.exit(0);
                case 2: break;
                case 1: inMenu = false; break;
            }
        }
        //if user decides to play with pet (game selection)
        while (true)
        {
            System.out.println("\nSelect a game(type \"1\" or \"2\")");
            System.out.println("");
            System.out.println("1.Guess the number");
            System.out.println("2.Matching game");
            System.out.println("");
            menuNum = in.nextInt();
            //number guessing game
            if (menuNum == 1)
            {
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
                        money += earned;
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
                            money += earned;
                            break;
                        }
                    }
                }
            }
            //matching game
            else if (menuNum == 2)
            {
                //game setup
                matches = 0;
                cards = "JJQQKKAA$$";
                hiddenCards = "XXXXXXXXXX";
                shuffledCards = "";
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
                        money += earned;
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
                            hiddenCards = hiddenCards.substring(0,selected)+shuffledCards.charAt(selected)+hiddenCards.substring(selected+1,hiddenCards.length());
                            revealed = selected;
                            System.out.println(hiddenCards);
                            selected = in.nextInt();
                            if (0 <= selected && selected < shuffledCards.length() && hiddenCards.charAt(selected) == 'X')
                            {
                                hiddenCards = hiddenCards.substring(0,selected)+shuffledCards.charAt(selected)+hiddenCards.substring(selected+1,hiddenCards.length());
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
                                        money += earned;
                                        break;
                                    }
                                }
                                //if the cards guessed by the user dont match
                                else
                                {
                                    hiddenCards = hiddenCards.substring(0,selected)+"X"+hiddenCards.substring(selected+1,hiddenCards.length());
                                    hiddenCards = hiddenCards.substring(0,revealed)+"X"+hiddenCards.substring(revealed+1,hiddenCards.length());
                                }
                            }
                            //if user guesses a card that is out of range or already matched
                            else
                            {
                                System.out.println("\nInvalid guess");
                                //if an unmatched card was already revealed before an invalid guess, hide card
                                if (0 > selected || selected >= shuffledCards.length())
                                {
                                    hiddenCards = hiddenCards.substring(0,revealed)+"X"+hiddenCards.substring(revealed+1,hiddenCards.length());
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
                
            }
            else
            {
                System.out.println("Invalid input\n");
            }
        }
    }
    
}
