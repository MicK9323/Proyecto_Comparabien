package utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import sun.misc.BASE64Decoder;



public class UtilFiles {
	
	
	
	private Path getPath(String filename) {
		return Paths.get(Utilitario.Constantes.UPLOAD_FOLDER).resolve(filename).toAbsolutePath();
	}
	
	@SuppressWarnings("unused")
	private InputStream getInputStream(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		return is;
	}
	
	public void createFolfer() throws IOException {
		Files.createDirectory(Paths.get(Utilitario.Constantes.UPLOAD_FOLDER));
		
	}
	
	public String subirLogo(String imageB64) throws IOException {
		String uniquefilename = UUID.randomUUID().toString()+".png";
		Path rootPath = getPath(uniquefilename);
		FileOutputStream fos = new FileOutputStream(rootPath.toFile().getPath());
		System.out.println("*********");
		System.out.println(imageB64);
		String replaced = "";
		replaced = imageB64.replace("/", "");
		System.out.println(replaced);
		byte[] byteArray = Base64.getDecoder().decode(replaced.split(",")[1]);
		fos.write(byteArray);
		fos.close();
		return uniquefilename;
	}
	
	
}
