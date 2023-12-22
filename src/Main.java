import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    // args = txt_file_name, player number(3), is there human player,  name1, name2, name3, lvl,lvl,lvl, detailRate
    public static void main(String[] args) {
        // playerList store all players
        ArrayList<Player> playerList = new ArrayList<>();
        int botNumber=0;
        int humanNumber =0;
        int detailRate = 0;
        // information from args
        try {
            botNumber = Integer.parseInt(args[1]);
            humanNumber = Integer.parseInt(args[2]);
            detailRate = Integer.parseInt(args[3+botNumber*2]);
            if (botNumber > 5){
                System.out.println("you can not play with more than 5 bot ");
                System.out.println("Exit function activated ");
                System.exit(0);
            }
            System.out.println("botNumber " + botNumber);
            System.out.println("humanNumber " + humanNumber);
            System.out.println("detailRate " + detailRate);
        }catch (Exception e){
            e.fillInStackTrace();
            System.out.println("invalid input from args pls enter digits");
            System.exit(0);
        }
        // detail rate if it is >3 or <0 exit system
        if (detailRate <0 || detailRate >3){
            System.out.println("detailRate can not be negative number or bigger then 2 // 0<detailRate<2");
            System.exit(0);
        }
        // if botNumber = or < 0 exit
        if (botNumber <= 0 ){
            System.out.println("botNumber can not be negative value or 0");
            System.exit(0);
        }
        // if human player > 0 and < 2 create realPlayer;
        if (humanNumber==1) {
            Scanner sc = new Scanner(System.in);
            System.out.println("pls enter the human payer name");
            realPlayer realPlayer = new realPlayer(sc.nextLine());
            playerList.add(realPlayer);
        }else if (humanNumber <0){
            System.exit(0);
        }
        //declare bot players object
        for (int i = 0; i < botNumber; i++) {
            try{
                Player player = new Player(Integer.parseInt(args[3+botNumber+i]),args[3+i]);
                // hardness lvl number control
                if (Integer.parseInt(args[3+botNumber+i]) >3 || Integer.parseInt(args[3+botNumber+i]) <0){
                    System.out.println("invalid harness rate input");
                    System.exit(1);
                }
                playerList.add(player);
            }catch (Exception e){
                e.fillInStackTrace();
                System.out.println("invalid input for level");
                System.exit(0);
            }
        }
        // information about created object
        for (int i = 0; i < botNumber + humanNumber; i++) {
            System.out.println("player name -> "+playerList.get(i).getName());
            System.out.println("hardness lvl -> " + playerList.get(i).getRateOfHardness());
            System.out.println(playerList.get(i).getClass());
            System.out.println(" ----- ---- ---- --- --- --- --- ---");
        }
        // Card created here
        CardCreator a = new CardCreator(args[0]); // PointFile.txt shared
        ArrayList<Card> cards = a.cardCreate52(); // 52 card come

        Player[] players = playerList.toArray(new Player[0]); // arraylist changed to array
        GameProcess gameProcess = new GameProcess(cards, players,detailRate); // game process object created

        for (int i = 0; i < 48/(playerList.size()*4) ; i++) {
            gameProcess.send4CardToEachPlayer();

            for (int j = 0; j < 4 ; j++) {
                gameProcess.GameReady();
            }
        }
        //for show the last scores
        Player winner = gameProcess.finishGame();
        ScoreList scoreList = new ScoreList(players,winner);
        try {
            scoreList.editScoreList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
