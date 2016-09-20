/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author nitishchandra
 */
public class controller {

    public static void main(String args[]) throws InterruptedException {

        DataStruc data = new DataStruc("http://jsoup.org/");
//ExecutorService exec = Executors.newCachedThreadPool();

        Thread crawl1 = new Crawler(data);
        Thread crawl2 = new Crawler(data);

        crawl1.start();
        crawl2.start();
   }    
}