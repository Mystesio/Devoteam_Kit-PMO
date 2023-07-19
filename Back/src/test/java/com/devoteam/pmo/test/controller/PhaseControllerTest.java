package com.devoteam.pmo.test.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devoteam.pmo.controller.PhaseController;
import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.service.PhaseService;

public class PhaseControllerTest {

    @Mock
    private PhaseService phaseService;

    @InjectMocks
    private PhaseController phaseController;

    private MockMvc mockMvc;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(phaseController).build();
    }

    @Test
    public void testShowPhases() throws Exception {
        // Arrange
        long projectId = 1L;
        List<Phase> phases = new ArrayList<>();
        phases.add(new Phase());
        phases.add(new Phase());
        when(phaseService.showPhases(projectId)).thenReturn(phases);

        // Act & Assert
        mockMvc.perform(get("/" + projectId + "/phases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(phases.size()));
    }

    @Test
    public void testShowPhase() throws Exception {
        // Arrange
        long phaseId = 1L;
        Phase phase = new Phase();
        when(phaseService.showPhase(phaseId)).thenReturn(phase);

        // Act & Assert
        mockMvc.perform(get("/phase/" + phaseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phaseId").value(phaseId));
    }

    @Test
    public void testAddNewPhase() throws Exception {
        // Arrange
        long projectId = 1L;
        Phase phase = new Phase(); // Replace this with the actual phase object initialization
        when(phaseService.addNewPhase(projectId, phase)).thenReturn(phase);

        // Act & Assert
        mockMvc.perform(post("/" + projectId + "/addNewPhase")
                .contentType("application/json")
                .content("{ \"phaseId\": 0,\r\n"
                		+ "  \"startDate\": \"2023-07-19T09:22:20.318Z\",\r\n"
                		+ "  \"endDate\": \"2023-07-19T09:22:20.318Z\",\r\n"
                		+ "  \"phaseName\": \"phase unit\""
                		+ "}")) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phaseId").value(phase.getPhaseId()));
    }

    
 
    // Write similar tests for deletePhase and updatePhase methods if needed
}