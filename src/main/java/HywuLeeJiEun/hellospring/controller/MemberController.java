package HywuLeeJiEun.hellospring.controller;

import HywuLeeJiEun.hellospring.domain.Member;
import org.springframework.stereotype.Controller;
import HywuLeeJiEun.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


// 스프링 컨테이너에 MemberController를 생성하여 spring에 넣어줌. 이후 관리
@Controller
public class MemberController {
    // 하단의 경우, 다른 컨트롤러들이 가져다 쓸 수 있기에 권장하지 않음!
    //private final MemberService = new MemberService();

    private final MemberService memberService;

    // 연결은 생성자로,
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        // 회원가입 join
        memberService.join(member);
        // 완료 이후 홈 화면으로 보냄.
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
