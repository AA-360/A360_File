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

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.data.impl.TableValue;
import com.automationanywhere.botcommand.data.model.Schema;
import com.automationanywhere.botcommand.data.model.table.Row;
import com.automationanywhere.botcommand.data.model.table.Table;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.*;

@BotCommand
@CommandPkg(label = "GetFilesFromPath",
        description = "Esta action captura os arquivos de uma pasta",
        icon = "pkg.svg",
        name = "GetFilesFromPath",
        return_description = "",
        return_type = TABLE,
        return_required = true
)


public class GetAllFromPath {

    @Execute
    public TableValue action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Diretório")
            @NotEmpty String diretorio,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Regex",default_value = ".*", default_value_type = STRING)
            String pattern
    ) {
        List<Schema> schemas = getHeaders();
        List<Row> listRows= new ArrayList<>();

        File folder = new File(diretorio);
        String[] lst = folder.list();
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
                if(file.getName().matches(pattern)) {
                    listRows.add(getDetails(file));
            }
        }

        Table OUTPUT = new Table(schemas,listRows);

        return new TableValue(OUTPUT);


    }

    Row getDetails(File file){
        try{
            List<Value> rwValue = new ArrayList<>();


            Path p = Paths.get(file.getAbsolutePath());
            BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();

            rwValue.add(new StringValue(file.getName()));
            rwValue.add(new StringValue(file.getCanonicalPath()));
            rwValue.add(new StringValue(view.creationTime().toString()));
            rwValue.add(new StringValue(view.lastAccessTime().toString()));
            rwValue.add(new StringValue(view.lastModifiedTime().toString()));
            rwValue.add(new StringValue(String.valueOf(view.isDirectory())));
            rwValue.add(new StringValue(String.valueOf(view.isOther())));
            rwValue.add(new StringValue(String.valueOf(view.isRegularFile())));
            rwValue.add(new StringValue(String.valueOf(view.isSymbolicLink())));
            rwValue.add(new StringValue(String.valueOf(view.size())));

            return new Row(rwValue);
        }
        catch(IOException e){
            throw new BotCommandException(e.getMessage());
        }

    }

    List<Schema> getHeaders(){
        List<Schema> schemas = new ArrayList<>();

        //================== CREATE SCHEMAS ===============
        schemas.add(new Schema("fileName"));
        schemas.add(new Schema("fileFullName"));
        schemas.add(new Schema("creationTime"));
        schemas.add(new Schema("lastAccessTime"));
        schemas.add(new Schema("lastModifiedTime"));
        schemas.add(new Schema("isDirectory"));
        schemas.add(new Schema("isOther"));
        schemas.add(new Schema("isRegularFile"));
        schemas.add(new Schema("isSymbolicLink"));
        schemas.add(new Schema("size"));
        return schemas;
    }
}
