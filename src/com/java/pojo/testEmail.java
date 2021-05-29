package com.java.pojo;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
public class testEmail {
        //JavaMail需要Properties来创建一个session对象。它将寻找字符串"mail.smtp.host"，属性值就是发送邮件的主机.
        public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.qq.com");// 设置smtp主机
        properties.put("mail.smtp.auth", "true");// 使用smtp身份验证
        /*
     * 在 JavaMail 中，可以通过 extends Authenticator 抽象类，在子类中覆盖父类中的 getPasswordAuthentication()
     * 方法，就可以实现以不同的方式来进行登录邮箱时的用户身份认证。JavaMail 中的这种设计是使用了策略模式（Strategy
     * */
        MimeMessage message = new MimeMessage(Session.getInstance(properties,new Authenticator() {
public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("lhfing123456@163.com", "JUNAXTWURRWIUULC");
        }
        }));
        //设置邮件的属性
        //设置邮件的发件人
        message.setFrom(new InternetAddress("1980496903@qq.com"));
        //设置邮件的收件人   cc表示抄送   bcc 表示暗送
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("1980496903@qq.com"));
        //设置邮件的主题
        message.setSubject("世界上最复杂的邮件有附件和图片");
        //创建邮件的正文
        MimeBodyPart text = new MimeBodyPart();
        // setContent(“邮件的正文内容”,”设置邮件内容的编码方式”)
        text.setContent("世界上最复杂的邮件<img src='//test.jpg'>","text/html;charset=gb2312");
        //创建图片
        MimeBodyPart img = new MimeBodyPart();
       /*JavaMail API不限制信息只为文本,任何形式的信息都可能作茧自缚MimeMessage的一部分.
        * 除了文本信息,作为文件附件包含在电子邮件信息的一部分是很普遍的.
        * JavaMail API通过使用DataHandler对象,提供一个允许我们包含非文本BodyPart对象的简便方法.*/
        DataHandler dh = new DataHandler(new FileDataSource("src//test.jpg"));
        img.setDataHandler(dh);
        //创建图片的一个表示用于显示在邮件中显示
        img.setContentID("a");

        //创建附件
        /*MimeBodyPart attch = new MimeBodyPart();
        DataHandler dh1 = new DataHandler(new FileDataSource("src//test.docx"));
        attch.setDataHandler(dh1);
        String filename1 = dh1.getName();
        // MimeUtility 是一个工具类，encodeText（）用于处理附件字，防止中文乱码问题
        attch.setFileName(MimeUtility.encodeText(filename1));
        //关系   正文和图片的
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(img);
        mm.setSubType("related");//设置正文与图片之间的关系
        //图班与正文的 body
        MimeBodyPart all = new MimeBodyPart();
        all.setContent(mm);
        //附件与正文（text 和 img）的关系
        MimeMultipart mm2 = new MimeMultipart();
        mm2.addBodyPart(all);
        mm2.addBodyPart(attch);
        mm2.setSubType("mixed");//设置正文与附件之间的关系*/

        //message.setContent(mm2);
        message.saveChanges(); //保存修改
        Transport.send(message);//发送邮件

        }
        }
