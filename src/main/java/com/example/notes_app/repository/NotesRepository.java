package com.example.notes_app.repository;

import com.example.notes_app.entity.NotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<NotesEntity, Long> {
    List<NotesEntity> findByUserId_Id(Long userId);
    List<NotesEntity> findByUserId_IdAndIsArchived(Long userId, Boolean isArchived);
}
