package xyz.carjoy.question.test.service.impl;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.test.service.KafkaMessageSendService;

@Service
@Transactional
public class KafkaMessageSendServiceImpl implements KafkaMessageSendService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void sendMessage(String topic, String key, String Message) {
        kafkaTemplate.send(new ProducerRecord<String, String>(topic,key, Message));
    }
}
