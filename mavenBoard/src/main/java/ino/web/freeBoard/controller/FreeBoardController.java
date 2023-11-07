package ino.web.freeBoard.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.dto.FreeBoardPaging;
import ino.web.freeBoard.dto.MultiFiles;
import ino.web.freeBoard.service.FreeBoardService;


@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	
//	@RequestMapping("/main.ino") 
//	public ModelAndView main(HttpServletRequest request){
//		ModelAndView mav = new ModelAndView();
//		System.out.println("확인");
//		List list = freeBoardService.freeBoardList();
////		List list = freeBoardService.freeBoardList2();
//	
//		mav.setViewName("boardMain");
//		mav.addObject("freeBoardList",list);
//		return mav;
//	}
	
//	@RequestMapping(value ="/main.ino", method = RequestMethod.GET)
//	public ModelAndView getBoardList(Model model
//			, @RequestParam(required = false, defaultValue = "1") int page
//			, @RequestParam(required = false, defaultValue = "1") int range
//			) throws Exception {
//		ModelAndView mav = new ModelAndView();
//		//전체 게시글 개수
//		int listCnt = freeBoardService.getBoardListCnt();
//		System.out.println(listCnt);
//		System.out.println(page);
//		System.out.println(range);
//	    //Pagination 객체생성
//		FreeBoardPaging pagination = new FreeBoardPaging();
//		pagination.pageInfo(page, range, listCnt);
//		
//		mav.setViewName("boardMain");
//		mav.addObject("pagination", pagination);
//		mav.addObject("freeBoardList", freeBoardService.getBoardList(pagination));
//		return mav;
//    }

	
	@RequestMapping(value="/main.ino", method= RequestMethod.GET)
	public ModelAndView boardList(HttpServletRequest request, ModelAndView mav, 
			@RequestParam(value = "currPageNo", required = false, defaultValue = "1") String tmpcurrPageNo, 
   	 		@RequestParam(value = "range", required = false, defaultValue = "1") String tmprange) {
		
		int currPageNo = 0;
		int range = 0;
		List<FreeBoardDto> boardList = null;
		try {			
			currPageNo = Integer.parseInt(tmpcurrPageNo);
			range = Integer.parseInt(tmprange);
			
		} catch(NumberFormatException e) {
			currPageNo = 1;
			range = 1;			
		}
		
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		if(searchType == null || (!"content".equals(searchType) && !"title".equals(searchType)) ) {
	       searchType = "";
	    }
	      
	    if(keyword == null || "".equals(keyword) || keyword.trim().isEmpty() ) {
	       keyword = "";
	    }	   
		
	    Map<String, String> paraMap = new HashMap<>();
		paraMap.put("searchType", searchType);
		paraMap.put("keyword", keyword);		
	    
		FreeBoardPaging pg = new FreeBoardPaging();
	    pg.setSearchType(searchType);
	    pg.setKeyword(keyword);
	    
	    int totalCnt = freeBoardService.getBoardListCnt(pg);	   
	    pg.pageInfo(currPageNo, range, totalCnt);
	    boardList = freeBoardService.getBoardList(pg);
		
		if(!"".equals(searchType) && !"".equals(keyword)) {
	        mav.addObject("paraMap", paraMap);
	    }
	
		mav.addObject("pagination", pg);
		mav.addObject("freeBoardList", boardList);
		mav.setViewName("boardMain");
		return mav;			
	}

	
	@RequestMapping("/freeBoardInsert.ino")
	public String freeBoardInsert(){
		return "freeBoardInsert";
	}
	
	@RequestMapping("/freeBoardInsertPro.ino")
	public String freeBoardInsertPro(HttpServletRequest request, FreeBoardDto dto){
		freeBoardService.freeBoardInsertPro(dto);
		int key = dto.getNum();
		
		return "redirect:/freeBoardDetail.ino?num="+key;
	}
	
	@RequestMapping("/freeBoardDetail.ino")
	public ModelAndView freeBoardDetail(HttpServletRequest request){
		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDto dto = freeBoardService.getDetailByNum(num);
		
		return new ModelAndView("freeBoardDetail", "freeBoardDto", dto);
	}
	
	@RequestMapping("/freeBoardUpdatePro.ino")
	public String freeBoardUpdatePro(HttpServletRequest request, FreeBoardDto dto){
		int num = Integer.parseInt(request.getParameter("num"));
		String content = request.getParameter("content");
		dto.setNum(num);
		dto.setContent(content);
		freeBoardService.getUpdateByNum(dto);
		
		return "redirect:/main.ino";
	}
	
	@RequestMapping("/freeBoardDeletePro.ino")
	public String freeBoardDeletePro(HttpServletRequest request){	
		int num = Integer.parseInt(request.getParameter("num"));
		freeBoardService.getDeleteByNum(num);
		
		return "redirect:/main.ino";
	}
	
	
//	아래는 FILE UPLOAD
	@RequestMapping(value="/upload.ino", method= RequestMethod.GET)
	public ModelAndView fileList(HttpServletRequest request, ModelAndView mav, 
			@RequestParam(value = "currPageNo", required = false, defaultValue = "1") String tmpcurrPageNo, 
   	 		@RequestParam(value = "range", required = false, defaultValue = "1") String tmprange) {
		
		int currPageNo = 0;
		int range = 0;
		List<FreeBoardDto> fileList = null;
		try {			
			currPageNo = Integer.parseInt(tmpcurrPageNo);
			range = Integer.parseInt(tmprange);
			
		} catch(NumberFormatException e) {
			currPageNo = 1;
			range = 1;			
		}
		
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		if(searchType == null || (!"content".equals(searchType) && !"title".equals(searchType)) ) {
	       searchType = "";
	    }
	    if(keyword == null || "".equals(keyword) || keyword.trim().isEmpty() ) {
	       keyword = "";
	    }	   
		
	    Map<String, String> paraMap = new HashMap<>();
		paraMap.put("searchType", searchType);
		paraMap.put("keyword", keyword);		
	    
		FreeBoardPaging pg = new FreeBoardPaging();
	    pg.setSearchType(searchType);
	    pg.setKeyword(keyword);
	    
	    int totalCnt = freeBoardService.getFileListCnt(pg);	   
	    pg.pageInfo(currPageNo, range, totalCnt);
	    fileList = freeBoardService.getFileList(pg);
		
		if(!"".equals(searchType) && !"".equals(keyword)) {
	        mav.addObject("paraMap", paraMap);
	    }
	
		mav.addObject("pagination", pg);
		mav.addObject("fileBoardList", fileList);
		mav.setViewName("uploadMain");
		return mav;			
	}
	
	@RequestMapping("/fileBoardInsert.ino")
	public String fileBoardInsert(){
		return "fileBoardInsert";
	}
	@RequestMapping(value = "/fileBoardInsertPro.ino",method=RequestMethod.POST)
	public String requestupload2(MultipartHttpServletRequest mtfRequest, FreeBoardDto dto) {
		freeBoardService.fileBoardInsertPro(dto);
		int key = dto.getNum();
		
		String originFileName ="";
		String safeFile ="";
	    String path = "C:\\Users\\lime\\lee\\eGovFrameDev-3.7.0-64bit\\workspace\\mavenBoard\\WebContent\\WEB-INF\\uploadfile\\";
	    List<MultipartFile> fileList = mtfRequest.getFiles("file");
	    
    	for (MultipartFile mf : fileList) {
	        originFileName = mf.getOriginalFilename(); // 원본 파일 명
	        if(originFileName == ""){
			    return "redirect:/fileBoardDetail.ino?num="+key;
		    }else{
	        String newId = UUID.randomUUID().toString(); //파일명 안겹치기
	        String saveName = newId+ "." + originFileName;
	        safeFile = saveName;
	        
	        try {
	            mf.transferTo(new File(path,safeFile));
	        } catch (IllegalStateException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        MultiFiles mfs= new MultiFiles();
		    mfs.setNum(key);
		    mfs.setFilename(originFileName);
		    mfs.setNfilename(safeFile);
		    freeBoardService.filesInsertPro(mfs);
		    }
	    }
    	return "redirect:/fileBoardDetail.ino?num="+key;
	}
	
	@RequestMapping("/fileBoardDetail.ino")
	public ModelAndView fileBoardDetail(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		int num = Integer.parseInt(request.getParameter("num"));
		FreeBoardDto dto = freeBoardService.getFileBoardByNum(num);
		List<MultiFiles> multiFiles = freeBoardService.getFileByNum(num);
		mav.setViewName("fileBoardDetail");
		mav.addObject("fileBoardDto", dto);
		mav.addObject("multiFiles",multiFiles);
		return mav;
	}
	
	@RequestMapping("/filedownload.ino")
    public void download(HttpServletRequest request, HttpServletResponse response) {
		String path = "C:\\Users\\lime\\lee\\eGovFrameDev-3.7.0-64bit\\workspace\\mavenBoard\\WebContent\\WEB-INF\\uploadfile\\";
		String nfilename = request.getParameter("nfilename");
		String filename = request.getParameter("filename");
        
        try {
            String browser = request.getHeader("User-Agent");
            //파일 인코딩 
            if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
                filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+","%20");
            } else {
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException");
        }
        String downFilename = path+nfilename;
        File file1 = new File(downFilename);
        if (!file1.exists()) {
            return ;
        }
//      파일명 지정        
        response.setContentType("application/octer-stream");
        response.setHeader("Content-Transfer-Encoding", "binary;");
//      데이터형식/성향설정 (attachment: 첨부파일)
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(downFilename);
 
            int ncount = 0;
            byte[] bytes = new byte[512];
 
            while ((ncount = fis.read(bytes)) != -1 ) {
                os.write(bytes, 0, ncount);
            }
            fis.close();
            os.close();
        } catch (Exception e) {
            System.out.println("FileNotFoundException : " + e);
        }
    }
	
	@RequestMapping(value="/fileBoardUpdatePro.ino", method=RequestMethod.POST)
	public String fileBoardUpdatePro(MultipartHttpServletRequest mtfRequest, FreeBoardDto dto){
		freeBoardService.getUpdateFileboardByNum(dto);
		int key = dto.getNum();
		
		String path = "C:\\Users\\lime\\lee\\eGovFrameDev-3.7.0-64bit\\workspace\\mavenBoard\\WebContent\\WEB-INF\\uploadfile\\";
		String originFileName ="";
		String safeFile ="";
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		
		for (MultipartFile mf : fileList) {
		    originFileName = mf.getOriginalFilename();
		    if(originFileName == ""){
			    return "redirect:/fileBoardDetail.ino?num="+key;
		    }else{
		    String newId = UUID.randomUUID().toString();
		    String saveName = newId+ "." + originFileName;
		    safeFile = saveName;
		    
		    try {
		        mf.transferTo(new File(path,safeFile));
		    } catch (IllegalStateException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    MultiFiles mfs= new MultiFiles();
		    mfs.setNum(key);
		    mfs.setFilename(originFileName);
		    mfs.setNfilename(safeFile);
		    freeBoardService.filesInsertPro(mfs);
		   }
		}
		return "redirect:/fileBoardDetail.ino?num="+key;
	}
	
	@RequestMapping("/fileBoardDeletePro.ino")
	public String fileBoardDeletePro(HttpServletRequest request){	
		int num = Integer.parseInt(request.getParameter("num"));
		List<MultiFiles> multiFiles = freeBoardService.deleteUploadFiles(num);
		
		for(int i=0; i < multiFiles.size();i++){
			String path = "C:\\Users\\lime\\lee\\eGovFrameDev-3.7.0-64bit\\workspace\\mavenBoard\\WebContent\\WEB-INF\\uploadfile\\";
			String nfilename = multiFiles.get(i).getNfilename();
			File file = new File(path + nfilename);
			if( file.exists() ){
				file.delete();
			}else{
				System.out.println("파일삭제 실패");
			}
		}
		freeBoardService.deleteFileByNum(num);
		return "redirect:/upload.ino";
	}
	
	@RequestMapping("/fileremove.ino")
    public String fileRemove(HttpServletRequest request, HttpServletResponse response) {
		String path = "C:\\Users\\lime\\lee\\eGovFrameDev-3.7.0-64bit\\workspace\\mavenBoard\\WebContent\\WEB-INF\\uploadfile\\";
		String nfilename = request.getParameter("nfilename");
		int num = Integer.parseInt(request.getParameter("num"));
		File file = new File(path + nfilename);
		
		if( file.exists() ){
    		if(file.delete()){
    			freeBoardService.deleteFileByName(nfilename);
    			System.out.println("파일삭제 성공");
    		}else{
    			System.out.println("파일삭제 실패");
    		}
    	}else{
    		System.out.println("파일 없음");
    	}
		return "redirect:/fileBoardDetail.ino?num="+num;	
	}
}
