package imageboard.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import imageboard.bean.ImageboardDTO;
import imageboard.dao.ImageboardDAO;

public class ImageboardListAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		List<ImageboardDTO> list = imageboardDAO.getList(1, 3);
//		request.setAttribute("pg", pg);
		request.setAttribute("list", list);
		request.setAttribute("display", "/imageboard/imageboardList.jsp");
			
		return "/main/index.jsp";
	}

}
