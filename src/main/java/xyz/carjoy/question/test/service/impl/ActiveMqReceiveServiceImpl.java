package xyz.carjoy.question.test.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.test.service.ActiveMqReceiveService;
@Transactional
@Service("activeMqReceiveService")
public class ActiveMqReceiveServiceImpl implements ActiveMqReceiveService {

    @Override
//    @JmsListener(destination = "test0001",containerFactory = "jmsListenerContainerQueue")
    public void receive() {

    }

}
