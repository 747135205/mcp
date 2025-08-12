package com.itzixi.mcp.tool;

import com.itzixi.mcp.McpTool;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Author: 123
* @Description: 用来自动注册的中心
* @DateTime: 2025
*/
@Component
public class Tools implements ApplicationContextAware {
    private List<McpTool> mcpTools;
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        mcpTools = new ArrayList<>();
        Map<String, McpTool> tools = applicationContext.getBeansOfType(McpTool.class);
        //使用注释来标记需要使用的mcp-tools
//        applicationContext.getBeansWithAnnotation(xxx)
        mcpTools.addAll(tools.values());
    }

    public ToolCallbackProvider createToolCallbackProvider() {
        return MethodToolCallbackProvider.builder()
                .toolObjects(mcpTools.toArray())
                .build();
    }

    private void addTool(McpTool mcpTool){
        mcpTools.add(mcpTool);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
