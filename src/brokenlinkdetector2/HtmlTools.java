/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brokenlinkdetector2;

/**
 *
 * @author nitishchandra
 */
public class HtmlTools {

    public static String fixUrl(String inUrl, String domain) {
        String url = "";

        if (!inUrl.startsWith(domain)) {
            if (!inUrl.startsWith("http")) {
                if (inUrl.startsWith("/")) {
                    url = domain.concat(inUrl);
                } else {
                    url = domain.concat("/" + inUrl);
                }
            } else {
                url = inUrl;
            }

        }
        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        if (url.contains("#")) {
            url = url.substring(0, url.indexOf("#") - 1);
        }

        return url;

    }

}
