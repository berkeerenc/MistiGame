import java.util.ArrayList;
import java.util.Scanner;

public class realPlayer extends Player{
    public static final String ANSI_YELLOW = "\u001B[33m";//color
    public static final String ANSI_RESET = "\u001B[0m"; // color reset
    public realPlayer(String name) {
        super(0,name);
    }
    @Override
    public Card PlayerGameplay(ArrayList<Card> middleCard, int detailRate) {
        // if detailRate == 2 we will show just last card of middleCard when the turn comes player
        if (detailRate == 2 && middleCard.size() >= 1){
            System.out.println(ANSI_YELLOW+"Last Card ".toUpperCase()+ANSI_RESET); //color
            middleCard.get(middleCard.size()-1).printCardInfo();
        }
        // show cards of ralPlayer
        for (int i = 0; i < getComputerCard4().size(); i++) {
            System.out.printf("index: %s ",i);
            getComputerCard4().get(i).printCardInfo();
        }
        //select a card for play
        while (true){
            System.out.println("pls select card index");
            try{
                Scanner sc = new Scanner(System.in);
                int selectedCard = sc.nextInt();
                if (selectedCard>=0 && selectedCard < getComputerCard4().size()){
                    //return selected card
                    Card SelectedAndDeletedCard = getComputerCard4().get(selectedCard);
                    //delete selectedCard and return selected card
                    ArrayList<Card> newList = getComputerCard4();
                    // selectedCard Store
                    newList.remove(SelectedAndDeletedCard);
                    setComputerCard4(newList); // new list copied to ComputerCard4
                    // store used card
                    updateRemainingCards(SelectedAndDeletedCard);
                    return SelectedAndDeletedCard;
                }else {
                    System.out.println("there is no card with this index");
                }
            }catch (Exception e){
                System.out.println(e.fillInStackTrace());
                System.out.println("pls enter a digit value ");
            }
        }
    }
}
