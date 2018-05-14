package com.dondeestudiar.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;


public class UploadFiles {
	
	public String subirFoto(MultipartFile file, String ruta) throws IOException {
		String filename = "";
		if(!file.isEmpty()) {
			filename = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
			Path rutaRaizRelativa = Paths.get(ruta).resolve(filename);
			Path rutaAbsoluta = rutaRaizRelativa.toAbsolutePath();
			Files.copy(file.getInputStream(), rutaAbsoluta);
		}
		return filename;
	}
	
	public Resource cargarImagen(String filename, String ruta) throws MalformedURLException {
		Path rutaFoto = getPath(filename, ruta);
		Resource recurso = null;
		recurso = new UrlResource(rutaFoto.toUri());
		if( !recurso.exists() || !recurso.isReadable() ) {
			throw new RuntimeException("Error: Ocurrio un error al cargar la imagen "+rutaFoto.toString());
		}
		return recurso;
	}
	
	public boolean eliminarImagen(String filename, String ruta) {
		Path rutaFoto = getPath(filename, ruta);
		File archivo = rutaFoto.toFile();
		if( archivo.exists() && archivo.canRead() ) {
			if( archivo.delete() )
				return true;
		}
		return false;
	}
	
	private Path getPath(String filename, String ruta) {
		return Paths.get(ruta).resolve(filename).toAbsolutePath();
	}
	
}
