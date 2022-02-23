package xyz.carjoy.question.test.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.utils.Pager;
import xyz.carjoy.question.utils.Result;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("phone/test")
public class ActiveMqReceiveController {

//    @Autowired
//    private ActiveMqReceiveService activeMqReceiveService;

    /**
     * 发送
     * @param request
     * @param pager
     * @return
     */
    @RequestMapping("/reveive")
    @ResponseBody
    public Result receive(HttpServletRequest request, Pager pager) {

//        activeMqReceiveService.receive();

        String.valueOf((int)(Math.random()*9+1)*Math.pow(10,5));
        Result result = new Result();
        return result;
    }
}
