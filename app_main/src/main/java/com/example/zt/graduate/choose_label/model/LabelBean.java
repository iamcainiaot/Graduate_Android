package com.example.zt.graduate.choose_label.model;

/**
 * @author taozhu5
 * @date 2019/4/17 16:48
 * @description 描述
 */
public class LabelBean {
    public LabelBean() {
    }

    public LabelBean(boolean isHeader, String label) {
        this.isHeader = isHeader;
        Label = label;
    }

    /**
     * 是否是头
     */
    private boolean isHeader;
    /**
     * 显示内容
     */
    private String Label;

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }
}


