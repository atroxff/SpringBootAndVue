//package springboot.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import springboot.kafka.Consumer;
//import springboot.kafka.Producer;
//
//
//@RestController
//public class KafkaController {
//
//	@Autowired
//	private Consumer consumer;
//	@Autowired
//	private Producer producer;
//	@Autowired
//	KafkaTemplate kafkaTemplate;
//
//
//	@GetMapping("/hello")
//	public String hello(){
//		return "this is SpringBoot Demo";
//	}
//
//	@GetMapping("/kafka")
//	public String kafka(){
//		producer.send();
//		return "kafka send";
//	}
//}
