package Actions;

import Entity.Member;
import Repository.MemberRepository;
import Util.Helper;
import Util.HibernateUtil;
import lombok.Getter;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class MemberActions {

    @Getter
    private List<Member> memberList;
    private MemberRepository memberRepository;

    public MemberActions(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.memberList = new ArrayList<>();
    }

    public MemberActions() {
        this.memberList = new ArrayList<>();
        this.memberRepository = new MemberRepository();
    }

    public void addMember() {
        System.out.println("Add the necessary member information:");
        Member member = new Member();
        member.setName(Helper.getStringFromUser("Name"));
        member.setEmail(Helper.getStringFromUser("Email"));
        member.setPhone(Helper.getStringFromUser("Phone"));
        member.setMembership_date(Helper.getLocalDateFromUser("Membership Date"));

        memberRepository.create(member); // Save to DB
        memberList.add(member); // Add to local list
        System.out.println("Member with ID: " + member.getId() + " added successfully");
    }

    public void printAllMembers() {
        List<Member> members = memberRepository.findAll();
        if (members == null || members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        System.out.println("\n--- All Members ---");
        for (Member m : members) {
            System.out.println("ID: " + m.getId());
            System.out.println("Name: " + m.getName());
            System.out.println("Email: " + m.getEmail());
            System.out.println("Phone: " + m.getPhone());
            System.out.println("Membership Date: " + m.getMembershipDate());
            System.out.println("------------------");
        }
    }
    public List<Member> findByName(String memberName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "FROM Member m WHERE lower(m.name) LIKE :name", Member.class)
                    .setParameter("name", "%" + memberName.toLowerCase() + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // return empty list if error
        }
    }
}
