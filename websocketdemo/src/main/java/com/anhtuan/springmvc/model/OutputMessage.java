package com.anhtuan.springmvc.model;

import java.util.Date;

public class OutputMessage {

    private Date time;

    private Message message;

    public OutputMessage(Message message, Date time) {
        this.message = message;
        this.time = time;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OutputMessage{" +
                "time=" + time.toString() +
                ", message=" + message.toString() +
                '}';
    }
}
