package xyz.carjoy.question.miniprogram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.miniprogram.service.IMiniMemberService;
import xyz.carjoy.question.utils.BusinessException;
import xyz.carjoy.question.utils.HttpCode;
import xyz.carjoy.question.utils.Result;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mimiprogram/memberControl")
public class MiniMemberController {
    private static final Logger log = LoggerFactory.getLogger(MiniMemberController.class);


    @Autowired
    private IMiniMemberService miniMemberService;
    /**
     * 得到参数值
     * @param request
     * @return
     */
    @RequestMapping("/getMemberInfo")
    @ResponseBody
    public Result getMemberInfo(HttpServletRequest request, String mb_id) {
        Result result = new Result();
        try {


            result.setData(HttpCode.OK, miniMemberService.getMemberInfo(mb_id));
        } catch (BusinessException e) {
            result.setExecRes(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            log.error("【参数】异常!【" + e.getMessage() + "】", e);
            result.setError("参数失败");
        }
        return result;
    }
}
