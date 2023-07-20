package com.devoteam.pmo.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

import com.devoteam.pmo.controller.StepController;
import com.devoteam.pmo.entity.Step;
import com.devoteam.pmo.service.StepService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StepControllerTest {

    @Mock
    private StepService stepService;

    @InjectMocks
    private StepController stepController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stepController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testShowSteps() throws Exception {
        // Arrange
        long phaseId = 1;
        List<Step> steps = new ArrayList<>();
        steps.add(new Step());
        steps.add(new Step());
        when(stepService.showSteps(phaseId)).thenReturn(steps);

        // Act & Assert
        mockMvc.perform(get("/" + phaseId + "/steps"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(steps.size()));
    }

    @Test
    public void testShowStep() throws Exception {
        // Arrange
        long stepId = 1;
        Step step = new Step();
        when(stepService.showStep(stepId)).thenReturn(step);

        // Act & Assert
        mockMvc.perform(get("/step/" + stepId))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddNewStep() throws Exception {
        // Arrange
        long phaseId = 1L;
        Step step = new Step(); // Replace this with the actual Step object initialization
        when(stepService.addNewStep(phaseId, step)).thenReturn(step);

        // Act & Assert
        mockMvc.perform(post("/" + phaseId + "/addNewStep")
                .contentType("application/json")
                .content("{\r\n"
                        + "  \"stepId\": 0,\r\n"
                        + "  \"stepName\": \"Step Name\"\r\n"
                        + "}\r\n"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteStep() throws Exception {
        // Arrange
        long stepId = 1L;

        // Act & Assert
        mockMvc.perform(delete("/step/" + stepId + "/delete"))
                .andExpect(status().isOk());

        // Verify that the deleteStep method of the StepService is called once with the correct stepId
        verify(stepService, times(1)).deleteStep(stepId);
    }

    @Test
    public void testUpdateStep() throws Exception {
        long stepId = 1L;
        Step updatedStep = new Step();
        updatedStep.setStepId(stepId);
        updatedStep.setStepname("Step Update");

        // Set up the mock stepService
        Mockito.doNothing().when(stepService).updateStep(any(Step.class), eq(stepId));

        // Act & Assert
        mockMvc.perform(put("/step/" + stepId + "/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updatedStep)))
                .andExpect(status().isOk());

        // Verify that the updateStep method of the StepService is called once with the correct step and stepId
        ArgumentCaptor<Step> stepCaptor = ArgumentCaptor.forClass(Step.class);
        verify(stepService, times(1)).updateStep(stepCaptor.capture(), eq(stepId));

        // Compare the properties of the captured Step with the expected properties
        Step capturedStep = stepCaptor.getValue();
        assertEquals(updatedStep.getStepname(), capturedStep.getStepname());
    }
}

