package xyz.carjoy.question.configuration;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

//@Configuration
public class RocketMqProducerConfig {

    private static final Logger log = LoggerFactory.getLogger(RocketMqProducerConfig.class);

    @Bean
    public DefaultMQProducer getRocketMqProducer() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("xx");
        // 设置nameserver地址
        producer.setNamesrvAddr("42.192.16.23:9876");
        producer.start();
        log.info("RocketMq producer启动了");
        return producer;
    }

}
