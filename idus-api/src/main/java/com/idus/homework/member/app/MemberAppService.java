package com.idus.homework.member.app;

import com.idus.homework.common.data.PrincipalDetails;
import com.idus.homework.common.exception.MemberDuplicateException;
import com.idus.homework.common.exception.MemberNotFoundException;
import com.idus.homework.member.domain.Member;
import com.idus.homework.member.infra.service.MemberCommandService;
import com.idus.homework.member.infra.service.MemberQueryService;
import com.idus.homework.member.representation.MemberDetailResponse;
import com.idus.homework.member.representative.MemberRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class MemberAppService implements UserDetailsService {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    public MemberAppService(MemberCommandService memberCommandService, MemberQueryService memberQueryService) {
        this.memberCommandService = memberCommandService;
        this.memberQueryService = memberQueryService;
    }

    @Transactional
    public void join(MemberRequest memberRequest) throws MemberDuplicateException {
        Member member = memberQueryService.findByEmail(memberRequest.getEmail());
        if (!ObjectUtils.isEmpty(member)) {
            throw new MemberDuplicateException();
        }
        memberCommandService.save(memberRequest);
    }

    @Transactional(readOnly = true)
    public MemberDetailResponse findById(long id) throws MemberNotFoundException {
        Member member = memberQueryService.findById(id).orElseThrow(MemberNotFoundException::new);
        return MemberDetailResponse.from(member);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Member member = memberQueryService.findByEmail(email);
        if (!ObjectUtils.isEmpty(member)) {
            member.lastLoginDateUpdate();
            return new PrincipalDetails(member);
        }
        throw new UsernameNotFoundException("NotFound.UserDetails.username");
    }

}
