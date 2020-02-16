package imageboard.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.CommandProcess;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import imageboard.bean.ImageboardDTO;
import imageboard.dao.ImageboardDAO;

public class ImageboardWriteAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String realFolder = request.getServletContext().getRealPath("/storage");
		System.out.println("실제 폴더 = " + realFolder);
		String folder = "/miniProject/storage/";

		//업로드
		MultipartRequest multi = new MultipartRequest(request, realFolder, 5*1024*1024, "UTF-8"
													, new DefaultFileRenamePolicy());
				
		//데이터
		String imageId = multi.getParameter("imageId");
		String imageName = multi.getParameter("imageName");
		int imagePrice = Integer.parseInt(multi.getParameter("imagePrice"));
		int imageQty = Integer.parseInt(multi.getParameter("imageQty"));
		String imageContent = multi.getParameter("imageContent");

		String originalFileName = multi.getOriginalFileName("imagefile");
		String fileName = multi.getFilesystemName("imagefile");

		String image1 = folder + fileName;
		
		File file = multi.getFile("imagefile");

		long fileSize = 0;
		if(file!=null) fileSize = file.length();
		
		ImageboardDAO imageboardDAO = ImageboardDAO.getInstance();
		ImageboardDTO imageboardDTO = new ImageboardDTO(imageId, imageName, imagePrice, imageQty, imageContent, image1);
		
		if(imageboardDAO.insert(imageboardDTO)) {
			request.setAttribute("display", "/imageboard/imageboardList.jsp?pg=1");
			
			List<ImageboardDTO> list = imageboardDAO.getList(1, 3);
			request.setAttribute("list", list);
			
		}else{
			request.setAttribute("display", "/imageboard/imageboardFail.jsp");
		}
		
		return "/main/index.jsp";
	}

}
