package com.renhejia.robot.letianpaiservice.parser;

/**
 * @author liujunbin
 */
public class CmdInfo {

    private String command;
    private String data;

    public CmdInfo(String command, String data) {
        this.command = command;
        this.data = data;
    }

    public String getCommand() {
        return command;
    }

    public String getData() {
        return data;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "command:'" + command + '\'' +
                ", data:'" + data + '\'' +
                '}';
    }
}