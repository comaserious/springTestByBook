package spring;

import spring.assembler.Assembler;
import spring.memberData.WrongIdPasswordException;
import spring.password.ChangePasswordService;
import spring.password.MemberNotFoundException;
import spring.register.DuplicateMemberException;
import spring.register.MemberRegisterService;
import spring.register.RegisterRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static Assembler assembler =new Assembler();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("명령어를 입력하세요 : ");
            String command = br.readLine();

            if(command.equalsIgnoreCase("exit")){
                System.out.println("종료합니다");
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
            printHelp();
        }
    }

    private static void processChangeCommand(String[] s) {
        if(s.length!=4){
            printHelp();
            return;
        }
        ChangePasswordService changePasswordService = assembler.getChangePasswordService();

        try{
        changePasswordService.changePassword(s[1],s[2],s[3]);
        System.out.println("암호를 변경하였습니다");}
        catch(MemberNotFoundException e){
            System.out.println("존재하지 않는 이메일입니다");
        }
        catch (WrongIdPasswordException e){
            System.out.println("이메일과 암호가 일치하지 않습니다");
        }

    }

    private static void processNewCommand(String[] s) {

        if(s.length!=5){
            printHelp();
            return;
        }
        MemberRegisterService memberRegisterService = assembler.getMemberRegisterService();
        RegisterRequest req = new RegisterRequest();
        req.setEmail(s[1]);
        req.setName(s[2]);
        req.setPassword(s[3]);
        req.setConfirmPassword(s[4]);

        if(!req.isPasswordEqualToConfirmPassword()){
            System.out.println("비밀번호가 일치하지 않습니다 다시 확인하세요");
            return;
        }
        try{
        memberRegisterService.register(req);
        System.out.println("등록이 완료되었습니다");}
        catch(DuplicateMemberException e){
            System.out.println("이미 존재하는 회원입니다");
        }


    }

    private static void printHelp() {
        System.out.println("================================================");
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
        System.out.println("명령어 사용법 : ");
        System.out.println("new 이메일 이름 비밀번호 비밀번호확인");
        System.out.println("change 이메일 현재비밀번호 변경 비밀번호");
        System.out.println("=================================================");
    }
}
