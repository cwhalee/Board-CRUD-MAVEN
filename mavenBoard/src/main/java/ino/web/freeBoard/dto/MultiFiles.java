package ino.web.freeBoard.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Alias("multiFiles")
public class MultiFiles {

	private int filenum;
	private int num;
	private String filename;
	private String nfilename;
	private String uploaddate;
	private MultipartFile[] file1;
	
	public int getFilenum() {
		return filenum;
	}
	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getNfilename() {
		return nfilename;
	}
	public void setNfilename(String nfilename) {
		this.nfilename = nfilename;
	}
	public String getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(String uploaddate) {
		this.uploaddate = uploaddate;
	}
	public MultipartFile[] getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile[] file1) {
		this.file1 = file1;
	}

	@Override
	public String toString() {
		return "MultiFiles [num=" + num + ", nfilename=" + nfilename + ", filename=" + filename + ", uploaddate=" + uploaddate
				+ ", filenum=" + filenum + ", file1=" + file1 + "]";
	}
	
}
