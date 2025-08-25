package com.example.notes_app.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesResponse {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Boolean isArchived;
    private String createdAt;
    private String updatedAt;
}
