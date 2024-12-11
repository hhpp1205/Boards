package org.zerock.board.service;

import org.zerock.board.entity.Member;

import java.util.List;

public interface MemberService {
    List<Member> findAll();

    Member findByEmail(String email);

    void save(Member member);

    void update(Member member);

    void delete(String email);

    boolean login(Member member);
}
