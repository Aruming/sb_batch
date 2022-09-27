package com.ll.exam.spring_batch.app.member.service;

import com.ll.exam.spring_batch.app.cash.entity.CashLog;
import com.ll.exam.spring_batch.app.cash.service.CashService;
import com.ll.exam.spring_batch.app.member.entity.Member;
import com.ll.exam.spring_batch.app.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final CashService cashService;

    @Transactional
    public Member join(String username, String password, String email) {
        Member member = Member
                .builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        memberRepository.save(member);

        return member;
    }

    @Transactional
    public long addCash(Member member, long price) {
        CashLog cashLog = cashService.addCash(member, price);

        long newRestCash = member.getRestCash() + cashLog.getPrice();
        member.setRestCash(newRestCash);
        memberRepository.save(member);

        return newRestCash;
    }

    public long getRestCash(Member member) {
        return member.getRestCash();
    }
}
