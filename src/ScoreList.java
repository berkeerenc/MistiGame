import javax.imageio.IIOException;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
public class ScoreList {
    private Player[] playerList;
    private Player winter;
    public ScoreList(Player[] playerList, Player winter) {
        this.playerList = playerList;
        this.winter = winter;
    }
    public void  editScoreList() throws IOException {
        ArrayList<Scorer> orderedScorers = new ArrayList<>();
        File txtFileName = new File("game_results.txt");
//        if(!txtFileName.exists()){

//            txtFileName.createNewFile();
//        }
        String infoOfWinner = "";
        for (Player player : playerList){
            if (!player.equals(winter)){
                infoOfWinner += " |"+player.getName() + " " + player.getRateOfHardness() + "| ";
            }
        }
        Scorer winner = new Scorer(winter.getPoint(),winter.getName(),infoOfWinner);
        orderedScorers.add(winner);
        // read file
        try {
            Scanner scanner = new Scanner(txtFileName);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitedLine = line.strip().split("--");
                Scorer scorer;
                if (splitedLine[0]!= null){
                    scorer = new Scorer(Integer.parseInt(splitedLine[0]), splitedLine[1],splitedLine[2]);
                }else {
                    scorer = new Scorer(Integer.parseInt(splitedLine[0]), splitedLine[1],splitedLine[2]);
                }
                orderedScorers.add(scorer);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + " new file is creating... ");
        }
        // arraylist order
        orderedScorers.sort(Comparator.comparingInt(Scorer::getPoint).reversed());
        // write
        for (Scorer sc : orderedScorers){
            System.out.println(sc.getName());
            System.out.println(sc.getInfo());
            System.out.println(sc.getPoint());
        }
        try {
            FileWriter writer = new FileWriter("game_results.txt", false);
            // write maks 10 line
            int counter = 0;
            for (Scorer sc : orderedScorers){
                writer.write(sc.getPoint() + "--" + sc.getName() + "--" + sc.getInfo() + "\n");
                counter++;
                if (counter == 10){
                    break;
                }
            }
            writer.close();
            System.out.println("Data written successfully.");
        } catch (IOException e) {
            System.out.println("Error while writing data to file: " + e.getMessage());
        }
    }
}