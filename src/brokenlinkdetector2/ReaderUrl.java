
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenlinkdetector2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author nitishchandra
 */
public class ReaderUrl {

    private String url;
    public static int statusCode;
    public static String domain;
    private static final int MAX_PAGES_TO_SEARCH = 10;

    public static void EstablishConnection(String url, String baseUrl, DataStructure data, FileWriter f) throws MalformedURLException, IOException, URISyntaxException {
        //  System.out.println("entering Establishing Connection");

        URL obj = null;
        HttpURLConnection con = null;
        obj = new URL(url);
        URL baseurl = new URL(baseUrl);
        String baseDomain = baseurl.toURI().getHost();
        String currentDomain = obj.toURI().getHost();

        con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        statusCode = responseCode;
        if (responseCode >= 200 && responseCode <= 300 && baseDomain.equals(currentDomain)) {
            if ((!data.findinurltovisit(url)) && (!data.oldLink(url))) {
                System.out.println("Entering good connection " + url);
                data.addURL(url);
                data.addVisitedURL(url);

            }

        } else if (!data.findinbadlink(url)) {
            data.addBrokenURL(url);
            System.out.println("Entering  bad connection " + url);
            f.write(url);
            f.write("\n");

        }

    }

}
