package kr.pe.stoneis.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.pe.stoneis.board.dto.StoneBoardDto;
import kr.pe.stoneis.board.dto.StoneBoardFileDto;
import kr.pe.stoneis.board.service.StoneBoardService;


@Api(description="게시판 일반")
@Controller
public class StoneBoardController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StoneBoardService boardService;
	
	@ApiOperation(value="게시글 목록 조회")
	@RequestMapping("/stone/boardList.do")
	public ModelAndView boardList() throws Exception {
		log.debug("boardList");
		ModelAndView mv = new ModelAndView("stone/board/boardList");
		
		List<StoneBoardDto> list = this.boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/stone/boardWrite.do")
	public String boardWrite() throws Exception {
		return "stone/board/boardWrite";
	}
	
	@RequestMapping("/stone/insertBoard.do")
	public String insertBoard(StoneBoardDto dto, MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception {
		this.boardService.insertBoard(dto, multipartHttpServletRequest);
		return "redirect:/stone/boardList.do";
	}
	
	@RequestMapping("/stone/boardDetail.do")
	public ModelAndView boardDetail(@RequestParam int num) throws Exception {
		ModelAndView mv = new ModelAndView("stone/board/boardDetail");
		mv.addObject("boardDto", this.boardService.selectBoardDetail(num));

		return mv;
	}
	
	@RequestMapping("/stone/updateBoard.do")
	public String updateBoard(StoneBoardDto dto) throws Exception {
		this.boardService.updateBoard(dto);
		return "redirect:/stone/boardList.do";
	}
	
	@RequestMapping("/stone/deleteBoard.do")
	public String deleteBoard(@RequestParam int num) throws Exception {
		this.boardService.deleteBoard(num);
		return "redirect:/stone/boardList.do";
	}
	
	@RequestMapping("/stone/downloadBoardFile.do")
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
