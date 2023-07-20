package com.devoteam.pmo.test.controller;
import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devoteam.pmo.controller.PhaseController;
import com.devoteam.pmo.entity.Phase;
import com.devoteam.pmo.repository.PhaseRepository;
import com.devoteam.pmo.service.PhaseService;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PhaseControllerTest {

    @Mock
    private PhaseService phaseService;
    
    
    @Mock
    private PhaseRepository phaseRepository;

    @InjectMocks
    private PhaseController phaseController;

    private MockMvc mockMvc;
    
    private ObjectMapper objectMapper;
       
    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(phaseController).build();
        objectMapper = new ObjectMapper();
   
    }

    @Test
    public void testShowPhases() throws Exception {
        // Arrange
        long projectId = 1;
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
        long phaseId = 1;
        Phase phase = new Phase();
        when(phaseService.showPhase(phaseId)).thenReturn(phase);

        // Act & Assert
        mockMvc.perform(get("/phase/" + phaseId))
                .andExpect(status().isOk());
             
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
                .content("{\r\n"
                		+ "  \"phaseId\": 0,\r\n"
                		+ "  \"startDate\": \"2023-07-19T09:22:20.318Z\",\r\n"
                		+ "  \"endDate\": \"2023-07-19T09:22:20.318Z\",\r\n"
                		+ "  \"phaseName\": \"Phase Name\"\r\n"
                		+ "}\r\n")) 
                .andExpect(status().isOk());
    }
    
    @Test
    public void testDeletePhase() throws Exception {
        // Arrange
        long phaseId = 1L;
        
        // Act & Assert
        mockMvc.perform(delete("/phase/" + phaseId + "/delete"))
                .andExpect(status().isOk());
                
        // Verify that the deletePhase method of the PhaseService is called once with the correct phaseId
        verify(phaseService, times(1)).deletePhase(phaseId);
    }
    
    @Test
    public void testUpdatePhase() throws Exception {
        long phaseId = 1L;
        Phase updatedPhase = new Phase();
        updatedPhase.setPhaseId(phaseId);
        updatedPhase.setPhaseName("phase Update");
        updatedPhase.setStartDate(new Date());
        updatedPhase.setEndDate(new Date());

        // Set up the mock phaseService
        Mockito.doNothing().when(phaseService).update(any(Phase.class), eq(phaseId));

        // Act & Assert
        mockMvc.perform(put("/phase/" + phaseId + "/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updatedPhase)))
                .andExpect(status().isOk());

        // Verify that the update method of the PhaseService is called once with the correct phase and phaseId
        ArgumentCaptor<Phase> phaseCaptor = ArgumentCaptor.forClass(Phase.class);
        verify(phaseService, times(1)).update(phaseCaptor.capture(), eq(phaseId));

        // Compare the properties of the captured Phase with the expected properties
        Phase capturedPhase = phaseCaptor.getValue();
        assertEquals(updatedPhase.getPhaseName(), capturedPhase.getPhaseName());
        assertEquals(updatedPhase.getStartDate(), capturedPhase.getStartDate());
        assertEquals(updatedPhase.getEndDate(), capturedPhase.getEndDate());
     }

  }
    


