package managment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileCacheManager implements CacheManager {
	
	private String key;

	@Override
	public void save(String problem) throws IOException {
		//System.out.println("my problem is "+ problem );
		List<String> lines = Arrays.asList(problem.split(System.lineSeparator()));
		//System.out.println("my lines are "+ lines );
		//System.out.println("my lines length "+ lines.size() );
		Path file = Paths.get(key + ".txt");
		Files.write(file, lines, Charset.forName("ASCII"));
	}
	
	public FileCacheManager(String key) {
		this.key = key;
	}
	
	@Override
	public String load() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(key + ".txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    return sb.toString();
		} finally {
		    br.close();
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
