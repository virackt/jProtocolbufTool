package me.veryvic.game.tool.velocity;

import me.veryvic.game.tool.entity.ClassInfo;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.List;

/**
 * Created by vic on 15-3-27.
 */
public abstract class AbstractConverter {
    // velocity配置文件目录
    protected String velocityFilePath;
    // velocity配置文件名称
    protected String velocityFileName;
    // 一系列文件映射
    protected List<ClassInfo> classInfoList;
    // 输出目录
    protected String mappingFilePath;

    public void convert() {
        // 获取模板引擎
        VelocityEngine ve = new VelocityEngine();
        // 模板文件所在的路径
        ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, velocityFilePath);
        // 处理中文问题
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        try {
            // 初始化模板
            ve.init();
            // 获取模板
            Template template = ve.getTemplate("java.vm");
            // 获取上下文
            VelocityContext root = new VelocityContext();
            // 把数据填入上下文
            root.internalPut("list", classInfoList);
            // 输出
            Writer writer = new PrintWriter(new FileOutputStream(new File(mappingFilePath)));
            template.merge(root, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
