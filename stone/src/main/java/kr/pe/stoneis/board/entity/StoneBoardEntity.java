package kr.pe.stoneis.board.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name="STONE_JPA_BOARD")
@NoArgsConstructor
public class StoneBoardEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int num;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String contents;
	
	@Column(nullable=false)
	private int hitCnt;
	
	@Column(nullable=false)
	private LocalDateTime regDate = LocalDateTime.now();
	
	@Column(nullable=false)
	private String regName;
	
	private LocalDateTime updDate;
	
	private String updName;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="num")
	private Collection<StoneBoardFileEntity> fileList;
	
	public Collection<StoneBoardFileEntity> getFileList() {
		return fileList;
	}
	public void setFileList(Collection<StoneBoardFileEntity> fileList) {
		this.fileList = fileList;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(int hitCnt) {
		this.hitCnt = hitCnt;
	}
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
	
}
