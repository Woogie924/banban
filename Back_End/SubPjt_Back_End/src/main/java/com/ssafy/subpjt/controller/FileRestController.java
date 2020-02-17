package com.ssafy.subpjt.controller;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.subpjt.service.JwtService;


@RestController
@RequestMapping("/Img")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class FileRestController {

	@Autowired
	private JwtService jwtService;

	@PostMapping("/insertImg")
	private ResponseEntity<String> imgIsert(HttpServletRequest request, HttpServletResponse response) throws Exception{

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
			String path = "C://git//sub_pjt_2//Back_End//SubPjt_Back_End//src//main//webapp//image//";
			File serverFile = new File(path +origName ); 
			mfile.transferTo(serverFile);
			ans = origName;
		}
		System.out.println(ans);
		return new ResponseEntity<String>(ans, HttpStatus.OK);
	}

}
