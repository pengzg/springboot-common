package xyz.carjoy.question.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.test.service.ActiveMqSendService;

@Transactional
@Service("activeMqSendService")
public class ActiveMqSendServiceImpl implements ActiveMqSendService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    @Autowired
    private JmsTemplate jmsTemplate;

    //调用方法启动一次
    public  void  send(String destination,String msg){
        // 发送queue
//        jmsMessagingTemplate.convertAndSend(destination, msg);
        // 发送topic
//        jmsTemplate.send(destination, new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                Destination queue = session.createTopic("testTopic");
//                MessageProducer producer = session.createProducer(queue);
//                TextMessage textMessage = session.createTextMessage("这是什么东西");
//                textMessage.setText("这是什么玩意");
//                producer.send(textMessage);
//                return null;
//            }
//        });
    }


}
