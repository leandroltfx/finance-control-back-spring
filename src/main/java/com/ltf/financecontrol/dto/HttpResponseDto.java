package com.ltf.financecontrol.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HttpResponseDto {

    private List<String> listMessage = new ArrayList<>();
    private Object data;

    public void addMessage(String message) {
        this.listMessage.add(message);
    }

}
