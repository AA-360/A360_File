package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.botcommand.data.impl.StringValue;
import com.automationanywhere.botcommand.samples.commands.utils.File;
import com.automationanywhere.commandsdk.annotations.*;
import com.automationanywhere.commandsdk.annotations.rules.FileExtension;
import com.automationanywhere.commandsdk.annotations.rules.FileFolder;
import com.automationanywhere.commandsdk.annotations.rules.NotEmpty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.automationanywhere.commandsdk.model.DataType.STRING;
import static com.automationanywhere.commandsdk.model.AttributeType.FILE;
import static com.automationanywhere.commandsdk.model.AttributeType.*;


@BotCommand
@CommandPkg(label = "CreateDirectories",
        description = "Creates directories\n This action creates a folder or a full address of a folder:\n Sample: \n\tC:\\Temp\\test1\\test2\n\tHere the action will make sure all folders exists an create all of them.",
        icon = "pkg.svg",
        name = "CreateDirectories",
        node_label = "Create directories {{address}}"
)


public class CreateDirectories {

    @Execute
    public void action(
            @Idx(index = "1", type = TEXT)
            @Pkg(label = "Address:")
            @NotEmpty
            @FileFolder
            String address
    ) {
        try{
            Files.createDirectories(Paths.get(address));
        }catch (IOException e){
            throw new RuntimeException(e);
        }



    }
}
