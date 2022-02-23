package xyz.carjoy.question.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.test.service.ActiveMqSendService;
import xyz.carjoy.question.utils.Pager;
import xyz.carjoy.question.utils.Result;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("phone/test")
public class ActiveMqSendController {

    @Autowired
    private ActiveMqSendService activeMqSendService;

    /**
     * 发送
     * @param request
     * @param pager
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public Result send(HttpServletRequest request, Pager pager, String a1, String a2) {

        Result result = new Result();

//        activeMqSendService.send("test0001","helloworld");

        return result;
    }
}
