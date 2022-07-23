/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author tiendang
 */
public class UrlHelper {

    public static String minimizeUrl (String url) {
        String result = url;
        char[] urls = url.toCharArray();
        for (char c : urls) {
            if (c == '/') {
                result = url.replaceFirst("/", "");
                continue;
            } else {
                break;
            }
        }

        result = result.replaceAll("\\/\\*$", "");

        return result;
    }

    public static String getResourceUrl (String url) {
        String resource = url.replace("/onlinelearn", "");
        resource = minimizeUrl(resource);
        return resource;
    }
}
