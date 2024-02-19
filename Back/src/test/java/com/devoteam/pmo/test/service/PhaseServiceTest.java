package com.devoteam.pmo.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    }

    @Test
    public void testAddNewPhaseWithExistingProject() throws Exception {
        // Arrange
        long projectId = 1; // Note: Changed from 2 to 1
        Phase newPhase = new Phase();
        Project existingProject = new Project();
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(existingProject));
        when(phaseRepository.save(any(Phase.class))).thenReturn(newPhase);

        // Act
        Phase addedPhase = phaseService.addNewPhase(projectId, newPhase);

        // Assert
        assertNotNull(addedPhase);
        assertSame(existingProject, addedPhase.getProject());
        verify(projectRepository, times(1)).findById(projectId);
        verify(phaseRepository, times(1)).save(newPhase);
    }
}
