package com.example.notes_app.controller;

import com.example.notes_app.model.request.AddNotesRequest;
import com.example.notes_app.model.request.UpdateNotesRequest;
import com.example.notes_app.model.response.NotesResponse;
import com.example.notes_app.service.notes.NotesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;

    @Operation(summary = "Create Notes", operationId = "createNotes", description = "Create Notes")
    @PostMapping
    public ResponseEntity<NotesResponse> createNote(@RequestBody AddNotesRequest request) {
        return ResponseEntity.ok(notesService.createNote(request));
    }

    @Operation(summary = "Get All Notes", operationId = "getAllNotes", description = "Get All Notes")
    @GetMapping
    public ResponseEntity<List<NotesResponse>> getAllNotes(
            @RequestParam(value = "isArchived", required = false) Boolean isArchived) {
        return ResponseEntity.ok(notesService.getAllNotes(isArchived));
    }


    @Operation(summary = "Get Notes By Id", operationId = "getNotesById", description = "Get Notes By Id")
    @GetMapping("/{id}")
    public ResponseEntity<NotesResponse> getNoteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(notesService.getNoteById(id));
    }

    @Operation(summary = "Update Notes", operationId = "updateNotes", description = "Update Notes")
    @PutMapping("/{id}")
    public ResponseEntity<NotesResponse> updateNote(@PathVariable("id") Long id,
                                                    @RequestBody UpdateNotesRequest request) {
        return ResponseEntity.ok(notesService.updateNote(id, request));
    }

    @Operation(summary = "Delete Notes", operationId = "deleteNotes", description = "Delete Notes")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id) {
        notesService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
