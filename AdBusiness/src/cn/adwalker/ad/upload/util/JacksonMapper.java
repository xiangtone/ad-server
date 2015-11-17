package cn.adwalker.ad.upload.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

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
	 * json to list
	 * 
	 * @param <T>
	 * @param jsonVal
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> jsonToList(String json, Class<?> clazz) throws Exception {
		ObjectMapper mapper = JacksonMapper.getInstance();
		List<T> list = mapper.readValue(json, TypeFactory.collectionType(ArrayList.class, clazz));
		return list;
	}

	/**
	 * String json = "..."; ObjectMapper mapper = JacksonMapper.getInstance();
	 * YourBean bean = mapper.readValue(json, new YourBean().getClass());
	 */

}
