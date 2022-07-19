package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //해당 new MemoryMemberRepository()객체와 test의 new MemoryMemberRepository()객체가 다름
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //따라서 동일한 객체 사용을 위해 아래처럼 사용함
    private final MemberRepository memberRepository;

    //new MemoryMemberRepository() 생성대신 외부에서 넣어주게 바꿈
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); 대체제
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //내가 직접 new해서 객체를 생성하지 않고, 외부에서 memberRepository를 넣어줌 = Dependency Injection = DI

    //회원가입
    public Long join(Member member){
        /*
        //같은 이름이 있는 중복 회원 X
        //멤버에 값이 있으면 throw절 실행 / Optional이기 때문에 가능한 옵션
        //optional안에 member객체가 존재 / optional을 통해 여러 메서드 사용가능
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
            /*
            //메소드로 뽑기 가능
            memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                        throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
            //findbyName 실행 > 결과는 Optional member이다.
            // > Optional member니까 바로 .ifPresent메서드 사용가능
            */
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);  //회원저장
        return member.getId();
    }
    //회원중복체크 메서드
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();  //반환형 = list
    }
    //아이디로 회원 조회
    public  Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
