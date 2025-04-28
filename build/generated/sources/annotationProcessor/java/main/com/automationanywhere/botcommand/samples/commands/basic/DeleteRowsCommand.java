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
import java.lang.Double;
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

public final class DeleteRowsCommand implements BotCommand {
  private static final Logger logger = LogManager.getLogger(DeleteRowsCommand.class);

  private static final Messages MESSAGES_GENERIC = MessagesFactory.getMessages("com.automationanywhere.commandsdk.generic.messages");

  @Deprecated
  public Optional<Value> execute(Map<String, Value> parameters, Map<String, Object> sessionMap) {
    return execute(null, parameters, sessionMap);
  }

  public Optional<Value> execute(GlobalSessionContext globalSessionContext,
      Map<String, Value> parameters, Map<String, Object> sessionMap) {
    logger.traceEntry(() -> parameters != null ? parameters.entrySet().stream().filter(en -> !Arrays.asList( new String[] {}).contains(en.getKey()) && en.getValue() != null).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).toString() : null, ()-> sessionMap != null ?sessionMap.toString() : null);
    DeleteRows command = new DeleteRows();
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

    if(parameters.containsKey("from") && parameters.get("from") != null && parameters.get("from").get() != null) {
      convertedParameters.put("from", parameters.get("from").get());
      if(convertedParameters.get("from") !=null && !(convertedParameters.get("from") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","from", "Double", parameters.get("from").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("from") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","from"));
    }

    if(parameters.containsKey("to") && parameters.get("to") != null && parameters.get("to").get() != null) {
      convertedParameters.put("to", parameters.get("to").get());
      if(convertedParameters.get("to") !=null && !(convertedParameters.get("to") instanceof Double)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","to", "Double", parameters.get("to").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("to") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","to"));
    }

    if(parameters.containsKey("clearEmptyLines") && parameters.get("clearEmptyLines") != null && parameters.get("clearEmptyLines").get() != null) {
      convertedParameters.put("clearEmptyLines", parameters.get("clearEmptyLines").get());
      if(convertedParameters.get("clearEmptyLines") !=null && !(convertedParameters.get("clearEmptyLines") instanceof Boolean)) {
        throw new BotCommandException(MESSAGES_GENERIC.getString("generic.UnexpectedTypeReceived","clearEmptyLines", "Boolean", parameters.get("clearEmptyLines").get().getClass().getSimpleName()));
      }
    }
    if(convertedParameters.get("clearEmptyLines") == null) {
      throw new BotCommandException(MESSAGES_GENERIC.getString("generic.validation.notEmpty","clearEmptyLines"));
    }

    try {
      command.action((String)convertedParameters.get("Arquivo"),(Double)convertedParameters.get("from"),(Double)convertedParameters.get("to"),(Boolean)convertedParameters.get("clearEmptyLines"));Optional<Value> result = Optional.empty();
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
