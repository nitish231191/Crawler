/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawler;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author nitishchandra
 */
public class DataStruc {

    /** Queue to store URL's, can be accessed by multiple threads **/
    private ConcurrentLinkedQueue<String> url_to_visit = new ConcurrentLinkedQueue<String>();

    /** ArrayList of visited URL's **/
    private ArrayList<String> visited_url = new ArrayList<String>();

    public DataStruc(String initial_url) {
        url_to_visit.offer(initial_url);
    }

    // Method to add seed URL to queue
    public void addURL(String url) {
        url_to_visit.offer(url);
    }

    // Get URL at front of queue
    public String getURL() {
        return url_to_visit.poll();
    }

    // URL to visit size
    public int url_to_visit_size() {
        return url_to_visit.size();
    }

    // Add visited URL
    public void addVisitedURL(String url) {
        visited_url.add(url);
    }

    // Checks if link has already been visited
    public boolean oldLink(String link) {
        for(String s : visited_url) {
            if(s.equals(link)) {
                return true;
            }
        }   
        return false;
    }       

   
}