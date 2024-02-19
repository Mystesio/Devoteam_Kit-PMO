package com.devoteam.pmo.test.entity;

import org.junit.jupiter.api.Test;

import com.devoteam.pmo.entity.Phase;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Date;

public class PhaseTest {

    @Test
    public void testPhaseId() {
        // Arrange
        Long phaseId = 1L;
        Phase phase = new Phase();
        phase.setPhaseId(phaseId);

        // Act & Assert
        assertEquals(phaseId, phase.getPhaseId());
    }

    @Test
    public void testPhaseName() {
        // Arrange
        String phaseName = "Phase One";
        Phase phase = new Phase();
        phase.setPhaseName(phaseName);

        // Act & Assert
        assertEquals(phaseName, phase.getPhaseName());
    }

    @Test
    public void testStartDate() {
        // Arrange
        Date startDate = new Date();
        Phase phase = new Phase();
        phase.setStartDate(startDate);

        // Act & Assert
        assertEquals(startDate, phase.getStartDate());
    }

    @Test
    public void testEndDate() {
        // Arrange
        Date endDate = new Date();
        Phase phase = new Phase();
        phase.setEndDate(endDate);

        // Act & Assert
        assertEquals(endDate, phase.getEndDate());
    }

    // Additional tests for other properties can be added here
}
