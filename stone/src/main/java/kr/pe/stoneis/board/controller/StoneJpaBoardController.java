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

import kr.pe.stoneis.board.entity.StoneBoardEntity;
import kr.pe.stoneis.board.entity.StoneBoardFileEntity;
import kr.pe.stoneis.board.service.StoneJpaBoardService;

@Controller
public class StoneJpaBoardController {

	@Autowired
	private StoneJpaBoardService jpaBoardService;
	
	@RequestMapping(value="/stone/jpa/board", method=RequestMethod.GET)
	public ModelAndView boardList() throws Exception {
		ModelAndView mv = new ModelAndView("stone/board/jpaBoardList");
		List<StoneBoardEntity> list = this.jpaBoardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping(value="/stone/jpa/board/write", method=RequestMethod.GET)
	public String boardWrite() throws Exception {
		return "stone/board/jpaBoardWrite";
	}
	
	@RequestMapping(value="/stone/jpa/board/write", method=RequestMethod.POST)
	public String insertBoard(StoneBoardEntity board, MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception {
		this.jpaBoardService.saveBoard(board, multipartHttpServletRequest);
		return "redirect:/stone/jpa/board";
	}
	
	@RequestMapping(value="/stone/jpa/board/{num}", method=RequestMethod.GET)
	public ModelAndView boardDetail(@PathVariable("num") int num) throws Exception {
		ModelAndView mv = new ModelAndView("stone/board/jpaBoardDetail");
		mv.addObject("board", this.jpaBoardService.selectBoardDetail(num));

		return mv;
	}
	
	@RequestMapping(value="/stone/jpa/board/{num}", method=RequestMethod.PUT)
	public String updateBoard(StoneBoardEntity board) throws Exception {
		this.jpaBoardService.saveBoard(board, null);
		return "redirect:/stone/jpa/board";
	}
	
	@RequestMapping(value="/stone/jpa/board/{num}", method=RequestMethod.DELETE)
	public String deleteBoard(@PathVariable("num") int num) throws Exception {
		this.jpaBoardService.deleteBoard(num);
		return "redirect:/stone/jpa/board";
	}
	
	@RequestMapping(value="/stone/jpa/board/file", method=RequestMethod.GET)
	public void downloadBoardFile(@RequestParam int fileNum, @RequestParam int num,
			HttpServletResponse response) throws Exception {
		StoneBoardFileEntity fileDto = this.jpaBoardService.selectBoardFile(fileNum, num);
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
