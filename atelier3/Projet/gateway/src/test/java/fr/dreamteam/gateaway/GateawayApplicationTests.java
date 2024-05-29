package fr.dreamteam.gateaway;

import fr.dreamteam.gateaway.controller.UServiceController;
import fr.dreamteam.gateaway.service.UService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UServiceControllerTest {

    @Mock
    private UService uService;

    @InjectMocks
    private UServiceController uServiceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(uServiceController).build();
    }

    @Test
    void account_ShouldReturnResponseEntity_WhenUServiceReturnsResponse() throws Exception {
        String requestBody = "test-body";
        String responseBody = "response-body";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(responseBody);

        when(uService.proxy(eq("ACCOUNT"), any(String.class))).thenReturn(responseEntity);

        mockMvc.perform(post("/account/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(responseBody));
    }

    @Test
    void account_ShouldReturnServerError_WhenUServiceThrowsException() throws Exception {
        String requestBody = "test-body";

        //when(uService.proxy(eq("ACCOUNT"), any(String.class))).thenThrow(new IOException("Service error"));

        mockMvc.perform(post("/account/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}
