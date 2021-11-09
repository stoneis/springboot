package kr.pe.stoneis.board.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.pe.stoneis.board.common.FileUtils;
import kr.pe.stoneis.board.entity.StoneBoardEntity;
import kr.pe.stoneis.board.entity.StoneBoardFileEntity;
import kr.pe.stoneis.board.repository.StoneJpaBoardRepository;

@Service
public class StoneJpaBoardServiceImpl implements StoneJpaBoardService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	StoneJpaBoardRepository jpaBoardRepository;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<StoneBoardEntity> selectBoardList() throws Exception {
		return this.jpaBoardRepository.findAllByOrderByNumDesc();
	}

	@Override
	public void saveBoard(StoneBoardEntity board, MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception {
		board.setRegName("stone");
		List<StoneBoardFileEntity> list = fileUtils.parseFileInfo(multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false) {
			board.setFileList(list);
		}
		this.jpaBoardRepository.save(board);
	}

	@Override
	public StoneBoardEntity selectBoardDetail(int num) throws Exception {
		Optional<StoneBoardEntity> optional = this.jpaBoardRepository.findById(num);
		if(optional.isPresent()) {
			StoneBoardEntity board = optional.get();
			board.setHitCnt(board.getHitCnt() + 1);
			this.jpaBoardRepository.save(board);
			
			return board;
		} else {
			throw new NullPointerException();
		}
	}

	@Override
	public void deleteBoard(int num) throws Exception {
		this.jpaBoardRepository.deleteById(num);
	}
	
	@Override
	public StoneBoardFileEntity selectBoardFile(int fileNum, int num) throws Exception {
		return this.jpaBoardRepository.findBoardFile(fileNum, num);
	}
	
}
