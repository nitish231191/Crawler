/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author nitishchandra
 */
public class Crawler extends Thread {

    /** Instance of Data Structure **/
    DataStruc data;

    /** Number of page connections allowed before program terminates **/
    private final int INDEX_LIMIT = 10;

    /** Initial URL to visit **/
    public Crawler(DataStruc d) {
        data = d;
    }

    public void run() {

        // Counter to keep track of number of indexed URLS
        int counter = 0;

        // While URL's left to visit
        while((data.url_to_visit_size() > 0) ) {

            // Pop next URL to visit from stack
            String currentUrl = data.getURL();
            System.out.println("Current url "+currentUrl+"THread id "+Thread.currentThread().getId());

            try {
                // Fetch and parse HTML document
                Document doc = Jsoup.connect(currentUrl)                 
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36")
                        .referrer("http://www.google.com")
                        .timeout(12000)
                        .followRedirects(true)
                        .get();

                // Increment counter if connection to web page succeeds
                counter++;

                /** .select returns a list of elements (links in this case) **/
                Elements links = doc.select("a[href]"); // Relative URL

                // Add newly found links to stack
                addLinksToQueue(links);                             

            } catch (IOException e) {
                //e.printStackTrace();
            //    System.out.println("Error: "+currentUrl);
            }               
        }       
    }

    public void addLinksToQueue(Elements el) {
        // For each element in links
        for(Element e : el) {           

            String theLink = e.attr("abs:href"); // 'abs' prefix ensures absolute url is returned rather then relative url ('www.reddit.com/hello' rather then '/hello')

            if(theLink.startsWith("http") && !data.oldLink(theLink)) {
                data.addURL(theLink);
                data.addVisitedURL(theLink); // Register each unique URL to ensure it isnt stored in 'url_to_visit' again
            //    System.out.println(theLink);
            }               
        }   
    }
}
