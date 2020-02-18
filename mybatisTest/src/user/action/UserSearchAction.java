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
		
		String input = null;
		//String columnName = null;
		
		Map<String, String> map = new HashMap<String, String>();
		
		if(choice==1) {
			System.out.print("검색할 이름을 입력하세요 : ");
			input = "%"+scan.next()+"%";
			//columnName="name";
			map.put("searchMethod", "name");
			map.put("input", input);
		}else if(choice==2){
			System.out.print("검색할 아이디를 입력하세요 : ");
			input = "%"+scan.next()+"%";
			//columnName="id";
			map.put("searchMethod", "id");
			map.put("input", input);
		}
		//map.put("columnName",columnName);
		//map.put("input",input); 쌤은 두 번 안쓰고 이렇게 한 번에
		
		UserDAO userDAO = UserDAO.getInstance();
		List<UserDTO> list = userDAO.userSearch(map);
		
		for(UserDTO userDTO : list) {
			System.out.println(userDTO.getName()+"\t"+userDTO.getId()+"\t"+userDTO.getPwd());
		}//for
		if(list.size()==0)
		System.out.println("검색 결과가 없습니다.");
	}

}
