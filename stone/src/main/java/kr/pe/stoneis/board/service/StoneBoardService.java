package kr.pe.stoneis.board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.pe.stoneis.board.dto.StoneBoardDto;
import kr.pe.stoneis.board.dto.StoneBoardFileDto;

public interface StoneBoardService {

	List<StoneBoardDto> selectBoardList() throws Exception;
	 
	void insertBoard(StoneBoardDto dto, MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception;
	
	StoneBoardDto selectBoardDetail(int num) throws Exception;
	
	void updateBoard(StoneBoardDto dto) throws Exception;
	
	void deleteBoard(int num) throws Exception;
	
	StoneBoardFileDto selectBoardFile(int fileNum, int num) throws Exception;

}
