package com.example.remotehomeelectricalcontrolsystem.Model;

import java.util.List;

public class CheckDevice {
    public int endTime;
    public String nameDevice;
    public int startTime;
    public int state;

    public List<CheckDHT> getDhtList() {
        return dhtList;
    }

    public void setDhtList(List<CheckDHT> dhtList) {
        this.dhtList = dhtList;
    }


    public CheckDevice(int endTime, String nameDevice, int startTime, int state, List<CheckDHT> dhtList) {
        this.endTime = endTime;
        this.nameDevice = nameDevice;
        this.startTime = startTime;
        this.state = state;
        this.dhtList = dhtList;
    }

    public CheckDevice(int endTime, String nameDevice, int startTime, int state )  {
        this.endTime = endTime;
        this.nameDevice = nameDevice;
        this.startTime = startTime;
        this.state = state;

    }
    public List<CheckDHT> dhtList;

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
