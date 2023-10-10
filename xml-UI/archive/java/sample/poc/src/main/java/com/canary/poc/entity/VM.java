package com.canary.poc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VM {
    @JsonProperty
    private String name;
    @JsonProperty
    private String vm;
    @JsonProperty
    private String power_state;
    @JsonProperty
    private int memory_size_MiB;
    @JsonProperty
    private int cpu_count;
    @JsonProperty
    private String ip;

    @Override
    public String toString() {
        return "VM{" +
                "name='" + name + '\'' +
                ", vm='" + vm + '\'' +
                ", power_state='" + power_state + '\'' +
                ", memory_size_MiB=" + memory_size_MiB +
                ", cpu_count=" + cpu_count +
                ", ip='" + ip + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVm() {
        return vm;
    }

    public void setVm(String vm) {
        this.vm = vm;
    }

    public String getPower_state() {
        return power_state;
    }

    public void setPower_state(String power_state) {
        this.power_state = power_state;
    }

    public int getMemory_size_MiB() {
        return memory_size_MiB;
    }

    public void setMemory_size_MiB(int memory_size_MiB) {
        this.memory_size_MiB = memory_size_MiB;
    }

    public int getCpu_count() {
        return cpu_count;
    }

    public void setCpu_count(int cpu_count) {
        this.cpu_count = cpu_count;
    }
}
