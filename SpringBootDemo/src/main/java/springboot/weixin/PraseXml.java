package springboot.weixin;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;
import org.xml.sax.InputSource;

import javax.xml.transform.sax.SAXResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @auther: hefeng
 * @creatTime: 2019-8-19 16:25:28
 */
public class PraseXml {

    public static void main(String[] args) throws DocumentException, FileNotFoundException {
        HashMap<String, String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();
        //xml文件形式
        File config = ResourceUtils.getFile("classpath:config.xml");
        Document doc = reader.read(config);
        //字符串形式
//        String configStr = "<xml>\n" +
//                "    <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
//                "    <FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
//                "    <CreateTime>123456789</CreateTime>\n" +
//                "    <MsgType><![CDATA[event]]></MsgType>\n" +
//                "    <Event><![CDATA[LOCATION]]></Event>\n" +
//                "    <Latitude>23.137466</Latitude>\n" +
//                "    <Longitude>113.352425</Longitude>\n" +
//                "    <Precision>119.385040</Precision>\n" +
//                "</xml>";
//        StringReader read = new StringReader(configStr);
//        InputSource source = new InputSource(read);
//        Document doc = reader.read(source);

        Element root = doc.getRootElement();

        parseXml(root, map);
        for (Map.Entry<String, String> item : map.entrySet()) {
            System.out.println(item.getKey() + "=" + item.getValue());
        }

    }

    private static void parseXml(Element root, HashMap<String, String> map) {
        List<Element> eList = root.elements();

        if (eList.size() == 0) {
            map.put(root.getName(), root.getTextTrim());
        }else {
            for (Element e : eList) {
                map.put(e.getName(), e.getTextTrim());
            }
        }
    }
}
