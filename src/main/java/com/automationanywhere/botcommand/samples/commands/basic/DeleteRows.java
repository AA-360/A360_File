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

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.botcommand.samples.commands.utils.File;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.automationanywhere.commandsdk.model.AttributeType.*;

@BotCommand
@CommandPkg(label = "DeleteRowsInFile",
        description = "Esta action deleta linhas em um arquivo de texto", icon = "pkg.svg", name = "DeleteRowsInFile",
        return_description = "")


public class DeleteRows {

    @Execute
    public void action(
            @Idx(index = "1", type = FILE)
            @Pkg(label = "Arquivo")
            @NotEmpty String Arquivo,
            @Idx(index = "2", type = NUMBER)
            @Pkg(label = "From")
            Double from,
            @Idx(index = "3", type = NUMBER)
            @Pkg(label = "To")
            Double to,
            @Idx(index = "4", type = BOOLEAN)
            @Pkg(label = "Igorar Linhas em branco")
            Boolean clearEmptyLines
    ) {
        File fl = new File();

        List<String> buffer = fl.readFile(Arquivo);
        List<String> bufferOut = new ArrayList<String>();

        //========================================== AJUSTA INDEXES ==================

        Integer newFrom = idxArr(from.intValue(), buffer);
        Integer newTo = idxArr(to.intValue(), buffer);


        if(newFrom >= buffer.size()){
            throw new BotCommandException("Index '" + from.intValue() + "' higher than file rows!");
        }
        if(newTo >= buffer.size()){
            throw new BotCommandException("Index '" + to.intValue() + "' higher than file rows!");
        }
        if(newFrom > newTo){
            throw new BotCommandException("Index from("+from.intValue()+") is > than to("+to.intValue()+")");
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
            }
            if(!(index >= newFrom && index <= newTo)){
                bufferOut.add(line);
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
