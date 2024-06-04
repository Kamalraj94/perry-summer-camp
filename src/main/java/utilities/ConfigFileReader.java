package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	private Properties prop;
	private final String propertyFilePath = "src/test/resources/testdata.properties";

	public ConfigFileReader() throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(propertyFilePath);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
	}

	public String getURL() {
		String url = prop.getProperty("end_point_url");
		return url;
	}

	public String getUrlForId() {
		String url = prop.getProperty("user_model_get_id");
		return url;
	}

	public String postUrlForId() {
		String url = prop.getProperty("user_model_post");
		return url;
	}

	public String putURLforId() {
		String url = prop.getProperty("user_model_put");
		return url;
	}
	public String deleteURLforId() {
		String url = prop.getProperty("user_model_delete");
		return url;
	}

	public String postRequestForSendingMessage(){
		String url = prop.getProperty("message_model_post");
		return url;
	}

	public String getRequestForMessageValidation(){
		String url = prop.getProperty("message_model_get");
		return url;
	}


	public String putRequestForMessageValidation(){
		String url = prop.getProperty("message_model_put");
		return url;
	}

}
