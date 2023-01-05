package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class Main {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://battle-cats.fandom.com/wiki/Enemy_Dictionary").get();
        Elements enemy_type_select_whole = doc.select("div>div>>p>a");
        String[] links=new String[enemy_type_select_whole.size()];
        for (int i=0;i<enemy_type_select_whole.size();i++){
            links[i]=enemy_type_select_whole.get(i).attr("href");
        }
        System.out.println(links[links.length-1]);
        for (int i=0;i<links.length;i++){
            doc = Jsoup.connect("https://battle-cats.fandom.com"+links[i].toString()).get();
            Elements encounters = doc.select("h2:contains(Encounters)+div");
            encounters.select("p").remove();
            where_to_find_ul(encounters,doc.select("h1#firstHeading").text());
        }



    }
    public static void where_to_find_ul(Elements stages,String name){
        try {
            File file= new File("src/main/resources/enemies/"+name+".txt");
            file.createNewFile();
            FileWriter writer= new FileWriter(file);
            writer.write("-------------Empire of Cats-----------\n");
            writer.write(Jsoup.clean(stages.select("h3:containsWholeText(Empire of Cats):" +
                    "not(:contains(Outbreaks))+ul").html(), Safelist.basic())
                    .replaceAll("<[^>]*>","")+"\n");
            writer.write("-----------Into the Future--------\n");
            writer.write(Jsoup.clean(stages.select("h3:containsWholeText(Into the Future):" +
                            "not(:contains(Outbreaks))+ul").html(), Safelist.basic())
                    .replaceAll("<[^>]*>","")+"\n");
            writer.write("-----------Cats of the Cosmos--------\n");
            writer.write(Jsoup.clean(stages.select("h3:containsWholeText(Cats of the Cosmos):" +
                            "not(:contains(Outbreaks))+ul").html(), Safelist.basic())
                    .replaceAll("<[^>]*>","")+"\n");
            writer.write("------------The Aku Realms-----------\n");
            writer.write(Jsoup.clean(stages.select("h3:containsWholeText(The Aku Realms)+ul").html(), Safelist.basic())
                    .replaceAll("<[^>]*>","")+"\n");
            writer.write("------------Stories of Legend--------\n");
            writer.write(Jsoup.clean(stages.select("h3:containsWholeText(Stories of Legend)+ul").html(), Safelist.basic())
                    .replaceAll("<[^>]*>","")+"\n");
            writer.write("------------Uncanny Legends------\n");
            writer.write(Jsoup.clean(stages.select("h3:containsWholeText(Uncanny Legends)+ul").html(), Safelist.basic())
                    .replaceAll("<[^>]*>","")+"\n");
            writer.write("-----------Event Stages---------\n");
            writer.write(Jsoup.clean(stages.select("h3:containsWholeText(Event Stages)+ul").html(), Safelist.basic())
                    .replaceAll("<[^>]*>","")+"\n");
            writer.close();
        }catch (IOException e){
            System.out.println(e+" "+name);
        }
    }
}