package HywuLeeJiEun.hellospring.service;

import HywuLeeJiEun.hellospring.repository.MemoryMemberRepository;
import HywuLeeJiEun.hellospring.domain.Member;
import HywuLeeJiEun.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// spring내에 등록이 됨.
//@Service //componant가 등록되어 있음!

// JPA를 쓰기 위해 Transactional을 작성.
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {

//        long start = System.currentTimeMillis();
//
//        try {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join " + timeMs + "ms");
//        }
    }


    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // Optional을 지워도 Ok.
                .ifPresent(m -> { //값이 있으면 들어옴.
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    } // tip. Optional을 활용하기에 다양하게 사용할 수 있음 (Ex > .ifPresent ..)
    // 기본적으로 get 등을 활용해 직접적으로 꺼내는 것은 권장하지 않음!

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
//        long start = System.currentTimeMillis();
//        try {
        return memberRepository.findAll();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("findMembers " + timeMs + "ms");
//        }
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
