package managment;

import java.io.IOException;

public interface CacheManager {
		public void save(String problem) throws IOException;
		public String load() throws IOException;

}
