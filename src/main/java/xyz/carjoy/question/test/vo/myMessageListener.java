package xyz.carjoy.question.test.vo;

import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class myMessageListener implements  MessageListenerConcurrently {
    @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg: list
                ) {
                    System.out.println(new String(msg.getBody()) );
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
}
