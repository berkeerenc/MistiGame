public class Card {
    public static final String ANSI_BLACK = "\u001B[30m"; // white color
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m"; // color background
    public static final String ANSI_RESET = "\u001B[0m"; // color reset
    private String symbol;
    private String value;
    private int point;
    public Card(String symbol, String value, int point) {
        this.symbol = symbol;
        this.value = value;
        this.point = point;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
    public void printCardInfo(){
        System.out.println(ANSI_WHITE_BACKGROUND+ANSI_BLACK+symbol + " " + value + " / " + point +ANSI_RESET);
    }
}
