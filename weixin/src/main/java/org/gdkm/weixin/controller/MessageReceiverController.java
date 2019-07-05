package org.gdkm.weixin.controller;

import org.gdkm.commons.domain.InMessage;
import org.gdkm.weixin.service.MessageService;
import org.gdkm.weixin.service.MessageTypeRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;


@RestController // 基于RESTful风格的WEB服务的控制器
@RequestMapping("/lyf/weixin/reciver") // 访问哪个路径的时候，被此控制器处理
public class MessageReceiverController {

	@Autowired
	private MessageService messageService;
	@Autowired
	@Qualifier("xmlMapper")
	private XmlMapper xmlMapper;

	private static final Logger LOG = LoggerFactory.getLogger(MessageReceiverController.class);

	@GetMapping
	public String echo(//
			@RequestParam("signature") String signature, //
			@RequestParam("timestamp") String timestamp, //
			@RequestParam("nonce") String nonce, //
			@RequestParam("echostr") String echostr//
	) {
		

		return echostr;
	}

	@PostMapping
	public String receive(//
			@RequestParam(value = "signature", required = false) String signature, //
			@RequestParam(value = "timestamp", required = false) String timestamp, //
			@RequestParam(value = "nonce", required = false) String nonce, //
			// @RequestBody 表示请求体
			@RequestBody String xml//
	) {
		
		LOG.debug("\n收到请求参数\n"//
				+ "    signature : {}\n"// 大括号是一个占位符，需要后面继续传入实际的参数
				+ "    timestamp : {}\n"//
				+ "    nonce : {}\n"//
				+ "收到的请求内容\n{}\n"//
				, signature, timestamp, nonce, xml);



		// 截取XML字符串里面的消息类型
		String type = xml.substring(xml.indexOf("<MsgType><![CDATA[") + 18);
		type = type.substring(0, type.indexOf("]]></MsgType>"));

		// 根据消息类型，找到对应的Java类型
		Class<? extends InMessage> cla = MessageTypeRegister.getClass(type);

		
		try {
			InMessage inMessage = xmlMapper.readValue(xml, cla);

			
			this.messageService.onMessage(inMessage);
		} catch (Exception e) {
			LOG.error("处理公众号信息出现错误：{}", e.getMessage());
			LOG.debug("处理公众号信息时出现的错误详情：", e);
		}

		return "success";
	}
}
