package kr.pe.stoneis.board.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.pe.stoneis.board.dto.StoneBoardFileDto;
import kr.pe.stoneis.board.entity.StoneBoardFileEntity;

@Component
public class FileUtils {

	public List<StoneBoardFileDto> parseFileInfo(int num, MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception {
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		
		List<StoneBoardFileDto> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		// ZonedDateTime 클래스 오늘 날짜 확인 JDK1.8부터 사용할 수 있음.
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/"+current.format(format);
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		String newFileName, originalFileExtension, contentType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for(MultipartFile multipartFile : list) {
				if(multipartFile.isEmpty() == false) {
					contentType =multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					StoneBoardFileDto fileDto = new StoneBoardFileDto();
					fileDto.setNum(num);
					fileDto.setFileSize(multipartFile.getSize());
					fileDto.setOriginalFileName(multipartFile.getOriginalFilename());
					fileDto.setFilePath(path + "/" + newFileName);
					fileList.add(fileDto);
					
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
	
	public List<StoneBoardFileEntity> parseFileInfo(MultipartHttpServletRequest 
			multipartHttpServletRequest) throws Exception {
		if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
			return null;
		}
		
		List<StoneBoardFileEntity> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		// ZonedDateTime 클래스 오늘 날짜 확인 JDK1.8부터 사용할 수 있음.
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/"+current.format(format);
		File file = new File(path);
		if(file.exists() == false) {
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		String newFileName, originalFileExtension, contentType;
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = multipartHttpServletRequest.getFiles(iterator.next());
			for(MultipartFile multipartFile : list) {
				if(multipartFile.isEmpty() == false) {
					contentType =multipartFile.getContentType();
					if(ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if(contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}
					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;
					StoneBoardFileEntity boardFile = new StoneBoardFileEntity();
					boardFile.setFileSize(multipartFile.getSize());
					boardFile.setOriginalFileName(multipartFile.getOriginalFilename());
					boardFile.setFilePath(path + "/" + newFileName);
					boardFile.setRegName("stone");
					fileList.add(boardFile);
					
					file = new File(path + "/" + newFileName);
					multipartFile.transferTo(file);
				}
			}
		}
		return fileList;
	}
	
}
