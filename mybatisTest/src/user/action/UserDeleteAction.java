package user.action;

import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserDeleteAction implements UserAction {

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 아이디 입력 : ");
		String id = scan.next();
		
		UserDAO userDAO = UserDAO.getInstance();
		UserDTO userDTO = userDAO.selectOne(id);
		if(userDTO==null) {
			System.out.println("찾고자 하는 아이디가 없습니다.");return;
		}
		System.out.println(userDTO.getName()+"\t"+userDTO.getId()+"\t"+userDTO.getPwd());
		userDAO.userDelete(id);
		
		System.out.println("데이터 삭제 완료");
		
	}//execute

}
