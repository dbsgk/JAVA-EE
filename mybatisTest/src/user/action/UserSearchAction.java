package user.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import user.bean.UserDTO;
import user.dao.UserDAO;

public class UserSearchAction implements UserAction{

	@Override
	public void execute() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1. 이름 검색");
		System.out.println("2. 아이디 검색");
		System.out.print("번호 입력: ");
		int choice = scan.nextInt();
		UserDAO userDAO = UserDAO.getInstance();
		Map<String, String> map = new HashMap<String, String>();
		if(choice==1) {
			System.out.print("검색할 이름을 입력하세요 : ");
			String name = "%"+scan.next()+"%";
			map.put("searchMethod", "name");
			map.put("input", name);
			//userDAO.userSearch(name, "name");
		}else if(choice==2){
			System.out.print("검색할 아이디를 입력하세요 : ");
			String id = "%"+scan.next()+"%";
			map.put("searchMethod", "id");
			map.put("input", id);
			//userDAO.userSearch(id, "id");
		}
		List<UserDTO> list = userDAO.userSearch(map);
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName()+"\t"+userDTO.getId()+"\t"+userDTO.getPwd());
		}//for
		
	}

}
