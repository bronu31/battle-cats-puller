package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://battle-cats.fandom.com/wiki/Enemy_Dictionary").get();
        Elements enemy_type_select_whole = doc.select("div>div>>p>a");
        String[] links=new String[enemy_type_select_whole.size()];
        for (int i=0;i<enemy_type_select_whole.size();i++){
            links[i]=enemy_type_select_whole.get(i).attr("href");
        }
        System.out.println(links[links.length-1]);

        doc = Jsoup.connect("https://battle-cats.fandom.com"+links[0].toString()).get();
        System.out.println(doc.select("h1.page-header__title"));



        /*System.out.println(doc.select("div>div>div>div>h3:not(:contains(Outbreaks),:contains(Collaboration)" +
                ",:contains(Removed),:contains(Nyanko),:contains(Kumanchu)" +
                ",:contains(Burgle),:contains(Kyoutou),:contains(Pogo))"));
*/



    }
}