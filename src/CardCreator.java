import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class CardCreator {
    private String pointTxtFileName;
    private String[] cardSymbols = {"♠","♣","♥","♦"};
    private String[] cardValue = {"A","2","3","4","5","6","7","8","9","K","J","T","Q"};
    ArrayList<Card> cards52 = new ArrayList<>();
    public CardCreator(String pointTxtFileName){
    this.pointTxtFileName = pointTxtFileName;
    }
    public ArrayList<Card> cardCreate52(){
        // Card creation
        for (String symbolOfCards : cardSymbols){
            for (String valueOfCard : cardValue){
                Card a = new Card(symbolOfCards,valueOfCard,1);
                cards52.add(a);
            }
        }
        // Card52 shuffling
        Collections.shuffle(cards52);
        try {
            File txtFileName = new File(pointTxtFileName);
            Scanner scanner = new Scanner(txtFileName);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitedLine = line.strip().split(" ");
                for (Card card : cards52){
                    // symbol and value are same
                    if (splitedLine[0].substring(0,1).equals(card.getSymbol()) && splitedLine[0].substring(1,2).equals(card.getValue())){
                        card.setPoint(Integer.parseInt(splitedLine[1]));
                        //if there is * in txt file
                    }
                    //_*
                    else if (splitedLine[0].substring(1,2).equals("*")){
                        for (int j = 0; j < cards52.size(); j++) {
                            if (cards52.get(j).getSymbol().equals(splitedLine[0].substring(0,1))){
                                cards52.get(j).setPoint(Integer.parseInt(splitedLine[1]));
                            }
                        }
                    }
                    //
                    else if (splitedLine[0].substring(0,1).equals("*")){
                        for (int j = 0; j < cards52.size(); j++) {
                            if (cards52.get(j).getValue().equals(splitedLine[0].substring(1,2))){
                                cards52.get(j).setPoint(Integer.parseInt(splitedLine[1]));
                            }
                        }
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + " new file is creating... ");
        }
        // Card52 shuffling
        Collections.shuffle(cards52);
//        for ( Card card : cards52){
//            card.printCardInfo();
//        }
//        System.out.println("------------------");
        int halfSize = cards52.size() / 2;
        ArrayList<Card> firstHalf = new ArrayList<Card>(cards52.subList(0, halfSize));
        ArrayList<Card> secondHalf = new ArrayList<Card>(cards52.subList(halfSize, cards52.size()));
        Collections.reverse(secondHalf);
        ArrayList<Card> result = new ArrayList<Card>(secondHalf);
        result.addAll(firstHalf);
        System.out.println("cut process finished");
        System.out.println("cut are ready to play");
//        for ( Card card : result){
//            card.printCardInfo();
//        }
//        System.out.println("------------------");
        // control cards
//        for (int i = 0; i < cards52.size(); i++) {
//            //if (cards52.get(i).getPoint()==5)
//            cards52.get(i).printCardInfo();
//        }
//        System.out.println(cards52.size());
        return result;
    }
}
