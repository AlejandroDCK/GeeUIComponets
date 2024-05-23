package com.letianpai.robot.components.parser.voiceconfig;

public class VoiceConfig {
    private int is_close_voice_aide;

    public int getIs_close_voice_aide() {
        return is_close_voice_aide;
    }

    public void setIs_close_voice_aide(int is_close_voice_aide) {
        this.is_close_voice_aide = is_close_voice_aide;
    }

    @Override
    public String toString() {
        return "VoiceConfig{" +
                "is_close_voice_aide:" + is_close_voice_aide +
                '}';
    }
}
