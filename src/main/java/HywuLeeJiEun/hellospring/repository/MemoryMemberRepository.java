package HywuLeeJiEun.hellospring.repository;


import HywuLeeJiEun.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemoryMemberRepository implements MemberRepository {
    // tip. HashMap의 경우, 동시성 문제가 있을 수 있기에 실무에서는 지양하는 편이다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        // 옵션이 null이더라도 감쌀 수 있도록 함.
        return Optional.ofNullable(store.get(id));
    }
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // test 시, 순서대로 진행되지 않음. 에러를 막기 위해 초기화 시키는 역할을 함.
    public void clearStore() {
        store.clear();
    }
}

