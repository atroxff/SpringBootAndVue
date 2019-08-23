package springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/")
	public String toIndex(){
		return "forward:/index";
	}
	
	@RequestMapping("/index")
	public String index(){
		return "hello1";
	}

	@RequestMapping("/layui1")
	public String layui(){
		return "layui_demo1";
	}
	@RequestMapping("/layui2")
	public String layui2(){
		return "layui_demo2";
	}
}
