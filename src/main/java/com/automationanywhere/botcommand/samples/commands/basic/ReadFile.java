package com.automationanywhere.botcommand.samples.commands.basic;
import java.util.ArrayList;
import java.util.List;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.FileValue;
import com.automationanywhere.botcommand.data.impl.ListValue;
import com.automationanywhere.botcommand.samples.commands.utils.File;
import com.automationanywhere.commandsdk.annotations.rules.FileExtension;
import com.automationanywhere.commandsdk.model.DataType;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.platform.win32.User32;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.commandsdk.annotations.*;

import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.automationanywhere.commandsdk.model.AttributeType.FILE;
import static com.automationanywhere.commandsdk.model.AttributeType.TEXT;
import static com.automationanywhere.commandsdk.model.DataType.LIST;
import static com.automationanywhere.commandsdk.model.DataType.STRING;


@BotCommand
@CommandPkg(label = "ReadFile",
        description = "Reads File",
        icon = "pkg.svg",
        name = "ReadFile",
        return_description = "",
        return_type = STRING,
        return_required = true)


public class ReadFile {

    @Execute
    public StringValue action(
            @Idx(index = "1", type = FILE)
            @Pkg(label = "File")
            @FileExtension("txt")
            String file
    ) {

        File fl = new File();
        List<String> buffer = fl.readFile(file);

        String joined = "";
        for(String line: buffer){
            joined +=line + "\n";
        }

        joined = joined.substring(0, joined.length() - 1);

        return new StringValue(joined);
    }
}
