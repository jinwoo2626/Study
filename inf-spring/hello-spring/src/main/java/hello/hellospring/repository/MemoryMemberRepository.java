package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository{

    //key는 회원의 id = Long / 값은 member
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // member에 id, name / sequence추가
        store.put(member.getId(), member); //store에 저장 / map에 저장이 됨

        return member; //저장된 결과를 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // store에서 id로 값을 찾는다. / Null일때에는 Optional로 감싸서 반환시킴
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream() // 루프를 돌림
                .filter(member -> member.getName().equals(name)) //member.getName()이 파라미터로 넘어온 name과 같을 때 필터릴이 됨
                .findAny(); //찾으면 반환
       //루프를 돌면서 map에서 찾아지면 반환 / 없으면 Optional에 null이 포함되어 반환됨
        //스트림 생성 -> 필터(조건에 부합하는 데이터 거르기) -> 찾기 흐름
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //스토어에 있는 멤버들이 쭉 반환이 됨 / 10라인의 member
    }

    public void clearStore(){
        store.clear(); // 스토어를 비움
    }
}
