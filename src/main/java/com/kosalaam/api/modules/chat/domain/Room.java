package com.kosalaam.api.modules.chat.domain;

public class Room {
    int hostId;
    String roomName;

    public int getHostId() {
        return hostId;
    }
    public void setHostId(int hostId) {
        this.hostId = hostId;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + hostId + ", roomName=" + roomName + "]";
    }
}
