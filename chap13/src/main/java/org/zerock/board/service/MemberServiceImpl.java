package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void update(Member member) {
        Member originMember = memberRepository.findByEmail(member.getEmail());
        originMember.update(member);
        memberRepository.save(originMember);
    }

    @Override
    public void delete(String email) {
        memberRepository.deleteById(email);
    }

    @Override
    public boolean login(Member member) {
        return null != memberRepository.findByEmailAndPwd(member.getEmail(), member.getPwd());
    }
}
