/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenlinkdetector2;

import java.io.FileWriter;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author nitishchandra
 */
public class Consumer implements Callable {

    public String s;
    public int LEVEL_SEARCH = 20;
    public DataStructure data;
    public FileWriter fw;

    public void Start() {

        crawl(s);

    }

    public boolean equals(Consumer obj) {
        return (this.s.equals(obj.s));

    }

    public Consumer(Consumer obj) {
        this.s = obj.s;
        this.data = obj.data;
        this.fw = obj.fw;

    }

    public Consumer(String s, DataStructure d, FileWriter f) {
        this.s = s;
        this.data = d;
        this.fw = f;
    }

    public void crawl(String url) {
        int count = 0;

        System.out.println("Establishing connection with" + url + Thread.currentThread().getId());
        String geturl = url;
        HashSet<String> anchors;
        anchors = new HashSet<String>();
        
            try {
                Document doc = Jsoup.connect(geturl).get();
                Elements ele = doc.select("a");
                for (Element e : ele) {
                    String s = e.attr("href");

                    HtmlTools.fixUrl(s, geturl);
                    anchors.add(HtmlTools.fixUrl(s, geturl));

                }
                for (String e : anchors) {

                    if (count <= LEVEL_SEARCH) {
                        ReaderUrl.EstablishConnection(e, geturl, this.data, this.fw);
                        count++;
                    } else {
                        break;
                    }

                }

            } catch (Exception ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        

    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(100);
        Start();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
