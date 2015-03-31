package me.veryvic.game.tool.init;

import me.veryvic.game.tool.entity.ClassInfo;
import me.veryvic.game.tool.util.AppConfig;
import me.veryvic.game.tool.util.PathUtil;
import me.veryvic.game.tool.velocity.CConverter;
import me.veryvic.game.tool.velocity.JConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vic on 15-3-26.
 */
public class Main {

    public static void main(String[] args) {
        String inputPath = AppConfig.getStringValue("inputPath");
        String outputPath = AppConfig.getStringValue("outputPath");
        File file = new File(inputPath);
        File[] files = file.listFiles();
        List<ClassInfo> list = execProto(files, inputPath, outputPath);
        String velocityFilePath = AppConfig.getStringValue("velocityFilePath");
        String javaMappingFile = AppConfig.getStringValue("javaMappingFile");
        String messagePackage = AppConfig.getStringValue("messagePackage");
//        String csharpMappingFile = AppConfig.getStringValue("csharpMappingFile");

        new JConverter(velocityFilePath, "java.vm", list, javaMappingFile, messagePackage).convert();
//        new CConverter(velocityFilePath, "csharp.vm", list, csharpMappingFile).convert();

    }

    private static List<ClassInfo> execProto(File[] files, String inputPath, String outputPath) {
        List<ClassInfo> classInfos = new ArrayList<ClassInfo>();
        int startIndex = AppConfig.getIntValue("startIndex");
        try {
            for (File file : files) {
                exeProtoc(file.getName(), inputPath, outputPath);
                classInfos.add(new ClassInfo(startIndex, getClassName(file.getName())));
                startIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classInfos;
    }

    private static String getClassName(String fileName){
        return fileName.split("\\.")[0];
    }

    /**
     * 执行protoc命令
     *
     * @param fileName
     * @param inputPath
     * @param outputPath
     * @throws IOException
     */
    private static void exeProtoc(String fileName, String inputPath, String outputPath) throws IOException {
        Runtime.getRuntime().exec("protoc -I=" + inputPath + " --java_out=" + outputPath + " " + inputPath + File.separator + fileName);
    }

}
