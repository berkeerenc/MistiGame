import java.util.ArrayList;
public class Player {
    private int point;
    private ArrayList<Card> computerCard4 = new ArrayList<>();
    private int rateOfHardness;
    private String name;
    private static String[] allCardsForRemember = {"A","2","3","4","5","6","7","8","9","K","J","T","Q"};
    private static int[] allCardsRemaining = new int[13];
    private Boolean lastWinner = false;
    public Player(int rateOfHardness, String name) {
        this.point = 0;
        this.rateOfHardness = rateOfHardness;
        this.name = name;
        declareArraysForRemaining();
    }
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public ArrayList<Card> getComputerCard4() {
        return computerCard4;
    }
    public void setComputerCard4(ArrayList<Card> computerCard4) {
        this.computerCard4 = computerCard4;
    }
    public int getRateOfHardness() {
        return rateOfHardness;
    }
    public String getName() {
        return name;
    }
    public Boolean getLastWinner() {
        return lastWinner;
    }
    public void setLastWinner(Boolean lastWinner) {
        this.lastWinner = lastWinner;
    }
    public Card PlayerGameplay(ArrayList<Card> middleCard, int detailRate ) {
        int sum = middleSumPoint(middleCard); // middle Cards sum point
//        System.out.println(sum + "sum");
        if (rateOfHardness == 1){
            Card a1 = computerCard4.get(0);
            computerCard4.remove(0);
            updateRemainingCards(a1);
            return a1;
        } else if (rateOfHardness == 2) {
            for (Card card : computerCard4){
                if (middleCard.size()>=1 && middleCard.get(middleCard.size()-1).getValue().equals(card.getValue()) && sum>0){
                    updateRemainingCards(card);
                    computerCard4.remove(card);
                    return card;
                }
            }
            // joker play
            for (Card card : getComputerCard4()){
                if (card.getValue().equals("J") && middleCard.size() >= 3 && sum>0){
                    updateRemainingCards(card);
                    computerCard4.remove(card);
                    return card;
                }
            }
            Card a1 = computerCard4.get(0);
            computerCard4.remove(0);
            updateRemainingCards(a1);
            return a1;
        }else if (rateOfHardness == 3){
            for (Card card : computerCard4){
                if(middleCard.size()>1 && middleCard.get(middleCard.size()-1).getValue().equals(card.getValue()) && sum>0){
                    updateRemainingCards(card);
                    computerCard4.remove(card);
                    return card;
                }
                else if (middleCard.size() >= 2  && sum>0){
                    // joker play
                    if (card.getValue().equals("J")){
                        updateRemainingCards(card);
                        computerCard4.remove(card);
                        return card;
                    }
                }
                else {
                    Card minCard = getComputerCard4().get(0);
                    int index = 0;
                    // what is the card value it gives an index num
                    for (int i = 0; i<13;i++){
                        /*
                        if (allCardsForRemember[i].equals(minCard.getValue())){
                            index = i;
                        }
                        */
                    }
                    int remainingNum = allCardsRemaining[0];
                    int index2 = 0;
                    for (Card card1 : getComputerCard4()){
                        for (int i = 0; i<13;i++){
                            if (allCardsForRemember[i].equals(card1.getValue())){
                                index2 = i;
                                if (remainingNum > allCardsRemaining[index2]){
                                    minCard = card1;
                                }
                            }
                        }
                    }
                    updateRemainingCards(minCard);
                    computerCard4.remove(minCard);
                    return minCard;
                }
            }
            Card a1 = computerCard4.get(0);
            computerCard4.remove(0);
            updateRemainingCards(a1);
            return a1;
        }
        return null;
    }
    // fill the remember arrays
    public static void declareArraysForRemaining(){
        // there are 4 cards which have same value
        for (int i = 0; i < 13; i++) {
            allCardsRemaining[i] = 4;
        }
    }
    //  update it
    public void updateRemainingCards(Card middleCardLastCard){
        for (int i = 0; i<13;i++){
            if (allCardsForRemember[i].equals(middleCardLastCard.getValue())){
                allCardsRemaining[i] -= 1;
//                System.out.println(middleCardLastCard.getValue() + " Card used ");
            }
        }
        // for control the cards remembering situation
        //System.out.println("remembered Cards".toUpperCase());
        //for (int i = 0; i < 13; i++) {
            //System.out.println(allCardsForRemember[i] + " / " + allCardsRemaining[i]);
        //}
    }
    public static String[] getAllCardsForRemember() {
        return allCardsForRemember;
    }
    public static void setAllCardsForRemember(String[] allCardsForRemember) {
        Player.allCardsForRemember = allCardsForRemember;
    }
    public static int[] getAllCardsRemaining() {
        return allCardsRemaining;
    }
    public static void setAllCardsRemaining(int[] allCardsRemaining) {
        Player.allCardsRemaining = allCardsRemaining;
    }
    public int middleSumPoint(ArrayList<Card>middleCard){
        int sum = 0;
        for(Card card : middleCard){
            sum += card.getPoint();
        }
        return sum;
    }
}
