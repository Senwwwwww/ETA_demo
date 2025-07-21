package com.example.EAT_demo;

//import com.example.EAT_demo.server.TCPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 启用异步支持
@EnableAspectJAutoProxy(proxyTargetClass = true) // 强制使用CGLIB代理
public class EatDemoApplication {

	public static void main(String[] args) {
		// 启动 Spring 应用并获取上下文
		ApplicationContext context = SpringApplication.run(EatDemoApplication.class, args);

		// 从上下文获取 TCPServer 实例
//		TCPServer tcpServer = context.getBean(TCPServer.class);

		// 启动 TCP 服务器
//		new Thread(tcpServer::startServer).start(); // 调用 TCPServer 的启动方法
	}
}