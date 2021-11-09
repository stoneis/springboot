package kr.pe.stoneis.board.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.pe.stoneis.board.common.FileUtils;
import kr.pe.stoneis.board.dto.StoneBoardDto;
import kr.pe.stoneis.board.dto.StoneBoardFileDto;
import kr.pe.stoneis.board.mapper.StoneBoardMapper;

@Service
@Transactional
public class StoneBoardServiceImpl implements StoneBoardService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StoneBoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<StoneBoardDto> selectBoardList() throws Exception {
		return this.boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(StoneBoardDto dto, MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception {
		this.boardMapper.insertBoard(dto);
		
		List<StoneBoardFileDto> list = fileUtils.parseFileInfo(dto.getNum(), 
				multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false) {
			this.boardMapper.insertBoardFileList(list);
		}
		/**
		if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
			String name;
			while(iterator.hasNext()) {
				name = iterator.next();
				log.debug("file tag name : " + name);
				List<MultipartFile>list = multipartHttpServletRequest.getFiles(name);
				for(MultipartFile multipartFile : list) {
					log.debug("start file info");
					log.debug("file name : " + multipartFile.getOriginalFilename());
					log.debug("file size " + multipartFile.getSize());
					log.debug("file content type : " + multipartFile.getContentType());
					log.debug("end file info");
				}
				
			}
		}
		*/
		
	}

	@Override
	public StoneBoardDto selectBoardDetail(int num) throws Exception {
		StoneBoardDto dto = this.boardMapper.selectBoardDetail(num);
		List<StoneBoardFileDto> fileList = this.boardMapper.selectBoardFileList(num);
		dto.setFileList(fileList);
		this.boardMapper.updateHitCount(num);
		return dto;
	}

	@Override
	public void updateBoard(StoneBoardDto dto) throws Exception {
		this.boardMapper.updateBoard(dto);
	}

	@Override
	public void deleteBoard(int num) throws Exception {
		this.boardMapper.deleteBoard(num);
	}
	
	@Override
	public StoneBoardFileDto selectBoardFile(int fileNum, int num) throws Exception {
		return this.boardMapper.selectBoardFile(fileNum, num);
	}
	
}
