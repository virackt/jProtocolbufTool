package me.veryvic.game.tool.velocity;

import me.veryvic.game.tool.entity.ClassInfo;

import java.util.List;

/**
 * Created by vic on 15-3-27.
 */
public class CConverter extends AbstractConverter {

    public CConverter(String velocityFilePath, String velocityFileName, List<ClassInfo> classInfoList, String mappingFilePath) {
        this.velocityFileName = velocityFileName;
        this.classInfoList = classInfoList;
        this.velocityFilePath = velocityFilePath;
        this.mappingFilePath = mappingFilePath;
    }
}
