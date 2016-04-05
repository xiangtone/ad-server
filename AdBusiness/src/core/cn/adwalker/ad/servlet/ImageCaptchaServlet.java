package cn.adwalker.ad.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.service.CaptchaServiceException;

public class ImageCaptchaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
	}

	protected void doGet(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ServletException,
			IOException {
		genernateCaptchaImage(httpServletRequest, httpServletResponse);
		// System.out.println("DO GET....");
		// byte[] captchaChallengeAsJpeg = null;
		// // the output stream to render the captcha image as jpeg into
		// ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		// try {
		// // get the session id that will identify the generated captcha.
		// // the same id must be used to validate the response, the session id
		// // is a good candidate!
		// String captchaId = httpServletRequest.getSession().getId();
		// // call the ImageCaptchaService getChallenge method
		// BufferedImage challenge = CaptchaServiceSingleton.getInstance()
		// .getImageChallengeForID(captchaId,
		// httpServletRequest.getLocale());
		//
		// // a jpeg encoder
		// JPEGImageEncoder jpegEncoder = JPEGCodec
		// .createJPEGEncoder(jpegOutputStream);
		// jpegEncoder.encode(challenge);
		// } catch (IllegalArgumentException e) {
		// httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
		// return;
		// } catch (CaptchaServiceException e) {
		// httpServletResponse
		// .sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		// return;
		// }
		// captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		// // flush it in the response
		// httpServletResponse.setHeader("Cache-Control", "no-store");
		// httpServletResponse.setHeader("Pragma", "no-cache");
		// httpServletResponse.setDateHeader("Expires", 0);
		// httpServletResponse.setContentType("image/jpeg");
		// ServletOutputStream responseOutputStream = httpServletResponse
		// .getOutputStream();
		// responseOutputStream.write(captchaChallengeAsJpeg);
		//		
		// responseOutputStream.flush();
		// responseOutputStream.close();
	}

	/**
	 * 验证码servlet
	 */
	private void genernateCaptchaImage(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		try {
			String captchaId = request.getSession(true).getId();
			BufferedImage challenge = (BufferedImage) CaptchaServiceSingleton
					.getInstance().getChallengeForID(captchaId,
							request.getLocale());
			ImageIO.write(challenge, "jpg", out);
			out.flush();
		} catch (CaptchaServiceException e) {
		} finally {
			out.close();
		}
	}
}