package com.itzixi;


import com.itzixi.mcp.tool.Tools;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@MapperScan("com.itzixi.mapper")
@SpringBootApplication
public class Application {

//    http://localhost:9060/sse

    @Autowired
    private Tools mcpTool;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("启动成功");
    }

    /**
     * 注册MCP工具
     */
    @Bean
    public ToolCallbackProvider registMCPTools(Tools tools) {
        return tools.createToolCallbackProvider();
    }

}
