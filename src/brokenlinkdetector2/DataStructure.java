/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenlinkdetector2;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author nitishchandra
 */
public class DataStructure {
    

/**
 *
 * @author nitishchandra
 */


   
    private ConcurrentLinkedQueue<String> url_to_visit = new ConcurrentLinkedQueue<String>();
    private ArrayList<String> visited_url = new ArrayList<String>();

    private ConcurrentLinkedQueue<String> badlink = new ConcurrentLinkedQueue<String>();

    public DataStructure(String initial_url) {
        url_to_visit.offer(initial_url);
    }

    public String getbadlink() {
        return badlink.poll();
    }

   
    public void addURL(String url) {
        //   System.out.println("Current id of the thred"+Thread.currentThread().getId()+" "+url_to_visit.size());
        url_to_visit.offer(url);
    }

    public int getsizeofURl() {
        return url_to_visit.size();
    }

    public int getsizeofbadlink() {
        return badlink.size();
    }

    
    public String getURL() {
        return url_to_visit.poll();
    }

    public void addVisitedURL(String url) {
        visited_url.add(url);
    }

    public void addBrokenURL(String url) {

        badlink.add(url);
    }

    public boolean findinbadlink(String url) {
        return badlink.contains(url);
    }

    public boolean findinurltovisit(String url) {
        return url_to_visit.contains(url);
    }

    public boolean oldLink(String link) {
        for (String s : visited_url) {
            if (s.equals(link)) {
                return true;
            }
        }
        return false;
    }

}
