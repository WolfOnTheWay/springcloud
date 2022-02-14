package com.sdt.fsc.entity.record;

import java.io.Serializable;

public class RecordTagDTO implements Serializable {
    private String level;
    private String stage;
    private String content;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
