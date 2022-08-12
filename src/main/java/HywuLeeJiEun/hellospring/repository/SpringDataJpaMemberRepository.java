package HywuLeeJiEun.hellospring.repository;

import HywuLeeJiEun.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JPA를 활용하기 위한 JPA 스프링 데이터 활용하기

// interface 확장을 위한 extends, tip. 인터페이스는 다중 상속이 가능하다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
        Long>, MemberRepository {
    Optional<Member> findByName(String name);
}