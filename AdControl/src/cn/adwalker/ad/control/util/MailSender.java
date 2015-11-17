package cn.adwalker.ad.control.util;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 使用spring的mail发送邮件
 * @author liuchen
 * 
 */
public class MailSender {

	private JavaMailSenderImpl mailSender;

	/**
	 * 构造方法，初始化数据
	 */
	public MailSender(String mailHost, String username, String password) {
		// 初始化JavaMailSenderImpl，当然推荐在spring配置文件中配置，这里是为了简单
		mailSender = new JavaMailSenderImpl();
		// 设置参数
		mailSender.setHost(mailHost);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		Properties p = new Properties();
		// 邮件认证
		p.setProperty("mail.smtp.auth", "true");
		mailSender.setJavaMailProperties(p);
	}

	/**
	 * 文本格式邮件发送
	 * @param to 接收者
	 * @param subject 标题
	 * @param text 内容
	 */
	public void simpleSend(String[] to, String subject, String text) {
		// 构建简单邮件对象，见名知意
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 设定邮件参数
		mailMessage.setFrom(mailSender.getUsername());
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(text);
		// 发送邮件
		mailSender.send(mailMessage);
	}

	/**
	 * 文本和HTML格式邮件发送
	 * 
	 * @param to 接收者
	 * @param subject 标题
	 * @param text 内容
	 * @param isHtml 是否HTML格式
	 * @throws MessagingException
	 */
	public void send(String[] to, String subject, String text, boolean isHtml, String encoding) throws MessagingException {
		// 使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
		MimeMessage msg = mailSender.createMimeMessage();
		// 创建MimeMessageHelper对象，处理MimeMessage的辅助类
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, encoding);
		// 使用辅助类MimeMessage设定参数
		helper.setFrom(mailSender.getUsername());
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, isHtml);
		
		// 发送邮件
		mailSender.send(msg);
	}

	/**
	 * 带附件的文本和HTML格式邮件发送
	 * 
	 * @param to 接收者
	 * @param subject 标题
	 * @param text 内容
	 * @param fileName 附件名称
	 * @param filePath 附件路径
	 * @param isHtml 是否HTML格式
	 * @throws MessagingException
	 */
	public void attachedSend(String[] to, String subject, String text, String fileName, String filePath, boolean isHtml) throws MessagingException {
		// 使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
		MimeMessage msg = mailSender.createMimeMessage();
		// 创建MimeMessageHelper对象，处理MimeMessage的辅助类
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		// 使用辅助类MimeMessage设定参数
		helper.setFrom(mailSender.getUsername());
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, isHtml);
		// 加载文件资源，作为附件
		FileSystemResource file = new FileSystemResource(filePath);
		// 加入附件
		helper.addAttachment(fileName, file);
		// 发送邮件
		mailSender.send(msg);
	}
}
