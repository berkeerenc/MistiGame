import java.util.ArrayList;
import java.util.Scanner;
public class GameProcess {
    public static final String ANSI_GREEN = "\u001B[32m"; //  color for  earning point
    public static final String ANSI_PURPLE = "\u001B[35m";// color
    public static final String ANSI_YELLOW = "\u001B[33m";//color
    public static final String ANSI_CYAN = "\u001B[36m"; // color
    public static final String ANSI_RESET = "\u001B[0m"; // color reset
    private ArrayList<Card> card52 ; // games all card
    private ArrayList<Card> middleCard = new ArrayList<>(); // table cards
    private final Player[] playerList;
    private final int detailRate;
    private int tour = 0;
    private int hand = 0;
    public GameProcess(ArrayList<Card> getCard52, Player[] playerList, int detailRate) {
        this.card52 = getCard52;
        this.playerList = playerList;
        this.detailRate = detailRate;
        System.out.println("4 cards are throwing to the table ");
        // 4 card to middle
        System.out.println(ANSI_YELLOW+"middleCards".toUpperCase()+ANSI_RESET); //color
        for (int i = 0; i < 4; i++) {
            middleCard.add(card52.get(0));
            card52.get(0).printCardInfo();
            //update reminderCards
            playerList[0].updateRemainingCards(card52.get(0));
            card52.remove(0);
        }
//        System.out.println("other cards number is "+ card52.size());
    }
    public void GameReady(){
        // show players cards
        if (detailRate == 0){
            for (Player player : playerList){
                System.out.println( "player " + player.getName());
                for (Card card : player.getComputerCard4()){
                    card.printCardInfo();
                }
                System.out.println("------------------");
            }
        }

        // PLAYER PLAYS CARDS
        for (Player player : playerList){
            System.out.printf("tour %d, hand %s", tour/playerList.length, ++hand);
            for (int i = 0; i < playerList.length; i++) {
                System.out.printf(" %s -> %s /",playerList[i].getName(), playerList[i].getPoint());
            }
            System.out.println(ANSI_PURPLE+player.getName().toUpperCase() + " is playing" +ANSI_RESET);
            Card selectedCard = player.PlayerGameplay(middleCard,detailRate);
            middleCard.add(selectedCard);
            pointControl(player);
            System.out.println(player.getName().toUpperCase() + " is played");
            // according to detailRate we show middle cards info
            if (detailRate == 0){
                System.out.println(ANSI_YELLOW+"middleCards".toUpperCase()+ANSI_RESET); //color
                for (Card cardMiddle : middleCard){
                    cardMiddle.printCardInfo();
                }if (middleCard.size() == 0){
                    System.out.println("-");
                }
            }else if (detailRate == 1 && middleCard.size() >= 1){
                System.out.println(ANSI_YELLOW+"Last Card".toUpperCase()+ANSI_RESET);
                middleCard.get(middleCard.size()-1).printCardInfo();
            }
        }
    }
    public ArrayList<Card> giveCard4(){
        ArrayList<Card> card4 = new ArrayList<>();
        tour++; // for count tour
        //hw many card will give to players IF THERE ARE NOT ENOUGH CARD
        int howManyCard = 4;
        if (playerList.length*4 > card52.size()){
            howManyCard = card52.size()/playerList.length;
        }
        for (int j = 0; j < 4; j++) {
            card4.add(card52.get(0));
            card52.remove(0);
        }
            return card4;
    }
    public void send4CardToEachPlayer(){
        // 4 cards to each player
        for (Player player : playerList){
            player.setComputerCard4(giveCard4());
        }
    }
    public void pointControl(Player player){
        int lengthOfMiddleCards = middleCard.size();
        if (lengthOfMiddleCards > 1){
            //MISTI
            if (middleCard.get(lengthOfMiddleCards-1).getValue().equals(middleCard.get(lengthOfMiddleCards-2).getValue()) && lengthOfMiddleCards == 2 ){
                System.out.println("MISTI");

                for (Card card : middleCard){
                    player.setPoint((card.getPoint()) * 5);
                }
                //clear middle
                middleCard.clear();
                // every player's lastWinner boolean value = false
                for (Player player1 : playerList){
                    player1.setLastWinner(false);
                }
                // winner lastWinner = true
                player.setLastWinner(true);
                System.out.println(ANSI_CYAN+player.getPoint()+ " =||= " + player.getName().toUpperCase()+ANSI_RESET);
            }
            //JOKER
            else if (middleCard.get(lengthOfMiddleCards-1).getValue().equals("J")){
                for (Card card : middleCard){
                    player.setPoint(player.getPoint() + card.getPoint());
                }
                System.out.println(ANSI_CYAN+player.getPoint()+ " =||= " + player.getName().toUpperCase()+ANSI_RESET);
                //clear middle
                middleCard.clear();
                // every player's lastWinner boolean value = false
                for (Player player1 : playerList){
                    player1.setLastWinner(false);
                }
                // winner lastWinner = true
                player.setLastWinner(true);
            }
            //NORMAL
            else if (middleCard.get(lengthOfMiddleCards-1).getValue().equals(middleCard.get(lengthOfMiddleCards-2).getValue())){
                for (Card card : middleCard){
                    player.setPoint(player.getPoint() + card.getPoint());
                }
                System.out.println(ANSI_CYAN+player.getPoint()+ " =||= " + player.getName().toUpperCase()+ANSI_RESET);
                //clear middle
                middleCard.clear();
                // every player's lastWinner boolean value = false
                for (Player player1 : playerList){
                    player1.setLastWinner(false);
                }
                // winner lastWinner = true
                player.setLastWinner(true);
            }
        }
    }

    // finishGame func returns winner player its calling just 1 time
    public Player finishGame(){
        // every player's lastWinner boolean value = false
        for (Player player1 : playerList){
            if (player1.getLastWinner() == true){
                System.out.println( player1.getName() + " is last winner and collected all cards on table");

                // set points to last winner
                for (Card card : middleCard){
                    player1.setPoint(player1.getPoint() + card.getPoint());
                }

                System.out.println(ANSI_CYAN+player1.getPoint()+ " =||= " + player1.getName().toUpperCase()+ANSI_RESET);
                //clear middle
                middleCard.clear();
            }
        }

        System.out.println("SCORES");
        Player winner = playerList[0] ;
        for (Player player : playerList){
            System.out.println(ANSI_GREEN+player.getPoint()+ " =||= " + player.getName().toUpperCase()+ANSI_RESET);
            if (player.getPoint()>winner.getPoint()){
                winner = player;
            }
        }
        System.out.println("WINNER " + winner.getName().toUpperCase());
        return winner;
    }

    public ArrayList<Card> getCard52() {
        return card52;
    }
}
