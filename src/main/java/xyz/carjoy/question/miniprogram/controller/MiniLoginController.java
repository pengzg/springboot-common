package xyz.carjoy.question.miniprogram.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.miniprogram.service.IMiniLoginService;
import xyz.carjoy.question.miniprogram.vo.MemberLoginVO;
import xyz.carjoy.question.utils.BusinessException;
import xyz.carjoy.question.utils.HttpCode;
import xyz.carjoy.question.utils.Result;
import xyz.carjoy.question.utils.SessionInfo;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mimiprogram/loginControl")
public class MiniLoginController {
    private static final Logger log = LoggerFactory.getLogger(MiniLoginController.class);

    @Autowired
    private IMiniLoginService miniloginService;

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping("/onLogin")
    @ResponseBody
    public Result onLogin(HttpServletRequest request, String code) {
        Result result = new Result();
        try {
            String appidcode = request.getParameter("appidcode");
            log.info("====================" + appidcode);
            MemberLoginVO vo = new MemberLoginVO();
            vo.setLogincode(code);
            vo.setAppidcode(appidcode);

            result.setData(HttpCode.OK, miniloginService.onLogin(vo));
        } catch (BusinessException e) {
            result.setExecRes(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            log.error("【onLogin】异常!【" + e.getMessage() + "】", e);
            result.setError("登录失败");
        }
        return result;
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping("/updateMobile")
    @ResponseBody
    public Result updateMobile(HttpServletRequest request, String code) {
        Result result = new Result();
        try {
            String appidcode = request.getParameter("appidcode");
            log.info("====================" + appidcode);
            // WechatRepSession rep = memberLoginService.onLogin(code);

            result.setData(HttpCode.OK, null);
        } catch (BusinessException e) {
            result.setExecRes(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            log.error("【onLogin】异常!【" + e.getMessage() + "】", e);
            result.setError("登录失败");
        }
        return result;
    }

    /**
     * 检查用户是否注册
     * @param request
     * @return
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public Result checkUser(HttpServletRequest request, MemberLoginVO loginVO,String code) {
        Result result = new Result();
        try {
            String appidcode = request.getParameter("appidcode");
            log.info("====================" + appidcode);
            String  membername = request.getParameter("nickName");
            String  avatarUrl = request.getParameter("avatarUrl");

            SessionInfo sessionInfo = new SessionInfo();

            // WechatRepSession rep = memberLoginService.onLogin(code);

            result.setData(HttpCode.OK, miniloginService.checkUser(loginVO));
        } catch (BusinessException e) {
            result.setExecRes(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            log.error("【onLogin】异常!【" + e.getMessage() + "】", e);
            result.setError("登录失败");
        }
        return result;
    }

    /**
     * 检查用户是否注册
     * @param request
     * @return
     */
    @RequestMapping("/getSession")
    @ResponseBody
    public Result getSession(HttpServletRequest request, String code) {
        Result result = new Result();
        try {
            String appidcode = request.getParameter("appidcode");
            log.info("====================" + appidcode);
            // WechatRepSession rep = memberLoginService.onLogin(code);

            result.setData(HttpCode.OK, null);
        } catch (BusinessException e) {
            result.setExecRes(e.getErrorCode(), e.getMessage());
        } catch (Exception e) {
            log.error("【onLogin】异常!【" + e.getMessage() + "】", e);
            result.setError("登录失败");
        }
        return result;
    }
}
