package com.epam.duckmaze;

/**
 * Created by .
 */
public enum ActionEnum {
    GO_WEST("W"), GO_SOUTH("S"), GO_NORTH("N"), GO_EAST("E"), FINISH("F");

    private String commandName;

    public static ActionEnum getActionByChar(char commandName) {
        for (ActionEnum actionEnum : ActionEnum.values()) {
            if (actionEnum.commandName.charAt(0) == commandName) {
                return actionEnum;
            }
        }
        throw new IllegalArgumentException("NotSupported command name");
    }

    ActionEnum(String commandName) {

        this.commandName = commandName;
    }

    @Override
    public String toString() {
        return commandName;
    }

}
