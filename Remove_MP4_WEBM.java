import java.io.*;
import java.nio.file.*;

/* Should compile with following command: javac -encoding UTF8 Remove_MP4_WEBM.java. */
class Remove_MP4_WEBM{

	public static void Remove_MP4_WEBM(){}

	public static String getName(String name){
		int i1, i2, len;
		i2 = len = name.length();
		while( name.charAt(i2-1)!='.' ) i2--;
		i1 = i2;
		while( name.charAt(i1)!='/' ) i1--;
		return name.substring(i1+1, i2-1);
	}

	public static void main(String args[]) throws IOException{
		//Following path should be edited according to folder in question
		Path dir = Paths.get("/Users/Josue/Movies");
		String name;
		try {
			DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir, "*.{webm,mp4}");
			for( Path archive: dirStream ){
				if( !Files.isDirectory(archive) ){
					name = getName(archive.toString());
					Files.move(archive, archive.resolveSibling(name));
				}
			}

		} catch ( DirectoryIteratorException x ){
			System.err.println(x);
		}	
	}
}