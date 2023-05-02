package com.example.had.response;

import java.util.UUID;

public class Usage
{

    String usageTime;
    int count;

    public Usage(String usageTime, int count) {
        this.usageTime = usageTime;
        this.count = count;
    }


    public String getUsageTime() {
        return usageTime;
    }

    public void setUsageTime(String usageTime) {
        this.usageTime = usageTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Usage{" +
                "usageTime='" + usageTime + '\'' +
                ", count=" + count +
                '}';
    }
}
