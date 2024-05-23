package com.letianpai.robot.components.expressionres.parser;

import java.util.Arrays;

public class ExpressionFileData {


    private String version;
    private ExpressionFileInfo[] file_list;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ExpressionFileInfo[] getFile_list() {
        return file_list;
    }

    public void setFile_list(ExpressionFileInfo[] file_list) {
        this.file_list = file_list;
    }

    @Override
    public String toString() {
        return "{" +
                "version='" + version + '\'' +
                ", file_list=" + Arrays.toString(file_list) +
                '}';
    }
}
