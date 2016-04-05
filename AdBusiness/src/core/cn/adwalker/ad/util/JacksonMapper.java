package cn.adwalker.ad.util;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonMapper {

	private static final ObjectMapper mapper = new ObjectMapper();

	private JacksonMapper() {

	}

	public static ObjectMapper getInstance() {
		return mapper;
	}

	/**
	 * object to json String
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToJsonString(Object obj) throws IOException {

		ObjectMapper mapper = JacksonMapper.getInstance();
		StringWriter sw = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
		mapper.writeValue(gen, obj);
		gen.close();
		String json = sw.toString();

		return json;
	}

	/**
	 * String json = "..."; ObjectMapper mapper = JacksonMapper.getInstance();
	 * YourBean bean = mapper.readValue(json, new YourBean().getClass());
	 */

	 public static void main(String[] args) {

	}
}
