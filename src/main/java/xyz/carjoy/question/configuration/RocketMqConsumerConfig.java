package xyz.carjoy.question.configuration;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import xyz.carjoy.question.test.vo.myMessageListener;

//@Configuration
public class RocketMqConsumerConfig {
    private static final Logger log = LoggerFactory.getLogger(RocketMqProducerConfig.class);
    @Bean
    public DefaultMQPushConsumer getRocektMqConsumer() throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("testphone");
        consumer.setNamesrvAddr("42.192.16.23:9876");
        consumer.subscribe("testphone", "*");
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                for (MessageExt msg: list
//                ) {
//                    System.out.println(new String(msg.getBody()) );
//                }
//
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
        consumer.registerMessageListener(new myMessageListener());

        consumer.start();
        log.info("RocketMqconsumerstart==>");
        System.out.println("testConsumer-->start.......");
        return consumer;

    }

}
