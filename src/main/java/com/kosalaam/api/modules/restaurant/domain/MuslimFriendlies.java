package com.kosalaam.api.modules.restaurant.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MuslimFriendlies {
    HALAL_CERTIFIED("할랄 공식 인증"),
    SELF_CERTIFIED("무슬림 자가 인증"),
    MUSLIM_FRIENDLY("무슬림 프렌들리"),
    FORK_FREE("포크 프리"),
    NONE("NONE");

    private String name;
}
