package com.devoteam.pmo.test.service;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.entity.Step;
import com.devoteam.pmo.repository.PhaseRepository;
import com.devoteam.pmo.repository.StepRepository;
import com.devoteam.pmo.service.StepService;

public class StepServiceTest {

    @Mock
    private StepRepository stepRepositoryMock;
    
    @Mock
    private PhaseRepository phaseRepositoryMock;

    @InjectMocks
    private StepService stepService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowSteps() throws Exception {
        long phaseId = 1L;
        List<Step> steps = new ArrayList<>();
        // Add test steps to the list
        // ...

        when(stepRepositoryMock.findByPhasePhaseId(phaseId)).thenReturn(steps);

        List<Step> result = stepService.showSteps(phaseId);

        assertEquals(steps, result);
    }

    @Test
    public void testShowStep() throws Exception {
        long stepId = 1L;
        Step step = new Step();
        // Set test properties for the step
        // ...

        when(stepRepositoryMock.findById(stepId)).thenReturn(Optional.of(step));

        Step result = stepService.showStep(stepId);

        assertEquals(step, result);
    }

    @Test
    public void testShowStep_StepNotFound() {
        long stepId = 1L;

        when(stepRepositoryMock.findById(stepId)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> stepService.showStep(stepId));
    }

    @Test
    public void testAddNewStep() {
        long phaseId = 1L;
        Step newStep = new Step();
        // Set test properties for the new step
        // ...

        Phase phase = new Phase();
        // Set test properties for the phase
        // ...

        when(phaseRepositoryMock.findById(phaseId)).thenReturn(Optional.of(phase));
        when(stepRepositoryMock.save(newStep)).thenReturn(newStep);

        Step result = stepService.addNewStep(phaseId, newStep);

        assertEquals(newStep, result);
        assertEquals(phase, newStep.getPhase());
    }

    @Test
    public void testAddNewStep_PhaseNotFound() {
        long phaseId = 1L;
        Step newStep = new Step();
        // Set test properties for the new step
        // ...

        when(phaseRepositoryMock.findById(phaseId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> stepService.addNewStep(phaseId, newStep));
    }

    @Test
    public void testDeleteStep() {
        long stepId = 1L;

        stepService.deleteStep(stepId);

        verify(stepRepositoryMock, times(1)).deleteById(stepId);
    }

    @Test
    public void testUpdateStep() {
        long stepId = 1L;
        Step updatedStep = new Step();
        // Set test properties for the updated step
        // ...

        stepService.updateStep(updatedStep, stepId);

        verify(stepRepositoryMock, times(1)).save(updatedStep);
    }
}
