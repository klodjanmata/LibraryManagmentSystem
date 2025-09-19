package Actions;

import Entity.Member;
import Repository.MemberRepository;
import Util.Helper;

import java.util.ArrayList;
import java.util.List;

public class MemberActions {

    private List<Member> memberList;
    private MemberRepository memberRepository;

    public MemberActions(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberActions(List<Member> memberList) {
        this.memberList = memberList;
    }

    public MemberActions(){
        memberList = new ArrayList<>();
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public void addMember(){
        System.out.println("Add the necessary member information");
        Member member = new Member();
        member.setName(Helper.getStringFromUser("Name"));
        member.setEmail(Helper.getStringFromUser("Email"));
        member.setPhone(Helper.getStringFromUser("Phone"));
        member.setMembership_date(Helper.getLocalDateFromUser("Date"));

        memberRepository.saveMember(member);  // <-- Save to DB
        System.out.println("Member with ID: " + member.getId() + " added successfully");
    }
}
