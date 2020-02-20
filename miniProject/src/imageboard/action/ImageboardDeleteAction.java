package imageboard.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageboardDAO;

public class ImageboardDeleteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//데이터
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("seqs", new ArrayList<String>());
		Stream.of(request.getParameter("seqs").split(",")).forEach(seq -> map.get("seqs").add(seq));;
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		if(imageboardDAO.deleteMap(map)) {
			request.setAttribute("display", "/imageboard/imageboardList.jsp?pg=1");
			
			
			ImageboardPaging imageboardPaging = new ImageboardPaging();
			int totalA = imageboardDAO.getBoardTotalA();

			int pg = 1;
			int articlesPerPage = 3;
			int endNum = pg * articlesPerPage;
			int startNum = endNum - articlesPerPage + 1;
			
			List<ImageboardDTO> list = imageboardDAO.getList(startNum, endNum);
			request.setAttribute("list", list);
			
			imageboardPaging.setCurrentPage(pg);
			imageboardPaging.setPageBlock(3);
			imageboardPaging.setPageSize(articlesPerPage);
			imageboardPaging.setTotalA(totalA);
			imageboardPaging.makePagingHTML();
				
			request.setAttribute("imageboardPaging", imageboardPaging.getPagingHTML().toString());
			request.setAttribute("pg", pg);
			
		}else{
			request.setAttribute("display", "/imageboard/imageboardFail.jsp");
		}
		
		return "/main/index.jsp";
	}

}
