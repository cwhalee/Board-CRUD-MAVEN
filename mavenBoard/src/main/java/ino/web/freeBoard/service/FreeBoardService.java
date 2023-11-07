package ino.web.freeBoard.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.dto.FreeBoardPaging;
import ino.web.freeBoard.dto.MultiFiles;



@Service
public class FreeBoardService {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List freeBoardList(){
		return sqlSessionTemplate.selectList("freeBoardGetList");
	}
	
	public List freeBoardList2(){
		return sqlSessionTemplate.selectList("freeBoardGetList2");
	}
	
	public int getBoardListCnt(FreeBoardPaging pg){
		return sqlSessionTemplate.selectOne("getBoardListCnt",pg);
	}
	
	public List getBoardList(FreeBoardPaging pg){
		return sqlSessionTemplate.selectList("getBoardList",pg);
	}
	
	public void freeBoardInsertPro(FreeBoardDto dto){
		sqlSessionTemplate.insert("freeBoardInsertPro",dto);
	}	
	
	public FreeBoardDto getDetailByNum(int num){
		return sqlSessionTemplate.selectOne("freeBoardDetailByNum", num);
	}
	
	public void getUpdateByNum(FreeBoardDto dto){
		sqlSessionTemplate.delete("freeBoardUpdatePro", dto);
	}
	
	public void getDeleteByNum(int num){
		sqlSessionTemplate.delete("freeBoardDeletePro", num);
	}
	
	//파일업로드 메소드
	public int getFileListCnt(FreeBoardPaging pg){
		return sqlSessionTemplate.selectOne("getFileListCnt",pg);
	}
	
	public List getFileList(FreeBoardPaging pg){
		return sqlSessionTemplate.selectList("getFileList",pg);
	}
	
	public void fileBoardInsertPro(FreeBoardDto dto){
		sqlSessionTemplate.insert("fileBoardInsertPro",dto);
	}
	
	public void filesInsertPro(MultiFiles mfs){
		sqlSessionTemplate.insert("filesInsertPro",mfs);
	}
	
	public FreeBoardDto getFileBoardByNum(int num){
		return sqlSessionTemplate.selectOne("getFileBoardByNum", num);
	}
	
	public List getFileByNum(int num){
		return sqlSessionTemplate.selectList("getFileByNum", num);
	}
	
	public void getUpdateFileboardByNum(FreeBoardDto dto){
		sqlSessionTemplate.update("getUpdateFileboardByNum", dto);
	}
	
	public void deleteFileByNum(int num){
		sqlSessionTemplate.delete("uploadFileDel", num);
		sqlSessionTemplate.delete("deleteFileByNum", num);
	}
	
	public List deleteUploadFiles(int num){
		return sqlSessionTemplate.selectList("deleteUploadFiles", num);
	}
	
	public void deleteFileByName(String nfilename){
		sqlSessionTemplate.delete("deleteFileByName", nfilename);
	}
}
