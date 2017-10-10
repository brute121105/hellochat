package org.liufeng.course.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.NewsMessage;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.util.DBHelper;
import org.liufeng.course.util.MessageUtil;
import org.liufeng.weixin.pojo.AccessToken;
import org.liufeng.weixin.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-07-25
 */
public class CoreService {
	
	//private static Logger log = LoggerFactory.getLogger(CoreService.class);
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			System.out.println("");
			System.out.println("msgType:"+msgType);
			System.out.println("fromUserName:"+fromUserName);
			System.out.println("toUserName:"+toUserName);

			// 默认回复此文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义			
			StringBuffer contentMsg = new StringBuffer();  
			contentMsg.append("您好，欢迎来到顺风车留言平台，\n输入任意字符查询信息：").append("\n\n");  
			contentMsg.append("人找车，\n车找人").append("\n");  

			textMessage.setContent(contentMsg.toString());
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");
				ParsePhoneService service = new ParsePhoneService();
				String msg = service.savePhone(content);
				textMessage.setContent(msg);
				respMessage = MessageUtil.textMessageToXml(textMessage);
				/*
				// 单图文消息
				if ("1".equals(content)) {
					StringBuffer cn = new StringBuffer();  
					cn.append("1、人找车，北京到南宁.3点..").append("\n");  
					cn.append("2、人找车，北京到河池...").append("\n");  
					cn.append("3、人找车，广州到南宁...").append("\n");  
					cn.append("4、人找车，深圳到南宁...").append("\n");  
					textMessage.setContent(cn.toString());
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
}