package client;

import java.io.Serializable;

public class FileInfo implements Serializable {
	public int size;
	public String name;
	public byte[] file;
	public FileInfo(int s, String n, byte[] f) {
		size = s;
		name = n;
		file = f;
	}
}
