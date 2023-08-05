import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scraping scraping = new Scraping(lendoNick());
    }
    public String lendoNick(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}