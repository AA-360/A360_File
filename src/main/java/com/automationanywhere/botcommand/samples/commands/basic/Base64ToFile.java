/*
 * Copyright (c) 2020 Automation Anywhere.
 * All rights reserved.
 *
 * This software is the proprietary information of Automation Anywhere.
 * You shall use it only in accordance with the terms of the license agreement
 * you entered into with Automation Anywhere.
 */

/**
 *
 */
package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.jar.Attributes;

import static com.automationanywhere.commandsdk.model.AttributeType.FILE;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;

@BotCommand
@CommandPkg(
        label = "Base64ToFile",
        description = "Esta action converte Base64 para arquivos",
        icon = "pkg.svg",
        name = "Base64ToFile"
)

public class Base64ToFile {

    @Execute
    public void action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Base64")
            @NotEmpty String Base64,
            @Idx(index = "3", type = TEXT)
            @Pkg(label = "Output File",description = "C:/folder/file.txt")
            @NotEmpty String OutputAddress
    ) {
        this.decodeBase64ToFile(Base64,OutputAddress);

    }
    public void decodeBase64ToFile(String base64,String FileAddrs) {
        try {
            Files.write(Paths.get(FileAddrs),Base64.getDecoder().decode(base64.getBytes()));
            //return new File(System.getProperty("java.io.tmpdir"), base64).getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
