package user.action;

import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserInsertAction implements UserAction {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		//데이터
		System.out.print("이름 입력: ");
		String name = scan.next();
		System.out.print("아이디 입력: ");
		String id = scan.next();
		System.out.print("비밀번호 입력: ");
		String pwd = scan.next();
		
		UserDTO  userDTO = new UserDTO(name, id, pwd);
				
		//DB
		UserDAO userDAO = UserDAO.getInstance();
		userDAO.userWrite(userDTO);
		//응답
		System.out.println("데이터 저장 완료");
	}

}
