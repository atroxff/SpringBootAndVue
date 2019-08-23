package springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/crawl")
public class CrawlController {
    @RequestMapping(value = "/")
    public String index(){

        return "hello";
    }

    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    @ResponseBody
    public Map submit(String name,String crawDBPath,String seed){
        Map<String, String> map = new HashMap();
        map.put("name",name);
        map.put("crawDBPath",crawDBPath);
        map.put("seed",seed);
        return map;
    }
}
