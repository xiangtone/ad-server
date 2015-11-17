package cn.adwalker.core.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片工具类：完成图片的放缩，并根据传入图片路径创建相应的文件夹。 把生成新的图片存入其中。
 * 
 * @author gary
 * 
 */
public class ImageUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(ImageUtil.class);

	/**
	 * 实现图像的等比缩放和缩放后的截取
	 * 
	 * @param inFilePath
	 *            要截取文件的路径
	 * @param outFilePath
	 *            截取后输出的路径
	 * @param width
	 *            要截取宽度
	 * @param hight
	 *            要截取的高度
	 * @param isCut
	 *            是否裁剪 0:等比 1:裁剪
	 * 
	 * @throws Exception
	 */
	public static void saveZoomImage(String inFilePath, String outFilePath,
			int width, int height, int isCut) throws Exception {
		width = Math.max(width, 1);
		height = Math.max(height, 1);
		File file = new File(inFilePath);
		InputStream in = new FileInputStream(file);
		File saveFile = new File(outFilePath);
		BufferedImage srcImage = ImageIO.read(in);
		resize(srcImage, width, height, saveFile, isCut);
		in.close();
	}

	/**
	 * 实现图像的等比缩放
	 * 
	 * @param source
	 * @param targetW
	 * @param targetH
	 * @return
	 */
	private static void resize(BufferedImage source, int targetW, int targetH,
			File saveFile, int isCut) throws Exception {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;

		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();

		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		if (isCut == 0) {// 等比
			if (sx < sy) {
				sx = sy;
				targetW = (int) (sx * source.getWidth());
			} else {// 非等比
				sy = sx;
				targetH = (int) (sy * source.getHeight());
			}
		}

		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,
					targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(targetW, targetH, type);
		}
		Graphics2D g = target.createGraphics();

		// smoother than exlax:
		RenderingHints qualityHints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		qualityHints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(qualityHints);

		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();

		String fileName = saveFile.getName();
		String typeName = fileName.substring(fileName.lastIndexOf('.') + 1);

		ImageIO.write(target, typeName, saveFile);

		return;
	}

	/**
	 * 截取实现缩放后的截图
	 * 
	 * @param image
	 *            缩放后的图像
	 * @param subImageBounds
	 *            要截取的子图的范围
	 * @param subImageFile
	 *            要保存的文件
	 * @throws IOException
	 */
	public void saveSubImage(BufferedImage source, int w, int h, File saveFile)
			throws IOException {

		int width = w;
		int height = h;

		double sx = (double) w / source.getWidth();
		double sy = (double) h / source.getHeight();

		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx < sy) {
			sx = sy;
			w = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			h = (int) (sy * source.getHeight());
		}

		int x = 0;
		int y = 0;
		// 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取
		if (w == width) {
			// 计算X轴坐标
			x = 0;
			y = h / 2 - height / 2;
		}
		// 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取
		else if (h == height) {
			// 计算X轴坐标
			x = w / 2 - width / 2;
			y = 0;
		}

		BufferedImage subImage = source.getSubimage(x, y, width, height);

		String fileName = saveFile.getName();
		String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);

		// 转换成原图格式的图片
		ImageIO.write(subImage, formatName, saveFile);
		return;
	}

	public static void catImg01(String url, String out, int width, int height)
			throws IOException {
		File _file = new File(url);// 创建文件对象
		FileCopyUtils.copy(_file,new File(out));
	}

	public static void catImg(String url, String out, int width, int height)
			throws IOException {
		File _file = new File(url);// 创建文件对象
		BufferedImage image = javax.imageio.ImageIO.read(_file);
		int _width = image.getWidth(null);
		int _height = image.getHeight(null);
		int tempwith = 0;
		int tempheight = 0;
		BufferedImage tempImg = null;
		if ((Double.valueOf(_width) / _height) <= 1) {
			tempheight = height;
			tempwith = (int) (_width / ((Double.valueOf(_height) / height)));
			tempImg = zoomImage(image, tempwith, tempheight);
		}
		if (tempImg == null) {
			tempImg = image;
		}

		int top = 0;
		int left = (width - tempwith) / 2;
		BufferedImage tag = new BufferedImage(width, height, 1);
		Graphics taGraphics = tag.getGraphics();
		taGraphics.setColor(new Color(227, 227, 227));
		taGraphics.fillRect(0, 0, width, height);// 图片底色
		taGraphics.drawImage(tempImg, left, top, tempwith, tempheight, null);
		taGraphics.dispose();
		FileOutputStream outputStream = new FileOutputStream(out);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outputStream);
		encoder.encode(tag);
		outputStream.close();
	}

	public final static BufferedImage zoomImage(BufferedImage image, int width,
			int height) throws ImageFormatException, IOException {
		BufferedImage tempImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = tempImg.getGraphics();
		g.drawImage(image, 0, 0, width, height, null); // 绘制缩小后的图
		g.dispose();
		return tempImg;
	}

	/**
	 * 图片缩放
	 * 
	 * @param url
	 */
	public final static void zoomImage(String url, int width, int height) {
		File _file = new File(url);
		try {
			Image src = ImageIO.read(_file);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(src, 0, 0, width, height, null); // 绘制缩小后的图
			g.dispose();
			FileOutputStream out = new FileOutputStream(url);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.close();
		} catch (IOException e) {
			logger.error("IO错误", e);
		}
	}
}