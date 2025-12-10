import java.util.Scanner;
import java.util.Random;

class test {
    // method conversation
    public static String greeting(Scanner sc, int rating, int HP){
        String player;
        System.out.print("Enter your name ");
        player = sc.nextLine();

        System.out.println("Hello, "+player + "!");
        System.out.println("Current Score: " + rating);
        System.out.println("Lets play Sword-Hammer-Shield!");
        System.out.println("Your HP is: " + HP);
        
        return player;
    }
    // computer selection
    public static String getComputerChoice(){
        String[] computerChoices = {"Sword", "Hammer", "Shield"};
        
        Random random = new Random();
        int computerChoicesIndex = random.nextInt(computerChoices.length);
        return computerChoices[computerChoicesIndex];
    }

    // rating
    public static int userWin(String computer){
        System.out.println("Victory!!!!!!!!"); 
        return 100;
    }
    public static int userLose(String computer){
        System.out.println("Lost, but the computer choose " + computer);
        return 0;
    }
    public static int userDraw(String computer){
        System.out.println("!Draw!"); 
        return 50;
    }

    // HP system
    public static int HP = 5;
    public static int yourHP_W(int HP){
        System.out.println("Your HP is: " + HP + " now.");
        return 0;
    }
    public static int yourHP_L(int HP){
        System.out.println("Your HP is: "+(HP - 1)+" now.");
        return -1;
    }
    public static void lowHP(int HP){
        if(HP <= 2){
            System.out.println("Your HP is low");
        }
    }

    //Decide who win?
    //Hammer > Sword
    //Sword > Sheild
    //Sheild > Hammer
    public static int playGame(String PlayerChoice, String computer){ 
        int rating = 0;
        if(PlayerChoice.equals(computer)){
            rating = userDraw(computer); 
        }
        else if ((PlayerChoice.equals("Sword") && computer.equals("Shield")) ||
            (PlayerChoice.equals("Hammer") && computer.equals("Sword")) ||
            (PlayerChoice.equals("Shield") && computer.equals("Hammer")) )
        {
            rating = userWin(computer);
            HP += yourHP_W(HP); 
        }
        else if (PlayerChoice.equals("Sword") || 
            PlayerChoice.equals("Hammer") || 
            PlayerChoice.equals("Shield"))
        {
            rating = userLose(computer);
            HP += yourHP_L(HP); 
        }
        else {
            System.out.println("Invalid input! Please type Sword, Hammer, or Shield.");
        }
        if(HP <= 2){
            lowHP(HP);
        }
        return rating;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int rating = 0;
        int HP =5;
        // Ask player name and say Hello! and show current score
        greeting(sc, rating, HP);

        // game loop
        while(true){
            System.out.print("=> ");
            String choice = sc.nextLine();

            if(choice.equals("!exit")){
                System.out.println("Your rating: " + rating);
                System.out.println("Bye! ");
                break;
            }
            if(choice.equals("!rating")){
                System.out.println("Your rating: " + rating);
                continue;
            }

            /*  
            if(choice.equals("!Shop")){
                System.out.println("1. Potion: (y/n)");
                String input = sc.nextLine();
                int backpack = 0;
                if(input.equals("n")){
                    continue;
                }
                else if(input.equals("y")){
                    backpack +=1;
                }
                continue;
            }*/
           
            // random choice for computer
            String computer = getComputerChoice();

            // play one round, decide who win? and return rating 
            rating += playGame(choice, computer);
        }
    }
}