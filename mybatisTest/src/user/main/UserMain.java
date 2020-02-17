package user.main;

import java.util.Scanner;

import user.action.UserAction;
import user.action.UserDeleteAction;
import user.action.UserInsertAction;
import user.action.UserSearchAction;
import user.action.UserSelectAction;
import user.action.UserUpdateAction;

public class UserMain {
	public static void main(String[] args) {
		UserMain um = new UserMain();
		um.menu();
		System.out.println("프로그램을 종료합니다.");
	}
	public void menu() {
		Scanner scan = new Scanner(System.in);
		UserAction userAction = null;
		while(true) {
			System.out.println("***********");
			System.out.println("1. 입력");
			System.out.println("2. 출력");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
			System.out.println("5. 검색");
			System.out.println("6. 끝");
			System.out.println("***********");
			System.out.print("번호를 입력하세요:");
			int choice = scan.nextInt();
			if(choice==6) break;
			else if(choice==1) userAction = new UserInsertAction();//입력
			else if(choice==2) userAction = new UserSelectAction();//전체출력
			else if(choice==3) userAction = new UserUpdateAction();//수정
			else if(choice==4) userAction = new UserDeleteAction();//삭제
			else if(choice==5) userAction = new UserSearchAction();//검색
			userAction.execute();
		}
	}
}
