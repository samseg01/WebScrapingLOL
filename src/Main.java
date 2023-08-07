import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        System.out.print("Digite um nick: ");
        Scraping scraping = new Scraping(lendoNick());

        while (scraping.printElo().equals("")){
            System.out.println("Jogador inexistente, verifique a ortografia e tente novamente:");
            scraping.getNewNick(lendoNick());
        }

        System.out.println(scraping.printElo());
        System.out.println(scraping.printLevel());
        System.out.println(scraping.printMaestria());

    }
    public static String lendoNick(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}