package xyz.carjoy.question.test.controller;

import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.utils.Pager;
import xyz.carjoy.question.utils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
@RequestMapping("phone/test/rocketmq")
public class RocketMqProducerController {

//    @Autowired
//    private DefaultMQProducer producer;

    /**
     * 发送
     * @param request
     * @param pager
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public Result send(HttpServletRequest request, Pager pager, String a1, String a2) throws Exception {

        Result result = new Result();

        // topic消息的目的地  和message绑定
        // 单条发送
//        Message msg = new Message("test0002","tes6666t".getBytes());
//        SendResult send = producer.send(msg);
        // 多条发送
        ArrayList<Message> list = new ArrayList<>();
        Message msg1 = new Message("testphone","test 第一条".getBytes());
        Message msg2 = new Message("testphone","test 第二条".getBytes());
        Message msg3 = new Message("testphone","test 第三条".getBytes());
        Message msg4 = new Message("testphone","test 第四条".getBytes());
        list.add(msg1);
        list.add(msg2);
        list.add(msg3);
        list.add(msg4);
//        SendResult send = producer.send(list);
//        System.out.println(send);
//        producer.shutdown();

//        activeMqSendService.send("test0001","helloworld");

        return result;
    }
}
