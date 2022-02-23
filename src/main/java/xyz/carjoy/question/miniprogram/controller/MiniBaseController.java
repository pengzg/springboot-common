package xyz.carjoy.question.miniprogram.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.common.base.cache.BaseParameterCache;
import xyz.carjoy.question.utils.BusinessException;
import xyz.carjoy.question.utils.HttpCode;
import xyz.carjoy.question.utils.Result;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mimiprogram/baseControl")
public class MiniBaseController {
    private static final Logger log = LoggerFactory.getLogger(MiniBaseController.class);

    /**
     * 得到参数值
     * @param request
     * @return
     */
    @RequestMapping("/getParameterVal")
    @ResponseBody
    public Result getParameterVal(HttpServletRequest request, String key) {
        Result result = new Result();
        try {


            result.setData(HttpCode.OK, BaseParameterCache.getInstance().getKeyValue(key));
        } catch (BusinessException e) {
            result.setExecRes(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            log.error("【参数】异常!【" + e.getMessage() + "】", e);
            result.setError("参数失败");
        }
        return result;
    }
}
