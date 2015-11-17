package cn.adwalker.ad.upload.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.adwalker.ad.upload.util.AppConstant;

public class UploadService {

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 */
	public void mkdirs(String path) {
		System.out.println("********************"+path);
		File f1 = new File(path);
		if (!f1.exists()) {
			f1.mkdirs();
		}
	}

	/**
	 * 处理请求返回文件集合
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileItem> getFileItemList(HttpServletRequest request) {

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding(AppConstant.UTF8);

		List<FileItem> fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			ex.printStackTrace();
		}
		return fileList;
	}

	/**
	 * 错误的返回值
	 * 
	 * @param message
	 * @return
	 */
	public Map<String, Object> getErrorReturn(String message) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(AppConstant.KEY_ERROR, message);
		return map;
	}

}
