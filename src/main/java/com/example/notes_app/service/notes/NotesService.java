package com.example.notes_app.service.notes;

import com.example.notes_app.model.request.AddNotesRequest;
import com.example.notes_app.model.request.UpdateNotesRequest;
import com.example.notes_app.model.response.NotesResponse;
import java.util.List;

public interface NotesService {

    NotesResponse createNote(AddNotesRequest request);

    List<NotesResponse> getAllNotes(Boolean isArchived);

    NotesResponse getNoteById(Long noteId);

    NotesResponse updateNote(Long noteId, UpdateNotesRequest request);

    void deleteNote(Long noteId);
}
