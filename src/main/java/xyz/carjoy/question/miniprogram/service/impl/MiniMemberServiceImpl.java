package xyz.carjoy.question.miniprogram.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.carjoy.question.miniprogram.service.IMiniMemberService;
import xyz.carjoy.question.member.service.IMemberBaseService;
import xyz.carjoy.question.member.vo.MemberBaseVO;

@Service("miniMemberService")
public class MiniMemberServiceImpl implements IMiniMemberService {
    private static final Logger log = LoggerFactory.getLogger(MiniMemberServiceImpl.class);

    @Autowired
    private IMemberBaseService memberBaseService;

    @Override
    public MemberBaseVO getMemberInfo(String mb_id) {
        return memberBaseService.getDetail(mb_id);
    }
}
