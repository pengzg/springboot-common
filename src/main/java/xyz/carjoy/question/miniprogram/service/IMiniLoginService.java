package xyz.carjoy.question.miniprogram.service;

import xyz.carjoy.question.miniprogram.vo.MemberLoginVO;
import xyz.carjoy.question.utils.SessionInfo;

public interface IMiniLoginService {
    public SessionInfo onLogin(MemberLoginVO vo);

    public SessionInfo checkUser(MemberLoginVO loginVO);
}
