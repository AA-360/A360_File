package com.automationanywhere.botcommand.samples.commands.basic;

import com.automationanywhere.bot.service.GlobalSessionContext;
import com.automationanywhere.botcommand.BotCommand;
import com.automationanywhere.botcommand.data.Value;
import com.automationanywhere.botcommand.exception.BotCommandException;
import com.automationanywhere.commandsdk.i18n.Messages;
import com.automationanywhere.commandsdk.i18n.MessagesFactory;
import java.lang.Boolean;
import java.lang.ClassCastException;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.Throwable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ReplaceInFileCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(ReplaceInFileCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    ReplaceInFile command = new ReplaceInFile();
    HashMap<String, Object> convertedParameters = new HashMap<String, Object>();
    if(parameters.containsKey("Arquivo") && parameters.get("Arquivo") != null && parameters.get("Arquivo").get() != null) {
      convertedParameters.put("Arquivo", parameters.get("Arquivo").get());
      if(convertedParameters.get("Arquivo") !=null && !(convertedParameters.get("Arquivo") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","Arquivo", "String", parameters.get("Arquivo").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("Arquivo") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","Arquivo"));
    }

    if(parameters.containsKey("pattern") && parameters.get("pattern") != null && parameters.get("pattern").get() != null) {
      convertedParameters.put("pattern", parameters.get("pattern").get());
      if(convertedParameters.get("pattern") !=null && !(convertedParameters.get("pattern") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","pattern", "String", parameters.get("pattern").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("newValue") && parameters.get("newValue") != null && parameters.get("newValue").get() != null) {
      convertedParameters.put("newValue", parameters.get("newValue").get());
      if(convertedParameters.get("newValue") !=null && !(convertedParameters.get("newValue") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","newValue", "String", parameters.get("newValue").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("strDelLines") && parameters.get("strDelLines") != null && parameters.get("strDelLines").get() != null) {
      convertedParameters.put("strDelLines", parameters.get("strDelLines").get());
      if(convertedParameters.get("strDelLines") !=null && !(convertedParameters.get("strDelLines") instanceof String)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","strDelLines", "String", parameters.get("strDelLines").get().getClass().getSimpleName()));
      }
    }

    if(parameters.containsKey("clearEmptyLines") && parameters.get("clearEmptyLines") != null && parameters.get("clearEmptyLines").get() != null) {
      convertedParameters.put("clearEmptyLines", parameters.get("clearEmptyLines").get());
      if(convertedParameters.get("clearEmptyLines") !=null && !(convertedParameters.get("clearEmptyLines") instanceof Boolean)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","clearEmptyLines", "Boolean", parameters.get("clearEmptyLines").get().getClass().getSimpleName()));
      }
    }

    try {
      command.action((String)convertedParameters.get("Arquivo"),(String)convertedParameters.get("pattern"),(String)convertedParameters.get("newValue"),(String)convertedParameters.get("strDelLines"),(Boolean)convertedParameters.get("clearEmptyLines"));Optional<Value> result = Optional.empty();
      return logger.traceExit(result);
    }
    catch (ClassCastException e) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.IllegalParameters","action"));
    }
    catch (BotCommandException e) {
      logger.fatal(e.getMessage(),e);
      throw e;
    }
    catch (Throwable e) {
      logger.fatal(e.getMessage(),e);
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.NotBotCommandException",e.getMessage()),e);
    }
  }
}
