package spring.assembler;

import lombok.Getter;
import lombok.Setter;
import spring.memberData.MemberDAO;
import spring.password.ChangePasswordService;
import spring.register.MemberRegisterService;
@Getter@Setter
public class Assembler {

    // 이 패키지는 @Autowired를 좀더 이해하기 위해서 Assembler 를 한번 사용해보았다
    // 이러면 어노테이션으로 가려졌던 순서를 어느정도 파악하기 용이하다


    private MemberDAO memberDAO;
    private ChangePasswordService changePasswordService;
    private MemberRegisterService memberRegisterService;

    public Assembler(){
        memberDAO=new MemberDAO();
        changePasswordService=new ChangePasswordService(memberDAO);
        memberRegisterService=new MemberRegisterService(memberDAO);
    }

    // Assembler 의 역할을 spring 이 해주는 것이다
    // spring의 autowired 를 이용하여 주입을 한 이후
    // 인스턴스를 생성하는것을 자동적으로 Bean 으로 IOC 컨테이너에 생성함으로써
    // Assembler 의 인스턴스 생성 하는 작업을 안할수 있게 되는 것이다
}
