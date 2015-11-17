package cn.adwalker.ad.control.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import cn.adwalker.ad.control.vo.AdActualDayVo;

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
		if (obj == null) {
			return "";
		}
		ObjectMapper mapper = JacksonMapper.getInstance();
		StringWriter sw = new StringWriter();
		JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
		mapper.writeValue(gen, obj);
		gen.close();
		return sw.toString();
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
		List<T> list = null;
		if (json != null) {
			ObjectMapper mapper = JacksonMapper.getInstance();
			list = mapper.readValue(json, TypeFactory.collectionType(ArrayList.class, clazz));
		}
		return list;
	}

	public static String resultSrting(Result result, String jsoncallback) throws IOException {
		StringBuilder sb = new StringBuilder();
		return sb.append(jsoncallback).append("(").append(objectToJsonString(result)).append(")").toString();
	}

	public static void main(String[] args) {
		try {
			Result result = new Result();
			System.out.println(JacksonMapper.objectToJsonString(result));
			List<AdActualDayVo> adActualDayList = JacksonMapper.jsonToList("[{\"adId\":\"111\",\"impressionsAmount\":\"222\",\"clickAmount\":\"333\",\"downloadAmount\":\"444\",\"actionAmount\":\"555\"},{\"adId\":\"666\",\"impressionsAmount\":\"777\",\"clickAmount\":\"888\",\"downloadAmount\":\"999\",\"actionAmount\":\"0\"}]", AdActualDayVo.class);
			System.out.println(adActualDayList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
