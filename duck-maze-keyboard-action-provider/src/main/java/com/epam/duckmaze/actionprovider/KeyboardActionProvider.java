package com.epam.duckmaze.actionprovider;

import com.epam.duckmaze.ActionEnum;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

/**
 * Created by .
 */
public class KeyboardActionProvider implements MazeActionProvider {
    @Override
    public ActionEnum getAction(Set<ActionEnum> availableAction) {
        System.out.println("Available actions: " + availableAction);
        System.out.println("write command!");
        char c = 0;
        BufferedReader reader;
        reader=new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String command=reader.readLine();
                ActionEnum actionByChar = ActionEnum.getActionByChar(command.charAt(0));
                if (!availableAction.contains(actionByChar)) {
                    throw new IllegalArgumentException("NotAvailable action!");
                }
                return actionByChar;
            } catch (IOException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("please write right command");
            }
        }
    }
}
