package user.action;

import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserUpdateAction implements UserAction {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("수정할 아이디 입력 : ");
		String id = scan.next();
		
		UserDAO userDAO = UserDAO.getInstance();
		UserDTO userDTO = userDAO.selectOne(id);
		if(userDTO==null) {
			System.out.println("찾고자 하는 아이디가 없습니다.");return;
		}
		
		System.out.println(userDTO.getName()+"\t"+userDTO.getId()+"\t"+userDTO.getPwd());
		System.out.print("수정할 이름 입력 : ");
		String name = scan.next();
		System.out.print("수정할 비밀번호 입력 : ");
		String pwd = scan.next();
		
		userDTO.setName(name);
		userDTO.setPwd(pwd);
		userDAO.userUpdate(userDTO);
		
		System.out.println("데이터 수정 완료");
		
	}//execute

}
