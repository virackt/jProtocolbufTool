package me.veryvic.game.tool.entity;

/**
 * Created by vic on 15-3-26.
 */
public class ClassInfo {
    private int key;
    private String value;

    public ClassInfo(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
