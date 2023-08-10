package com.laptrinhjavaweb.dto;

import com.laptrinhjavaweb.entity.GroupEntity;

public class UserDTO extends AbstractDTO{
    private String userName;
    private String password;
    private int status;
    private Long GroupId;
    private String GroupName;

    private String fullName;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getGroupId() {
        return GroupId;
    }

    public void setGroupId(Long groupId) {
        GroupId = groupId;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
