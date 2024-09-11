package com.project.task.helpers;

import com.project.task.enums.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Message {
    private String text;
    private MessageType messageType;
}
