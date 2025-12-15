import java.util.Random;
import java.util.Scanner;
public class DefenseLightHeavy {

    public static String DEFENSE = "Defense";
    public static String LIGHT_ATTACK = "Light Attack";
    public static String HEAVY_ATTACK = "Heavy Attack";
    public static int STARTING_HP = 5;
    public static int MAX_ROUNDS = 10; 
    
    public static String greeting(Scanner sc, int winCount, int HP) {
        String player;
        System.out.println("Welcome to the Game!");
        System.out.print("Enter your name: ");
        player = sc.nextLine();

        System.out.println("\nHello, " + player + "!");
        System.out.println("Let's play! (Type 'Defense', 'Light Attack', or 'Heavy Attack')");
        System.out.println("Rules: Defense > Light, Light > Heavy, Heavy > Defense");
        System.out.println("Initial HP: " + HP + " | Total Rounds: " + MAX_ROUNDS);
        System.out.println("Type \n**!exit** to quit, \n**!winscore** to check your wins \n**!UsePotion** to using potion and \n**!BuyPotion** to buy a potion(cap only 1 per buying)");
        
        return player;
    }
    public static String getComputerChoice() {
        String[] computerChoices = {DEFENSE, LIGHT_ATTACK, HEAVY_ATTACK};
        Random random = new Random();
        int computerChoicesIndex = random.nextInt(computerChoices.length);
        return computerChoices[computerChoicesIndex];
    }

    // Win?Lose
    public static int userWin(String computer) {
        System.out.println("You win this match!");
        System.out.println("Enemy choose: " + computer);
        return 1; 
    }
    public static int userLose(String computer) {
        System.out.println("You lost this match!");
        System.out.println("Enemy choose: " + computer);
        return 0; 
    }
    public static int userDraw(String computer) {
        System.out.println("Draw!");
        System.out.println("Enemy choose: " + computer);
        return 0; 
    }

    // HP system
    public static int yourHP_W(int HP) {
        return 0; 
    }
    public static int yourHP_L(int HP) {
        return -1; 
    }
    public static void lowHP(int HP) {
        if (HP <= 2 && HP > 0) {
            System.out.println("Warning: Your HP is low!");
        }
    }

    //Decide who win
    public static int[] playGame(String PlayerChoice, String computer, int currentHP) {
        int winCountChange = 0;
        int HPChange = 0;

        if (PlayerChoice.equals(computer)) {
            winCountChange = userDraw(computer);
            HPChange = yourHP_W(currentHP); 
        } 
        else if ((PlayerChoice.equals(DEFENSE) && computer.equals(LIGHT_ATTACK)) || 
                 (PlayerChoice.equals(LIGHT_ATTACK) && computer.equals(HEAVY_ATTACK)) || 
                 (PlayerChoice.equals(HEAVY_ATTACK) && computer.equals(DEFENSE))) {
            winCountChange = userWin(computer);
            HPChange = yourHP_W(currentHP); 
        } 
        else {
            winCountChange = userLose(computer);
            HPChange = yourHP_L(currentHP); 
        }
        
        System.out.println("Your HP is: " + (currentHP + HPChange) + " now.");
        lowHP(currentHP + HPChange);

        return new int[]{winCountChange, HPChange};
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int winCount = 0; 
        int HP = STARTING_HP;
        String playerName;
        int roundCount = 0; 
        int potion = 0;
        playerName = greeting(sc, winCount, HP);

        while (HP > 0 && roundCount < MAX_ROUNDS) {
            
            roundCount++; 
            System.out.println("\n Match " + roundCount + " (HP: " + HP + "/" + STARTING_HP + ")");
            System.out.print(playerName + " => ");
            
            String choice = sc.nextLine().trim();

            if (choice.equalsIgnoreCase("!exit")) {
                System.out.println("Total Matches Won: **" + winCount + "/" + roundCount + "**");
                System.out.println("Bye! See you! " + playerName );
                break;
            }
            if (choice.equalsIgnoreCase("!winscore")) {
                System.out.println("Matches Won: " + winCount + " out of " + (roundCount - 1) + " played.");
                roundCount--; 
                continue;
            }
            // useing potion!
            if(choice.equalsIgnoreCase("!UsePotion")){
                if(potion >= 1){
                    potion--;
                    HP++;
                    System.out.println("Used Potion! HP restored(+1). Current HP: " + HP);
                    System.out.println("Potions left: " + potion);
                    roundCount--;
                    continue;
                }
                else{
                    System.out.println("You don't have a potion");
                    roundCount--;
                    continue;
                }
            }
            // Buy potion!
            if(choice.equalsIgnoreCase("!BuyPotion")){
                System.out.println("You want to buy a potion? (Y/N)");
                String comfirmBuy = sc.nextLine();

                if(comfirmBuy.equalsIgnoreCase("Y")){
                    if(winCount >= 1){
                        System.out.println("!!!!!!!!! Buy potion success. !!!!!!!!!");
                        potion++;
                        winCount--;
                        System.out.println("Current Potions: " + potion + " | Current Wins: " + winCount);
                    }
                    else if(winCount <1){
                        System.out.println("you doesn't have win to buy a potion");
                    }
                   
                }
                else if(choice.equalsIgnoreCase("N")){
                    System.out.println("!!!!!!! Buy potion fail. !!!!!!!!");
                }
                else{
                    System.out.println("Typing Y(yes) or N(no) only pls!!!");
                }
                roundCount--;
                continue;
            }

            if (choice.equalsIgnoreCase(DEFENSE) || 
                choice.equalsIgnoreCase(LIGHT_ATTACK) || 
                choice.equalsIgnoreCase(HEAVY_ATTACK)) {

                String computer = getComputerChoice();
                
                int[] results = playGame(choice, computer, HP);
                
                winCount += results[0];
                HP += results[1];

            } else {
                System.out.println("Invalid input!!!!!!");
                roundCount--; 
            }
        }

        System.out.println("\nEnd Games");
        
        if (HP <= 0) {
            System.out.println(" K.O.!! Your HP reached 0.");
            System.out.println(playerName + ", you have been defeated.");
        } else if (winCount == MAX_ROUNDS) {
             System.out.println("VICTORY! You won all " + MAX_ROUNDS + " matches!");
        } else if (roundCount == MAX_ROUNDS) {
            System.out.println("You finished after " + MAX_ROUNDS + " matches! You won " + winCount + " times.");
        }
        
        System.out.println("Final Win Score: " + winCount + "");
   }    
}