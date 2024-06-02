package fr.dreamteam.gateaway.service;

import dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Service
public class WorkflowService {

    private final WebClient webClient;
    private final UserService userService;
    private final HttpServletRequest httpServletRequest;

    public WorkflowService(UserService userService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
        webClient = WebClient.create();
    }

    public void buyCard(Long cardId) {

        String sessionId = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> cookie.getName().equals("sessionId"))
                .findFirst().orElseThrow(() -> new RuntimeException("No session cookie found")).getValue();
        UserDto currentUser = this.userService.getUser(sessionId);

        webClient.post().uri("http://localhost:8085/engine-rest/process-definition/key/Process_12scg7m/start")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(String.format("""
                        {
                        "variables": {
                        "userId": {
                            "value": "%d",
                        "type": "Long"
                        },
                        "amount": {
                            "value": 1000.0,
                        "type": "Double"
                        },
                           "cardId": {
                        "value": "%d",
                        "type": "Long"
                        }
                        }
                        }
                        """, currentUser.id(), cardId)).retrieve().bodyToMono(Void.class).block();
    }
}
