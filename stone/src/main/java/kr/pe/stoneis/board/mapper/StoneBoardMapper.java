package kr.pe.stoneis.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.pe.stoneis.board.dto.StoneBoardDto;
import kr.pe.stoneis.board.dto.StoneBoardFileDto;

@Mapper
public interface StoneBoardMapper {

	List<StoneBoardDto> selectBoardList() throws Exception;
	
	void insertBoard(StoneBoardDto dto) throws Exception;
	
	StoneBoardDto selectBoardDetail(int num) throws Exception;
	
	void updateHitCount(int num) throws Exception;
	
	void updateBoard(StoneBoardDto dto) throws Exception;

	void deleteBoard(int num) throws Exception;
	
	void insertBoardFileList(List<StoneBoardFileDto> list) throws Exception;
	
	List<StoneBoardFileDto> selectBoardFileList(int num) throws Exception;

	StoneBoardFileDto selectBoardFile(@Param("fileNum") int fileNum, 
			@Param("num") int num) throws Exception;

}
