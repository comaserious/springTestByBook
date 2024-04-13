package spring.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import spring.MemberData.MemberDAO;
import spring.password.ChangePasswordService;
import spring.print.MemberListPrint;
import spring.print.MemberPrinter;
import spring.register.MemberRegisterService;

@Configuration
public class ContextConfiguration {

    @Bean
    public MemberDAO memberDAO(){
        return new MemberDAO();
    }
    @Bean
    public ChangePasswordService changePasswordService(){
        return new ChangePasswordService();
    }

    @Bean
    MemberRegisterService memberRegisterService(){
        return new MemberRegisterService();
    }


    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }
    @Bean
    public MemberListPrint memberListPrint() {
        return new MemberListPrint(memberDAO(),memberPrinter());
    }
}
