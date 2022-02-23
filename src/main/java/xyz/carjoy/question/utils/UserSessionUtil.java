package xyz.carjoy.question.utils;


import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.carjoy.question.component.RedisDao;
import xyz.carjoy.question.component.SpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class UserSessionUtil {
    private static final int outTime = 86400;
    private static final String keyPrefix = "MOBILE_LOGIN_USER_";
    private static RedisDao redisDao = (RedisDao) SpringUtils.getBean("redisDao");

    public UserSessionUtil() {
    }

    public static SessionInfo getSession(HttpServletRequest request) {
        return request == null ? null : (SessionInfo)request.getSession().getAttribute("RM_LOGIN_USER");
    }

    public static void setSession(HttpServletRequest request, SessionInfo si) {
        if (request != null) {
            request.getSession().setAttribute("RM_LOGIN_USER", si);
        }

    }

    public static SessionInfo getSession() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if (ra == null) {
            return null;
        } else {
            HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
            return request == null ? null : getSession(request);
        }
    }

    public static Object getSession(String attr) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
        return request == null ? null : (String)request.getSession().getAttribute(attr);
    }

    public static SessionInfo getSessionInCache(String token) {
        if (StringUtils.isBlank(token)) {
            throw new BusinessException(HttpCode.SESSION_OUT.toString(), "登陆失效，请重新登陆！");
        } else {
            SessionInfo session = (SessionInfo)redisDao.getObj("MINI_LOGIN_USER:" + token);
            if (session != null) {
                redisDao.setObj("MINI_LOGIN_USER:" + token, session, 86400);
                return session;
            } else {
                throw new BusinessException(HttpCode.SESSION_OUT.toString(), "登陆失效，请重新登陆！");
            }
        }
    }

    public static void setSessionToCache(SessionInfo session) {
        String token = UUID.randomUUID().toString().replace("-", "");
        session.setToken(token);
       redisDao.setObj("MINI_LOGIN_USER:" + token, session, 86400);
    }

    public static void updateSessionToCache(SessionInfo session) {
        redisDao.setObj("MINI_LOGIN_USER:" + session.getToken(), session, 86400);
    }
}