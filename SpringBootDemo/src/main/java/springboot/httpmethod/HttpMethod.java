package springboot.httpmethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @description:java发送http请求
 * @auther: hefeng
 * @creatTime: 2019-8-20 09:41:43
 */
public class HttpMethod {
    public String sendPost(String url, String params) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0; Windows NT 5.1;SV1)");
            //发送Post
            connection.setDoInput(true);
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();

        }

        return null;
    }
}
