import java.io.*;
import java.nio.file.*;

/*
Adds .mp4 at the end of files without extension inside given directory.
It is recommended to compile with the following command:
javac -encoding UTF8 Add_MP4.java.
*/

class Add_MP4{

	public static void Add_MP4(){}

	public static String getName(String name){
		int i1, i2, len;
		i2 = len = name.length();
		while( name.charAt(i2-1)!='.' && name.charAt(i2-1)!='/' ) i2--;
		if( name.charAt(i2-1)=='/' ) return name.substring(i2, len);
		i1 = i2;
		while( name.charAt(i1)!='/' ) i1--;
		return name.substring(i1+1, i2-1);
	}

	public static boolean hasExtension( String name ) {
		int i1, i2, len;
		i2 = len = name.length();
		while( name.charAt(i2-1)!='.' && name.charAt(i2-1)!='/' ) i2--;
		return  (name.charAt( i2-1 ) == '.');
	}

	public static void main(String args[]) throws IOException{
		Path dir = Paths.get("/Users/Josue/Movies");

		String name;
		try {
			DirectoryStream<Path> dirStream = Files.newDirectoryStream(dir, "*");
			for( Path archive: dirStream ){
				if( !Files.isDirectory(archive) && !hasExtension(archive.toString()) ){
					name = getName(archive.toString());
					Files.move(archive, archive.resolveSibling(name+".mp4"));
				}
			}

		} catch ( DirectoryIteratorException x ){
			System.err.println(x);
		}
	}
}
