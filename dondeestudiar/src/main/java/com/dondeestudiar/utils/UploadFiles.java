package com.dondeestudiar.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public class UploadFiles {
	
	public String subirFoto(MultipartFile file) throws IOException {
		String filename = "";
		if(!file.isEmpty()) {
			filename = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
			Path rutaRaizRelativa = Paths.get(Constantes.FILE_UPLOADS).resolve(filename);
			Path rutaAbsoluta = rutaRaizRelativa.toAbsolutePath();
			Files.copy(file.getInputStream(), rutaAbsoluta);
		}
		return filename;
	}
	
}
