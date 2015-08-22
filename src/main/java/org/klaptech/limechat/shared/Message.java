package org.klaptech.limechat.shared;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * Describes object for exchange between client and server
 * @author rlapin
 */
public class Message implements Serializable{

    /**
     * ERROR message
     */
    private static final Message ERROR = new Message(MessageType.ERROR, null);
    /**
     * Type of message
     */
    private MessageType type;
    /**
     * Argument of message
     */
    private String[] args;

    private Message(MessageType type, String[] args) {
        this.type = type;
        this.args = args;
    }

    /**
     * factory method for create message using input text
     * @param text input text
     * @return message corresponding to input text
     */
    public static Message getMessage(String text) {
        EnumSet<MessageType> enumSet = EnumSet.allOf(MessageType.class);
        enumSet.remove(MessageType.ERROR);
        for (Iterator<MessageType> iterator = enumSet.iterator(); iterator.hasNext(); ) {
            MessageType messageType = iterator.next();
            String inputPattern = messageType.getInputPattern();
            if(text.startsWith(inputPattern)){ //TODO remove case sensivity in comparison
                String []args = new String[0];
                if(!text.equals(inputPattern)){
                    args = text.substring(inputPattern.length()).split(" ");
                }
                return new Message(messageType, args);
            }
        }
        return ERROR;
    }

    /**
     *
     * @return type of message
     */
    public MessageType getType() {
        return type;
    }

    /**
     *
     * @return arguments of message
     */
    public String[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(type.name());
        for (String arg : args) {
            stringBuilder.append(" ").append(arg);
        }


        return stringBuilder.toString();
    }
}
