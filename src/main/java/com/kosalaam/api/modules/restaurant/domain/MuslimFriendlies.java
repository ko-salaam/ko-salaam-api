package com.kosalaam.api.modules.restaurant.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MuslimFriendlies {
    HALAL_CERTIFIED("HALAL_CERTIFIED"),
    SELF_CERTIFIED("SELF_CERTIFIED"),
    MUSLIM_FRIENDLY("MUSLIM_FRIENDLY"),
    FORK_FREE("FORK_FREE"),
    NONE("NONE");

    private String name;
}
