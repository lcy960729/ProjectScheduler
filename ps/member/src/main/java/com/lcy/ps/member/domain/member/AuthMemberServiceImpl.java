package com.lcy.ps.member.domain.member;

import com.lcy.ps.core.exception.NotRegisteredMemberException;
import com.lcy.ps.integrate.domain.member.AuthMemberService;
import com.lcy.ps.integrate.domain.member.Permission;
import com.lcy.ps.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthMemberServiceImpl implements AuthMemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void auth(long memberId, Permission permission) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotRegisteredMemberException::new);

        member.checkRegisteredAndPermission(permission);
    }
}
