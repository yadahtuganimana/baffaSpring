//package com.securitylast.emails.notification.service;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Service
//@Slf4j
//public class StorageService {
//
//	 @Value("${upload.path}")
//	    private String url;
//       
//	    public String upload(MultipartFile file,String url,String fileName){
//	        String msg=null;
//	        try {
//	            //String fileName = file.getOriginalFilename();
//	            InputStream is = file.getInputStream();
//	            String format=file.getContentType();
//	            String extension = (format.equalsIgnoreCase("image/jpeg") ? ".jpg" : (format.equalsIgnoreCase("image/png")) ? ".png" : format.equalsIgnoreCase("image/gif") ? ".gif" : format.equalsIgnoreCase("application/pdf") ? ".pdf" : "");
//	            String newName = UUID.randomUUID().toString() + extension;
//	            Files.copy(is, Paths.get(url + fileName),
//	                    StandardCopyOption.REPLACE_EXISTING);
//	        } catch (IOException e) {
//	            msg = String.format("Failed to store file", file.getName());
//	            e.printStackTrace();
//	        }
//	        return msg;
//	    }
//	
//}
