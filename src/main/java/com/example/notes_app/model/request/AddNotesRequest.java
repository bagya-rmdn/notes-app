package com.example.notes_app.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNotesRequest {
    private String title;
    private String content;
}
