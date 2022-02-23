package xyz.carjoy.question.miniprogram.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.carjoy.question.miniprogram.service.IMiniLoginService;
import xyz.carjoy.question.miniprogram.vo.MemberLoginVO;
import xyz.carjoy.question.member.model.MemberBase;
import xyz.carjoy.question.member.service.IMemberBaseService;
import xyz.carjoy.question.utils.DateUtil;
import xyz.carjoy.question.utils.SessionInfo;
import xyz.carjoy.question.utils.UserSessionUtil;
import xyz.carjoy.question.weixin.cache.WeixinInformationCache;
import xyz.carjoy.question.weixin.model.WeixinInformation;
import xyz.carjoy.question.weixin.service.IWeixinInformationService;
import xyz.carjoy.question.weixin.vo.WechatRepSession;
import xyz.carjoy.question.weixin.vo.WechatReqSession;

import java.util.HashMap;
import java.util.List;

@Transactional
@Service("miniloginService")
public class MiniLoginServiceImpl implements IMiniLoginService {

    @Autowired
    private IWeixinInformationService weixinInformationService;

    @Autowired
    private IMemberBaseService memberBaseService;

    @Override
    public SessionInfo onLogin(MemberLoginVO vo) {

        SessionInfo sessionInfo = new SessionInfo();
        WechatReqSession args = new WechatReqSession();
        WeixinInformation information = WeixinInformationCache.getInstance().get(vo.getAppidcode());
        args.setAppId(information.getWi_appid());
        args.setSecret(information.getWi_appsecret());
        args.setJs_code(vo.getLogincode());

        WechatRepSession rep = weixinInformationService.Jscode2session(args);

        // 判断用户是否存在如果不存在则创建用户
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("mb_openid", rep.getOpenid());
        List<MemberBase> mbList = memberBaseService.select(map);
        if (mbList !=null && mbList.size()>0) {
            MemberBase mbInfo = mbList.get(0);
            createSession(sessionInfo, mbInfo);
        } else {
//            MemberBase mbVO = new MemberBase();
//            mbVO.setMb_openid(rep.getOpenid());
//            mbVO.setMb_addtime(DateUtil.getCurrentDateToString2());
//            memberBaseService.insert(mbVO);
//            createSession(sessionInfo,mbVO);
        }
        sessionInfo.setOpenid(rep.getOpenid());
        UserSessionUtil.setSessionToCache(sessionInfo);
        return sessionInfo;
    }

    @Override
    public SessionInfo checkUser(MemberLoginVO loginVO) {
        SessionInfo sessionInfo = new SessionInfo();
        // 判断用户是否存在如果不存在则创建用户
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("mb_openid", loginVO.getOpenid());
        List<MemberBase> mbList = memberBaseService.select(map);
        if (mbList !=null && mbList.size()>0) {
            MemberBase mbInfo = mbList.get(0);
            createSession(sessionInfo, mbInfo);
        } else {
            MemberBase mbVO = new MemberBase();
            mbVO.setMb_openid(loginVO.getOpenid());
            mbVO.setMb_addtime(DateUtil.getCurrentDateToString2());
            mbVO.setMb_img(loginVO.getAvatarUrl());
            mbVO.setMb_name(loginVO.getNickName());
            mbVO.setMb_nickname(loginVO.getNickName());
            memberBaseService.insert(mbVO);
            createSession(sessionInfo,mbVO);
        }
        sessionInfo.setOpenid(loginVO.getOpenid());
        UserSessionUtil.setSessionToCache(sessionInfo);
        return sessionInfo;
    }

    private void createSession(SessionInfo sessionInfo, MemberBase mbInfo) {
        sessionInfo.setUserid(mbInfo.getMb_id());
        sessionInfo.setLoginname(mbInfo.getMb_name());
        sessionInfo.setUsername(mbInfo.getMb_name());
        sessionInfo.setNickname(mbInfo.getMb_nickname());
        sessionInfo.setAvatar(mbInfo.getMb_img());
    }


}
