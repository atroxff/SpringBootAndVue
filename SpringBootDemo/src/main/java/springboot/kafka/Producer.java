package springboot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import springboot.util.JsonUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @auther: hefeng
 * @creatTime: 2019-7-16 15:22:14
 */
@Component
public class Producer {

    @Autowired
    KafkaTemplate kafkaTemplate;

    /**
     * 发送数据
     * @param
     */
    public void send(){

        Message msg = new Message();
        msg.setId(System.currentTimeMillis());
        msg.setMsg(UUID.randomUUID().toString());
        msg.setSendTime(new Date());
        String json = JsonUtils.objectToJson(msg);
        kafkaTemplate.send("test1", json);
    }
}

