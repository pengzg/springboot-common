package xyz.carjoy.question.common.sys.controller;


import xyz.carjoy.question.common.base.service.IBaseAppVersionService;
import xyz.carjoy.question.common.sys.model.SysMenu;
import xyz.carjoy.question.common.sys.model.SysUser;
import xyz.carjoy.question.common.sys.service.ISysMenuService;
import xyz.carjoy.question.common.sys.service.ISysUserService;
import xyz.carjoy.question.common.sys.vo.MenusListVO;
import xyz.carjoy.question.common.sys.vo.SysUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import xyz.carjoy.question.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feizhang
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/admin/login/loginControl")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(SysLoginLogController.class);

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private IBaseAppVersionService baseAppVersionService;

    
    /**
     * 登录
     */
    /**
     * 用户登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/doLogin")
    public Result doLogin(HttpServletRequest request, HttpServletResponse response, SysUser data) {

        Result result = new Result();
        String passWord = request.getParameter("passWord");
        String loginName = request.getParameter("loginName");
        try {

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("loginName", loginName);
            map.put("passWord", passWord);
            SysUserVO suVO = sysUserService.login(map);
            if (suVO != null) {
                SessionInfo sessionInfo = new SessionInfo();
                sessionInfo.setUserid(suVO.getSu_id());
                sessionInfo.setLoginname(suVO.getSu_loginname());
                sessionInfo.setUsername(suVO.getSu_name());
                

                //取可以访问的菜单信息
                setMenuPrivValue(sessionInfo,suVO);
                UserSessionUtil.setSession(request, sessionInfo);
                result.setData(HttpCode.OK, null, "登录成功！");
            } else {
                result.setData(HttpCode.INTERNAL_SERVER_ERROR, null, "登录失败！");
            }
        }catch(BusinessException e){

            log.error(e.getMessage(), e);
            result.setData(HttpCode.INTERNAL_SERVER_ERROR, null, e.getMessage());
        }
        return result;
    }

    /**
     * 得到 session
     *
     * @param request
     * @return
     */
    @RequestMapping("/getSessionInfo")
    @ResponseBody
    public Result getUserInfo(HttpServletRequest request) {
        Result result = new Result();
        SessionInfo session = UserSessionUtil.getSession(request);
        result.setData(HttpCode.OK, session, null);
        return result;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/queryMenuByUser")
    @ResponseBody
    public Result queryMenuByUser(HttpServletRequest request) {
        Result result = new Result();
        SessionInfo session = UserSessionUtil.getSession(request);
        //
        Map<String, Object> map = new HashMap<>();
        map.put("sm_state", GlobalConstants.STATUS_ON);
        map.put("sur_userid", session.getUserid());

        List<MenusListVO> menuList = sysMenuService.getMenuList(map);
        result.setData(HttpCode.OK, menuList, null);
        return result;
    }

    private void setMenuPrivValue(SessionInfo sessionInfo,SysUserVO user) {
        // 取可以访问的菜单信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sur_userid", user.getSu_id());
        List<SysMenu> list = sysMenuService.select(map);
        List<String> menusList = new ArrayList();
        for(SysMenu t:list){
            menusList.add(t.getSm_url());
        }
       // sessionInfo.setMenusList(menusList);
    }

    /**
     * 注销系统
     */
    @ResponseBody
    @RequestMapping("/loginOut")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        if (request.getSession() != null) {
            request.getSession().invalidate();
        }
        result.setData(HttpCode.OK, null, "退出成功！");
        return result;
    }










  

    public Map<?, ?> getQueryCondition(HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }


}