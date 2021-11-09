package kr.pe.stoneis.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.stoneis.board.dto.StoneBoardDto;
import kr.pe.stoneis.board.service.StoneBoardService;

@RestController
public class StoneRestBoardAPIController {

	@Autowired
	private StoneBoardService boardService;
	
	@RequestMapping(value="/stone/api/board", method=RequestMethod.GET)
	public List<StoneBoardDto> boardList() throws Exception {
		return this.boardService.selectBoardList();
	}
	
	@RequestMapping(value="/stone/api/board/write", method=RequestMethod.POST)
	public void insertBoard(@RequestBody StoneBoardDto dto) throws Exception {
		this.boardService.insertBoard(dto, null);
	}
	
	@RequestMapping(value="/stone/api/board/{num}", method=RequestMethod.GET)
	public StoneBoardDto boardDetail(@PathVariable("num") int num) throws Exception {
		return this.boardService.selectBoardDetail(num);
	}
	
	@RequestMapping(value="/stone/api/board/{num}", method=RequestMethod.PUT)
	public String updateBoard(@RequestBody StoneBoardDto dto) throws Exception {
		this.boardService.updateBoard(dto);
		return "redirect:/stone/api/board";
	}
	
	@RequestMapping(value="/stone/api/board/{num}", method=RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("num") int num) throws Exception {
		this.boardService.deleteBoard(num);
		return "redirect:/stone/api/board";
	}
		
}
