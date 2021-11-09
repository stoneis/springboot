package kr.pe.stoneis.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kr.pe.stoneis.board.dto.StoneBoardDto;
import kr.pe.stoneis.board.dto.StoneBoardFileDto;
import kr.pe.stoneis.board.service.StoneBoardService;

@Controller
public class StoneRestBoardController {

	@Autowired
	private StoneBoardService boardService;
	
	@RequestMapping(value="/stone/board", method=RequestMethod.GET)
	public ModelAndView boardList() throws Exception {
		ModelAndView mv = new ModelAndView("stone/board/restBoardList");
		List<StoneBoardDto> list = this.boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping(value="/stone/board/write", method=RequestMethod.GET)
	public String boardWrite() throws Exception {
		return "stone/board/restBoardWrite";
	}
	
	@RequestMapping(value="/stone/board/write", method=RequestMethod.POST)
	public String insertBoard(StoneBoardDto dto, MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception {
		this.boardService.insertBoard(dto, multipartHttpServletRequest);
		return "redirect:/stone/board";
	}
	
	@RequestMapping(value="/stone/board/{num}", method=RequestMethod.GET)
	public ModelAndView boardDetail(@PathVariable("num") int num) throws Exception {
		ModelAndView mv = new ModelAndView("stone/board/restBoardDetail");
		mv.addObject("boardDto", this.boardService.selectBoardDetail(num));

		return mv;
	}
	
	@RequestMapping(value="/stone/board/{num}", method=RequestMethod.PUT)
	public String updateBoard(StoneBoardDto dto) throws Exception {
		this.boardService.updateBoard(dto);
		return "redirect:/stone/board";
	}
	
	@RequestMapping(value="/stone/board/{num}", method=RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("num") int num) throws Exception {
		this.boardService.deleteBoard(num);
		return "redirect:/stone/board";
	}
	
	@RequestMapping(value="/stone/board/file", method=RequestMethod.GET)
	public void downloadBoardFile(@RequestParam int fileNum, @RequestParam int num,
			HttpServletResponse response) throws Exception {
		StoneBoardFileDto fileDto = this.boardService.selectBoardFile(fileNum, num);
		if(ObjectUtils.isEmpty(fileDto) == false) {
			String fileName = fileDto.getOriginalFileName();
			byte[] files = FileUtils.readFileToByteArray(new File(fileDto.getFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" +
				URLEncoder.encode(fileName, "UTF-8") +"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
		
	}
	
}
