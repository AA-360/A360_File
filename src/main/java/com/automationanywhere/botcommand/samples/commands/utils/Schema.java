package com.automationanywhere.botcommand.samples.commands.utils;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.automationanywhere.commandsdk.model.DataType;

public class Schema {
    private DataType type;
    private String name;

    public Schema() {
        this.type = DataType.STRING;
        this.name = "";
    }

    public Schema(String name) {
        this.type = DataType.STRING;
        this.name = "";
        this.name = name;
    }

    public Schema(String name, DataType type) {
        this.type = DataType.STRING;
        this.name = "";
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getType() {
        return this.type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
}

