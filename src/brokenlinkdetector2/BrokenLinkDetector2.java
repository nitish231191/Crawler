/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenlinkdetector2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author nitishchandra
 */
public class BrokenLinkDetector2 {

    private static int NUMBER_OF_THREADS = 2;
    private static final int MAX_PAGES_TO_SEARCH = 3;
    private static Set<String> brokenlinks = new HashSet<String>();
    private static List<String> pagesToVisit = new LinkedList<String>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Enter the seed url");
        Scanner in = new Scanner(System.in);
        String url = in.next();
        File output = new File("output.txt");
        FileWriter fw = new FileWriter(output);
        DataStructure data = new DataStructure(url);
        List<Callable<Consumer>> callables = new LinkedList<Callable<Consumer>>();

        DataStructureUtil.addElement(callables, data, fw);

        ExecutorService executorService = Executors.newWorkStealingPool();
        long starttime = System.currentTimeMillis();
        long end = starttime + 100000L;
        for (int i = 0; i < callables.size() && i < MAX_PAGES_TO_SEARCH; i++) {

            executorService.invokeAll(callables, 100, TimeUnit.SECONDS);
            DataStructureUtil.addElement(callables, data, fw);

        }

        fw.close();

    }
}
