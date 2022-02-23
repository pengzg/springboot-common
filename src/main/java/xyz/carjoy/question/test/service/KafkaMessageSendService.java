package xyz.carjoy.question.test.service;

public interface KafkaMessageSendService {
    public void sendMessage(String topic, String key, String Message);
}
