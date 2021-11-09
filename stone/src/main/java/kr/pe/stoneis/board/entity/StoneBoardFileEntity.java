package kr.pe.stoneis.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name="STONE_JPA_FILE")
@NoArgsConstructor
public class StoneBoardFileEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int fileNum;
	
	@Column(nullable=false)
	private String originalFileName;
	
	@Column(nullable=false)
	private String filePath;
	
	@Column(nullable=false)
	private long fileSize;
	
	@Column(nullable=false)
	private LocalDateTime regDate = LocalDateTime.now();
	
	@Column(nullable=false)
	private String regName;
	
	private LocalDateTime updDate;
	
	public LocalDateTime getRegDate() {
		return regDate;
	}
	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public LocalDateTime getUpdDate() {
		return updDate;
	}
	public void setUpdDate(LocalDateTime updDate) {
		this.updDate = updDate;
	}
	public String getUpdName() {
		return updName;
	}
	public void setUpdName(String updName) {
		this.updName = updName;
	}
	private String updName;

	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

}
