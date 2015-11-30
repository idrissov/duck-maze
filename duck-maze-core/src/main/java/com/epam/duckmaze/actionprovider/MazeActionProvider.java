package com.epam.duckmaze.actionprovider;

import com.epam.duckmaze.ActionEnum;

import java.util.Set;

/**
 * Created by .
 */
public interface MazeActionProvider {

    ActionEnum getAction(Set<ActionEnum> availableAction);
}
