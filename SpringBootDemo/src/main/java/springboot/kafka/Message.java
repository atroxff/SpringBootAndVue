package springboot.kafka;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @auther: hefeng
 * @creatTime: 2019-7-16 15:11:51
 */
@Data
@Accessors(chain = true)
public class Message {

    private Long id;
    private String msg;
    private Date sendTime;

}
