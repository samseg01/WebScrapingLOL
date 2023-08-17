import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Scraping {
    Document doc;
    String nickName="";
    static final String linkOpGG = "https://www.op.gg/summoners/br/";
    static final String linkMaestria = "https://championmastery.gg/summoner?summoner=";

    public Scraping(String nickName) {
        this.nickName = nickName;
    }

    public void getNewNick(String novoNick){
        this.nickName = novoNick;
    }


    private void getHTML(String link, String info) {
        try {
            this.doc = Jsoup.connect(link + trocaExecCaracteres(info)).get();
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    private String trocaExecCaracteres(String info) {
        String nick = nickName;
        //spaceCaracOpGG = "%20";
        //spaceCaracMaestria = "+";
        //addRegion = "&region=BR";
        String space = "";
        if (info.equals("dados")) {
            space = "%20";
        } else if (info.equals("maestria")) {
            space = "+";
        }

        String nickS = "";

        for(int i = 0; i < nick.length(); ++i) {
            char carac = nick.charAt(i);
            String letra = Character.toString(carac);
            if (letra.equals(space)) {
                nickS = nickS + "%20";
            } else {
                nickS = nickS + letra;
            }
        }

        if (info.equals("maestria")) {
            nickS = nickS + "&region=BR";
        }

        return nickS;
    }

    public String printElo() {
//        String classeRankedSolo = "css-1kw4425 e1h9n1vr0";
        String classeRankedSolo = "css-1kw4425 elodsbv0";
        this.getHTML(linkOpGG, "dados");
        String saida = "";
        if (this.doc.getElementsByClass(classeRankedSolo).text().contains("Unranked")) {
            saida = "Ranked Solo: Unranked";
        } else {
            saida = this.doc.getElementsByClass(classeRankedSolo).text();
        }

        return saida;
    }

    public String printLevel() {
        this.getHTML(linkOpGG, "dados");
        String saida = this.doc.getElementsByClass("level").text();
        String Saida = "";
        int contSpace = 0;

        for(int i = 0; i < saida.length(); ++i) {
            char carac = saida.charAt(i);
            String letra = String.valueOf(carac);
            if (letra.equals(" ")) {
                Saida = Saida + " ";
                ++contSpace;
            } else {
                Saida = Saida + letra;
            }

            if (contSpace == 1) {
                break;
            }
        }

        return "Nível " + Saida;
    }

    public String printMaestria() {
        this.getHTML(linkMaestria, "maestria");
        String saida = "";
        saida = this.doc.getElementsByClass("internallink").text();
        String Saida = "";
        int contSpace = 0;

        for(int i = 0; i < saida.length(); ++i) {
            char carac = saida.charAt(i);
            String letra = String.valueOf(carac);
            if (letra.equals(" ")) {
                Saida = Saida + " ";
                ++contSpace;
            } else {
                Saida = Saida + letra;
            }

            if (contSpace == 3) {
                break;
            }
        }

        return "Suas maiores maestrias são com : " + Saida;
    }
}
