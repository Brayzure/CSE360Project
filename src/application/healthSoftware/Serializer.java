package application.healthSoftware;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
	// Decode serialized object into something we can use
	public static Object deserialize(String fileName) throws IOException,
			ClassNotFoundException,
			FileNotFoundException {
		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}
	
	// Encode object into serialized object onto disk
	public static void serialize(Object obj, String fileName)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		
		fos.close();
	}
}
