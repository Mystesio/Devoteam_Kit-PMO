package com.devoteam.pmo.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.entity.Project;
import com.devoteam.pmo.repository.PhaseRepository;
import com.devoteam.pmo.repository.ProjectRepository;
import com.devoteam.pmo.service.PhaseService;



public class PhaseServiceTest {

    @Mock
    private PhaseRepository phaseRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private PhaseService phaseService;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // Mock the behavior of projectRepository
        when(projectRepository.findById(1L)).thenReturn(Optional.of(new Project()));

        // Set the projectRepository in the phaseService using ReflectionTestUtils
        ReflectionTestUtils.setField(phaseService, "projectRepository", projectRepository);
    }



    @Test
    public void testShowPhases() throws Exception {
        // Arrange
        long projectId = 1L;
        List<Phase> phases = Arrays.asList(new Phase(), new Phase());
        when(phaseRepository.findByProjectProjectId(projectId)).thenReturn(phases);

        // Act
        List<Phase> result = phaseService.showPhases(projectId);

        // Assert
        assertEquals(phases, result);
    }

    @Test
    public void testShowPhaseExisting() throws Exception {
        // Arrange
        long phaseId = 1L;
        Phase phase = new Phase();
        when(phaseRepository.findById(phaseId)).thenReturn(Optional.of(phase));

        // Act
        Phase result = phaseService.showPhase(phaseId);

        // Assert
        assertEquals(phase, result);
    }

    @Test
    public void testShowPhaseNonExisting() {
        // Arrange
        long phaseId = 1L;
        when(phaseRepository.findById(phaseId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(Exception.class, () -> phaseService.showPhase(phaseId));
    }

    @Test
    public void testAddNewPhaseWithExistingProject() {
        // Arrange
        long projectId = 1L;
        Phase newPhase = new Phase();
        Project project = new Project();
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(phaseRepository.save(newPhase)).thenReturn(newPhase);

        // Act
        Phase result = phaseService.addNewPhase(projectId, newPhase);

        // Assert
        assertEquals(newPhase, result);
        assertEquals(project, result.getProject());
    }

    @Test
    public void testAddNewPhaseWithNonExistingProject() {
        // Arrange
        long projectId = 1L;
        Phase newPhase = new Phase();
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> phaseService.addNewPhase(projectId, newPhase));
    }

    @Test
    public void testDeletePhase() {
        // Arrange
        Phase phase = new Phase();

        // Act
        phaseService.deletePhase(phase);

        // Assert
        verify(phaseRepository, times(1)).delete(phase);
    }

    @Test
    public void testUpdate() {
        // Arrange
        long phaseId = 1L;
        Phase phase = new Phase();

        // Act
        phaseService.update(phase, phaseId);

        // Assert
        verify(phaseRepository, times(1)).save(phase);
    }
}

