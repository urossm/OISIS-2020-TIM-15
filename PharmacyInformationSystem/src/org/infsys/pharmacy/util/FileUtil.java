package org.infsys.pharmacy.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtil {

	public static Object loadObjectFromFile(File file) {	
		if (file == null || !file.exists()) {
			return null;
		}
		
		try(ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
			return objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
	}
	
	public static void saveObjectInFile(Object object, File file) {
		if (object == null || file == null) {
			return;
		}
		
		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
			objectOutputStream.writeObject(object);
		} catch (IOException e) {
			return;
		}
	}
}