package kr.pe.stoneis.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.pe.stoneis.board.entity.StoneBoardEntity;
import kr.pe.stoneis.board.entity.StoneBoardFileEntity;

public interface StoneJpaBoardService {

	List<StoneBoardEntity> selectBoardList() throws Exception;
	 
	void saveBoard(StoneBoardEntity board, MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception;
	
	StoneBoardEntity selectBoardDetail(int num) throws Exception;
	
	void deleteBoard(int num) throws Exception;
	
	StoneBoardFileEntity selectBoardFile(int fileNum, int num) throws Exception;

}
