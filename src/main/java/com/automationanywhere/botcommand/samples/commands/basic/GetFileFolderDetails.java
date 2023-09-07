package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.data.impl.DictionaryValue;
import com.automationanywhere.botcommand.data.impl.StringValue;
import java.io.File;

import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static com.automationanywhere.commandsdk.model.AttributeType.FILE;
import static com.automationanywhere.commandsdk.model.DataType.DICTIONARY;

@BotCommand
@CommandPkg(label = "GetFileDetails",
        description = "Get File Details",
        icon = "pkg.svg",
        name = "GetFileDetails",
        return_description = "",
        return_type = DICTIONARY,
        return_required = true)


public class GetFileFolderDetails {

    @Execute
    public DictionaryValue action(
            @Idx(index = "1", type = FILE)
            @Pkg(label = "Path")
            String file
    ) {
        DictionaryValue ret = new DictionaryValue();
        Map<String, Value> v= new HashMap<String, Value>();;



        try{

            File fl = new File(file);

            Path p = Paths.get(fl.getAbsolutePath());
            BasicFileAttributes view = Files.getFileAttributeView(p, BasicFileAttributeView.class).readAttributes();


            v.put("creationTime",new StringValue(view.creationTime().toString()));
            v.put("lastAccessTime",new StringValue(view.lastAccessTime().toString()));
            v.put("lastModifiedTime",new StringValue(view.lastModifiedTime().toString()));
            v.put("isDirectory",new StringValue(String.valueOf(view.isDirectory())));
            v.put("isOther",new StringValue(String.valueOf(view.isOther())));
            v.put("isRegularFile",new StringValue(String.valueOf(view.isRegularFile())));
            v.put("isSymbolicLink",new StringValue(String.valueOf(view.isSymbolicLink())));
            v.put("size",new StringValue(String.valueOf(view.size())));
        }
        catch(IOException e){
            throw new BotCommandException(e.getMessage());
        }


        ret.set(v);
        return ret;
    }
}
