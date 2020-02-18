package com.ssafy.subpjt.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;



@RestController
@RequestMapping("/Img")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class FileRestController {


	@PostMapping("/insertImg/{storeid}")
	private ResponseEntity<String> imgIsert(@PathVariable String storeid, HttpServletRequest request, HttpServletResponse response) throws Exception{

		System.out.println("insertImg Controller");
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> itr =multipartHttpServletRequest.getFileNames();
		MultipartFile mfile = null;
		String ans = "";
		while(itr.hasNext()) {
			String filename = itr.next();
			mfile = multipartHttpServletRequest.getFile(filename);
			String origName;
			origName=new String(mfile.getOriginalFilename());
			if ("".equals(origName)) 
			{ 
				continue; 
			} 
			String path = "C://git//sub_pjt_2//Back_End//SubPjt_Back_End//src//main//webapp//image//" + storeid +"//";
			File Folder = new File(path);
			if (!Folder.exists()) {
				try{
					Folder.mkdir(); //폴더 생성합니다.
					System.out.println("폴더가 생성되었습니다.");
				} 
				catch(Exception e){
					e.getStackTrace();
				}        
			}
			File serverFile = new File(path +origName ); 
			mfile.transferTo(serverFile);
			ans = storeid +"//" +origName;
		}
		System.out.println(ans);
		return new ResponseEntity<String>(ans, HttpStatus.OK);
	}

}
