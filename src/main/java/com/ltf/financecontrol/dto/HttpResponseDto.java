package com.ltf.financecontrol.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HttpResponseDto {

    private List<String> messages = new ArrayList<>();
    private Object data;

    public void addMessage(String message) {
        this.messages.add(message);
    }

}
