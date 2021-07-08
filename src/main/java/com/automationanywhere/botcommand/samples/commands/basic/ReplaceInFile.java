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
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.File;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;
import com.automationanywhere.commandsdk.model.DataType;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.automationanywhere.commandsdk.model.AttributeType.BOOLEAN;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.AttributeType.FILE;
import static com.automationanywhere.commandsdk.model.DataType.LIST;
import static com.automationanywhere.commandsdk.model.DataType.STRING;

import javax.swing.JOptionPane;

@BotCommand
@CommandPkg(label = "ReplaceInFile",
        description = "Esta action substitui caracteres em um arquivo de texto", icon = "pkg.svg", name = "ReplaceInFile",
        return_description = "")


public class ReplaceInFile {

    @Execute
    public void action(
            @Idx(index = "1", type = FILE)
            @Pkg(label = "Arquivo")
            @NotEmpty String Arquivo,
            @Idx(index = "2", type = TEXT)
            @Pkg(label = "Replace/Regex")
            String pattern,
            @Idx(index = "3", type = TEXT)
            @Pkg(label = "Novo valor", description = "Valor  ser substituido")
            String newValue,
            @Idx(index = "4", type = TEXT)
            @Pkg(label = "Deletar Linhas", description = "Ex: 0|1|3|-1 --> (-1|deleta última linha)")
            String strDelLines,
            @Idx(index = "5", type = BOOLEAN)
            @Pkg(label = "Igorar Linhas em branco")
            Boolean clearEmptyLines
    ) {
        File fl = new File();

        List<String> buffer = fl.readFile(Arquivo);
        List<String> bufferOut = new ArrayList<String>();

        //========================================== AJUSTA INDEXES ==================
        List<String> delLines = Arrays.asList(strDelLines.split("\\|"));
        List<Integer> idxDel =  new ArrayList<>();;

        Integer id=0;
        Integer newId;
        if(!delLines.get(0).trim().isEmpty())
        for(String idx: delLines) {
            id = Integer.parseInt(idx);
            newId = idxArr(id, buffer);
            if(newId >= buffer.size()){
                throw new BotCommandException("Index '" + id + "' higher than file rows!");
            }
            idxDel.add(newId);
        }
        //========================================== TRATA VALORES =====================
        //JOptionPane.showMessageDialog(null, buffer.get(0), "InfoBox: Title", JOptionPane.INFORMATION_MESSAGE);
        int index = 0;

        for(String line: buffer){
            //limpa linhas vazias
            //System.out.println(line.length());

            if(line.trim().isEmpty() && clearEmptyLines){
                index ++;
                continue;
            }else{
                if(idxDel.indexOf(index)== -1){
                    if(!pattern.trim().isEmpty())
                        line = line.replaceAll(pattern, newValue);
                    bufferOut.add(line);
                }
            }
            index ++;
        }
        fl.WritetoFile(Arquivo,bufferOut);
    }

    private Integer idxArr(Integer idx,List<String> arr){
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        if (idx >= 0)return idx;

        for(int i=arr.size()-1;i>-1;i--){
            indexes.add(i);
        }
        return indexes.get((idx*-1)-1);
    }

}
