package com.example.notes_app.service.notes;

import com.example.notes_app.entity.NotesEntity;
import com.example.notes_app.entity.UserEntity;
import com.example.notes_app.model.request.AddNotesRequest;
import com.example.notes_app.model.request.UpdateNotesRequest;
import com.example.notes_app.model.response.NotesResponse;
import com.example.notes_app.repository.NotesRepository;
import com.example.notes_app.repository.UserRepository;
import com.example.notes_app.security.AuthenticationContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotesServiceImpl implements NotesService {
    private final NotesRepository notesRepository;
    private final UserRepository userRepository;
    private final AuthenticationContext userContext;


    @Override
    public NotesResponse createNote(AddNotesRequest request) {
        UserEntity user = userContext.getCurrentLoggedInUser();

        userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        NotesEntity note = new NotesEntity();
        note.setUserId(user);
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        note.setIsArchived(false);
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAt(LocalDateTime.now());

        NotesEntity saved = notesRepository.save(note);
        return mapToResponse(saved);
    }

    @Override
    public List<NotesResponse> getAllNotes(Boolean isArchived) {
        UserEntity user = userContext.getCurrentLoggedInUser();
        List<NotesEntity> notes;

        if (isArchived == null) {
            // Tidak ada filter -> ambil semua
            notes = notesRepository.findByUserId_Id(user.getId());
        } else {
            // Ada filter -> ambil sesuai status archive
            notes = notesRepository.findByUserId_IdAndIsArchived(user.getId(), isArchived);
        }

        return notes.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotesResponse getNoteById(Long noteId) {
        UserEntity user = userContext.getCurrentLoggedInUser();

        NotesEntity note = notesRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserId().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        return mapToResponse(note);
    }

    @Override
    public NotesResponse updateNote(Long noteId, UpdateNotesRequest request) {
        UserEntity user = userContext.getCurrentLoggedInUser();

        NotesEntity note = notesRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserId().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        if (request.getTitle() != null) {
            note.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            note.setContent(request.getContent());
        }
        if (request.getIsArchived() != null) {
            note.setIsArchived(request.getIsArchived());
        }
        note.setUpdatedAt(LocalDateTime.now());

        NotesEntity updated = notesRepository.save(note);
        return mapToResponse(updated);
    }

    @Override
    public void deleteNote(Long noteId) {
        UserEntity user = userContext.getCurrentLoggedInUser();

        NotesEntity note = notesRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUserId().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied");
        }

        notesRepository.delete(note);
    }

    private NotesResponse mapToResponse(NotesEntity entity) {
        NotesResponse res = new NotesResponse();
        res.setId(entity.getId());
        res.setUserId(entity.getUserId().getId());
        res.setTitle(entity.getTitle());
        res.setContent(entity.getContent());
        res.setIsArchived(entity.getIsArchived());
        res.setCreatedAt(entity.getCreatedAt().toString());
        res.setUpdatedAt(entity.getUpdatedAt().toString());
        return res;
    }
}
