package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원저장

    Optional<Member> findById(Long id); // 방금 id로 회원을 찾음

    Optional<Member> findByName(String name); //null을 그대로 반환하는대신 optional로 감싸서 반환

    List<Member> findAll(); //모든 회원 가져옴
}
