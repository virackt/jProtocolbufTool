package me.veryvic.game.tool.velocity;

import me.veryvic.game.tool.entity.ClassInfo;
import me.veryvic.game.tool.util.PathUtil;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 15-3-27.
 */
public class JConverter extends AbstractConverter {
    private String messagePackage;
    private List<String> packageList;

    public JConverter(String velocityFilePath, String velocityFileName, List<ClassInfo> classInfoList, String mappingFilePath, String messagePackage) {
        this.velocityFileName = velocityFileName;
        this.classInfoList = classInfoList;
        this.velocityFilePath = velocityFilePath;
        this.mappingFilePath = mappingFilePath;
        this.messagePackage = messagePackage;
        packageList = new ArrayList<String>();
        for(ClassInfo info : classInfoList){
            packageList.add(messagePackage + "." + info.getValue());
        }
    }

    @Override
    public void convert() {
        // 获取模板引擎
        VelocityEngine ve = new VelocityEngine();
        // 模板文件所在的路径
        ve.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, PathUtil.getClassPath() + velocityFilePath);
        // 处理中文问题
        ve.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        ve.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        Writer writer = null;
        try {
            // 初始化模板
            ve.init();
            // 获取模板
            Template template = ve.getTemplate("java.vm");
            // 获取上下文
            VelocityContext root = new VelocityContext();
            // 输出文件
            File file = new File(mappingFilePath);
            // 把数据填入上下文
            root.internalPut("list", classInfoList);
            root.internalPut("className", file.getName().split("\\.")[0]);
            root.internalPut("package", getPackage());
            root.internalPut("imps", packageList);
            // 输出
            writer = new PrintWriter(new FileOutputStream(file));
            template.merge(root, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取java包
     * @return
     */
    private String getPackage(){
        return mappingFilePath.substring(mappingFilePath.indexOf("/src/main/java/") + "/src/main/java/".length(), mappingFilePath.lastIndexOf("/")).replace("/", ".");
    }
}
