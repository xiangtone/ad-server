package cn.adwalker.ad.upload.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.adwalker.ad.upload.service.UploadService;
import cn.adwalker.ad.upload.util.AppConstant;
import cn.adwalker.ad.upload.util.MessageConstant;
import cn.adwalker.ad.upload.vo.file.FallVO;
import cn.adwalker.ad.upload.vo.file.SucceedVO;
import cn.adwalker.ad.util.ConfigUtil;

public class FileUploadService extends UploadService {

	private static Log log = LogFactory.getLog(FileUploadService.class);

	/**
	 * 上传文件方法
	 * 
	 * @param policy
	 *            文件名称策略 0:不变 1:随机生成
	 * @param fileName
	 *            文件名称
	 * @param path
	 *            上传的相对路径
	 * @param isCover
	 *            是否覆盖 0:不覆盖 1:覆盖
	 */
	public Map<String, Object> uploadFile(String policy, String path, String isCover, HttpServletRequest request) {
		// 判断文件夹是否存在，如果不存在则创建文件夹
		mkdirs(ConfigUtil.getString("file.path") + path);
		// 解析请求
		List<FileItem> fileList = getFileItemList(request);
		if (fileList == null) {
			return getErrorReturn(MessageConstant.ERROR_PARSE_REQUEST);
		}
		// 根据上传策略上传文件
		return doPolicy(policy, path, isCover, fileList);
	}

	/**
	 * 根据策略处理上传
	 * 
	 * @param policy
	 * @param path
	 * @param isCover
	 * @return
	 */
	private Map<String, Object> doPolicy(String policy, String path, String isCover, List<FileItem> fileList) {
		if (policy == null || (!policy.equals(AppConstant.FILE_NAME_POLOCY_NOCHANGE_RULE) && !policy.equals(AppConstant.FILE_NAME_POLOCY_RANDOM) && !policy.equals(AppConstant.FILE_NAME_POLOCY_NOCHANGE_ALL))) {
			log.error("策略参数错误，policy=" + policy);
			return getErrorReturn(MessageConstant.ERROR_REQUEST_PARAM);
		}

		if (!isCover.equals(AppConstant.IS_COVER_YES) && !isCover.equals(AppConstant.IS_COVER_NO)) {
			log.error("策略参数错误，isCover=" + isCover);
			return getErrorReturn(MessageConstant.ERROR_REQUEST_PARAM);
		}

		String absolutePath = ConfigUtil.getString("file.path") + path;// 拼接绝对路径

		Map<String, Object> map = new HashMap<String, Object>();// 返回值
		List<SucceedVO> suList = new ArrayList<SucceedVO>();// 上传成功的集合
		List<FallVO> faList = new ArrayList<FallVO>();// 上传失败的集合

		Iterator<FileItem> it = fileList.iterator();

		String oldName = "";
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				oldName = item.getName();
				String name = item.getName();
				long size = item.getSize();
				String type = item.getContentType();
				log.info("size=" + size + " type=" + type);

				if (name == null || name.trim().equals("")) {
					faList.add(new FallVO("", MessageConstant.FALL_FILE_NAME_NULL));
					continue;
				}
				if (policy.equals(AppConstant.FILE_NAME_POLOCY_RANDOM)) {// 随机生成名称
					// 扩展名格式：
					String extName = "";
					if (name.lastIndexOf(".") >= 0) {
						extName = name.substring(name.lastIndexOf("."));
					}
					// name = UUID.randomUUID().toString() + extName;
					name = System.currentTimeMillis() + extName;
				} else if (policy.equals(AppConstant.FILE_NAME_POLOCY_NOCHANGE_RULE)) {
					if (!name.matches(AppConstant.FILE_NAME_MATCH)) {
						faList.add(new FallVO(oldName, MessageConstant.FALL_FILE_NAME_ILLEGAL));
						continue;
					}
				}

				File saveFile = new File(absolutePath + name);

				if (saveFile.exists()) {// 判断是否覆盖
					if (isCover.equals(AppConstant.IS_COVER_NO)) {// 不覆盖
						faList.add(new FallVO(oldName, MessageConstant.FALL_FILE_NOT_COVER));
						continue;
					}
				}
				try {
					item.write(saveFile);
					suList.add(new SucceedVO(oldName, name, size, type, path));
				} catch (Exception e) {
					e.printStackTrace();
					faList.add(new FallVO(oldName, e.getMessage()));
				}
			}
		}

		if (suList.size() != 0) {
			map.put(AppConstant.KEY_SUCCEED, suList);
		}
		if (faList.size() != 0) {
			map.put(AppConstant.KEY_FALL, faList);
		}

		return map;
	}

}
