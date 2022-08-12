package HywuLeeJiEun.hellospring.repository;

import HywuLeeJiEun.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// interface는 선언만이 존재하며, 멤버 변수와 멤버 메서드를 선언할 수 있으나,
// 접근 제한자는 설정할 수 없다.  => 객체의 속성 및 함수를 생성해야하는 경우에 사용
public interface MemberRepository {
    Member save(Member member);
    //Optional -> null 반환 방식 중 하나
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
