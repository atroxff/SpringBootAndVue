//package springboot.kafka;
//
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//import springboot.util.JsonUtils;
//
///**
// * @auther: hefeng
// * @creatTime: 2019-7-16 15:44:54
// */
//@Component
//public class Consumer {
//
//    @KafkaListener(topics = "test1")
//    public void receiveMsg(String content) {
//        Message m = JsonUtils.jsonToPojo(content, Message.class);
//        System.out.println("receive:" + m.toString());
//    }
//}
