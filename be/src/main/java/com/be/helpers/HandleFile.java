package com.be.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HandleFile {
	public static boolean writeToFile(InputStream uploadedInputStream, String uploadFileLocation) {
		try {
			OutputStream out = new FileOutputStream(new File(uploadFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			while((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
