package imageboard.action;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageboardDAO;

public class ImageboardListAction implements CommandProcess{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int pg = 1;
		if(request.getParameter("pg")!= null) 
			pg = Integer.parseInt(request.getParameter("pg"));
		
		request.setAttribute("display", "/imageboard/imageboardList.jsp?pg="+pg);
		
		int articlesPerPage = 3;
		int endNum = pg * articlesPerPage;
		int startNum = endNum - articlesPerPage + 1;
		
		// 쿠키 설정 - 목록으로 돌아왔으므로 hit_on = true 
		Cookie cookie = new Cookie("img_hit_on", "true");
		cookie.setMaxAge(60*30); // 초 단위
		response.addCookie(cookie); // 클라이언트에게 보내기
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		List<ImageboardDTO> list = imageboardDAO.getList(startNum, endNum);
		request.setAttribute("list", list);
		
		ImageboardPaging imageboardPaging = new ImageboardPaging();
		int totalA = imageboardDAO.getBoardTotalA();

		imageboardPaging.setCurrentPage(pg);
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(articlesPerPage);
		imageboardPaging.setTotalA(totalA);
		imageboardPaging.makePagingHTML();
			
		request.setAttribute("imageboardPaging", imageboardPaging.getPagingHTML().toString());
		request.setAttribute("pg", pg);
		
		request.setAttribute("display", "/imageboard/imageboardList.jsp?pg=1");
		
		return "/main/index.jsp";
	}

}
