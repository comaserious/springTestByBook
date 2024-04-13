package spring.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.MemberData.WrongPasswordException;
import spring.password.ChangePasswordService;
import spring.password.MemberNotFoundException;
import spring.print.MemberListPrint;
import spring.register.DuplicateMemberException;
import spring.register.MemberRegisterService;
import spring.register.RegisterRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    private static ApplicationContext context = null;

    public static void main(String[] args) throws IOException {

        context=new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for(String bean : beanNames){
            System.out.println("bean = " + bean);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("명령어를 입력하세요 : ");
            String command = br.readLine();

            if(command.equalsIgnoreCase("exit")){
                System.out.println("시스템을 종료합니다");
                break;
            }

            if(command.startsWith("new")){
                processNewCommand(command.split(" "));
                continue;
            }
            else if(command.startsWith("change")){
                processChangeCommand(command.split(" "));
                continue;
            }
            else if(command.equalsIgnoreCase("list")){
                processListCommand();
                continue;
            }
            printHelp();

        }

    }

    private static void processListCommand() {

        MemberListPrint memberListPrint = context.getBean("memberListPrint",MemberListPrint.class);
        memberListPrint.printAll();
    }

    private static void printHelp() {
        System.out.println("================================================");
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인해보세요");
        System.out.println("명령어 사용법 : ");
        System.out.println("new 이메일 이름 비밀번호 비밀번호확인");
        System.out.println("change 이메일 현재비밀번호 새로운비밀번호");
        System.out.println("================================================");
    }

    private static void processChangeCommand(String[] s) {
        if(s.length!=4){
            printHelp();
            return;
        }
        ChangePasswordService pwdService = context.getBean("changePasswordService",ChangePasswordService.class);
        try{
            pwdService.changePassword(s[1],s[2],s[3]);
            System.out.println("암호를 변경하였습니다");
        }catch (MemberNotFoundException e){
            System.out.println("존재하지 않는 회원입니다");
        }catch (WrongPasswordException e){
            System.out.println("이메일과 암호가 일치하지 않습니다");
        }
    }

    private static void processNewCommand(String[] s) {
        if(s.length!=5){
            printHelp();
            return;
        }
        MemberRegisterService registerService=context.getBean("memberRegisterService", MemberRegisterService.class);
        RegisterRequest req = new RegisterRequest();
        req.setEmail(s[1]);
        req.setName(s[2]);
        req.setPassword(s[3]);
        req.setConfirmPassword(s[4]);

        if(!req.isPasswordEqualsToConfirmPassword()){
            System.out.println("비밀번호가 일치하지 않습니다");
            return;
        }
        try{
            registerService.register(req);
            System.out.println("등록이 완료되었습니다");
        }
        catch(DuplicateMemberException e){
            System.out.println("이미 존재하는 회원입니다");
        }
    }
}
