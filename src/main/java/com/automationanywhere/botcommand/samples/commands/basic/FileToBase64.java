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
import com.automationanywhere.botcommand.exception.BotCommandException;
//import com.automationanywhere.botcommand.samples.commands.utils.File;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;

import java.util.Base64;
import java.nio.file.Files;

@BotCommand
@CommandPkg(
        label = "FileToBase64",
        description = "Esta action converte arquivos para Base64",
        icon = "pkg.svg",
        name = "FileToBase64",
        return_type = DataType.STRING,
        return_description = "String Base64",
        return_required = true
)


public class FileToBase64 {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = FILE)
            @Pkg(label = "Arquivo")
            @NotEmpty
            String Arquivo
    ) {
        File fl = new File(Arquivo);
        String base64 = this.encodeFileToBase64(fl);

        return new StringValue(base64);
    }

    private static String encodeFileToBase64(java.io.File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

    public String encodeFileToBase64_2(String file) {
        try {
            return new String(Base64.getEncoder().encode(Files.readAllBytes(Paths.get(file))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
