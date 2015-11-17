package cn.adwalker.ad.upload.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.adwalker.ad.upload.service.UploadService;
import cn.adwalker.ad.upload.util.AppConstant;
import cn.adwalker.ad.upload.util.ImageUtil;
import cn.adwalker.ad.upload.util.MessageConstant;
import cn.adwalker.ad.upload.vo.image.FallVO;
import cn.adwalker.ad.upload.vo.image.SucceedVO;
import cn.adwalker.ad.util.ConfigUtil;

public class ImageUploadService extends UploadService {

	private static Log log = LogFactory.getLog(ImageUploadService.class);

	/**
	 * 上传文件方法
	 * 
	 * @param policy
	 *            图片名称策略 0:不变 1:随机生成
	 * @param imgName
	 *            图片名称
	 * @param path
	 *            上传的相对路径
	 * @param isCover
	 *            是否覆盖 0:不覆盖 1:覆盖
	 * @param canCut
	 *            是否允许裁剪 0:不裁剪 1:裁剪
	 * @param zoomSize
	 *            图片缩放尺寸
	 * @param count
	 *            规定上传的图片的数量
	 */
	public Map<String, Object> uploadImage(String policy, String path, String isCover, String canCut, String zoomSize, HttpServletRequest request) {
		// 判断文件夹是否存在，如果不存在则创建文件夹
		mkdirs(ConfigUtil.getString("img.path") + path);

		// 解析请求
		List<FileItem> imgList = getFileItemList(request);
		if (imgList == null) {
			return getErrorReturn(MessageConstant.ERROR_PARSE_REQUEST);
		}
		// if (minSizeLimit != null) {
		// if (!minSizeLimit.matches(AppConstant.IS_NUM_MATCH)) {// 验证参数是否为正整数
		// return getErrorReturn(MessageConstant.ERROR_PARAM_FORMAT);
		// }
		// int c = Integer.parseInt(minSizeLimit);
		// int size = Function.getSize(imgList);
		// if (size > c) {
		// return getErrorReturn(MessageConstant.ERROR_IMAGE_COUNT);
		// }
		// }

		// 根据上传策略上传文件
		return doPolicy(policy, path, isCover, imgList, canCut, zoomSize);
	}

	/**
	 * 根据策略处理上传
	 * 
	 * @param policy
	 * @param path
	 * @param isCover
	 * @return
	 */
	private Map<String, Object> doPolicy(String policy, String path, String isCover, List<FileItem> imgList, String canCut, String zoomSize) {

		if (!policy.equals(AppConstant.FILE_NAME_POLOCY_NOCHANGE_RULE) && !policy.equals(AppConstant.FILE_NAME_POLOCY_RANDOM)) {
			log.error("策略参数错误，policy=" + policy);
			return getErrorReturn(MessageConstant.ERROR_REQUEST_PARAM);
		}

		if (!isCover.equals(AppConstant.IS_COVER_YES) && !isCover.equals(AppConstant.IS_COVER_NO)) {
			log.error("是否覆盖参数错误，isCover=" + isCover);
			return getErrorReturn(MessageConstant.ERROR_REQUEST_PARAM);
		}

		if (canCut == null || (!canCut.equals(AppConstant.IS_CUT_YES) && !canCut.equals(AppConstant.IS_CUT_NO))) {
			log.error("是否裁剪错误，canCut=" + canCut);
			return getErrorReturn(MessageConstant.ERROR_REQUEST_PARAM);
		}

		String absolutePath = ConfigUtil.getString("img.path") + path;// 拼接绝对路径

		Map<String, Object> map = new HashMap<String, Object>();// 返回值
		List<SucceedVO> suList = new ArrayList<SucceedVO>();// 上传成功的集合
		List<FallVO> faList = new ArrayList<FallVO>();// 上传失败的集合

		Iterator<FileItem> it = imgList.iterator();

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
					if (!name.matches(AppConstant.IMAGE_NAME_MATCH)) {
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
				if (zoomSize == null || zoomSize.trim().equals("")) {// 验证尺寸
					faList.add(new FallVO(oldName, MessageConstant.ERROR_REQUEST_PARAM));
					continue;
				}
				if (!zoomSize.matches(AppConstant.IMAGE_ZOOM_SIZE_MATCH)) {// 验证尺寸宽度格式
					faList.add(new FallVO(oldName, MessageConstant.ERROR_PARAM_FORMAT));
					continue;
				}

				int zoomWidth = Integer.parseInt(zoomSize.substring(0, zoomSize.indexOf("_")));
				int zoomHeight = Integer.parseInt(zoomSize.substring(zoomSize.indexOf("_") + 1));
				InputStream in;
				int width = 0;// 原图宽度
				int height = 0;// 原图高度
				try {
					in = item.getInputStream();
					BufferedImage srcImage = ImageIO.read(in);
					width = srcImage.getWidth();// 读取原图宽度
					height = srcImage.getHeight();// 读取原图高度
				} catch (IOException e) {
					e.printStackTrace();
					faList.add(new FallVO(oldName, e.getMessage()));
				}

				// if (canCut.equals(AppConstant.IS_CUT_NO)) {// 判断是否允许裁剪图片
				// // 不裁剪则计算比例是否匹配
				// if (width > 0 && height > 0) {
				// if (width / height != zoomWidth / zoomHeight) {
				// faList.add(new FallVO(oldName,
				// MessageConstant.FALL_IMAGE_NOT_CUT));
				// continue;
				// }
				// } else {
				// faList.add(new FallVO(oldName,
				// MessageConstant.ERROR_PARSE_IMAGE));
				// continue;
				// }
				// }
				try {
					item.write(saveFile);// 储存原图
					ImageUtil imageUtil = new ImageUtil();// 进行图片按要求裁剪
					String newName = name.substring(0, name.lastIndexOf(".")) + "_" + zoomWidth + "_" + zoomHeight;// 截取文件名
					newName = newName + name.substring(name.lastIndexOf("."));// 拼接后缀名
					imageUtil.saveZoomImage(absolutePath + name, absolutePath + newName, zoomWidth, zoomHeight, Integer.parseInt(canCut));

					suList.add(new SucceedVO(oldName, newName, size, type, width, height, zoomWidth, zoomHeight, path));
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
