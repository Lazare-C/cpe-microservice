package fr.dreamteam.gateaway.service;

import com.netflix.discovery.EurekaClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UService {

    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;
    private final WebClient webClient;
    private final EurekaClient eurekaClient;

    public UService(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, EurekaClient eurekaClient) {
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.eurekaClient = eurekaClient;
        this.webClient = WebClient.create();
    }

    public ResponseEntity<String> proxy(String service, String body) {

        String requestUri = this.getEurekaServiceUrl(service) + httpServletRequest.getRequestURI()
                .substring(httpServletRequest.getRequestURI().indexOf("/", 1));
        HttpMethod method = HttpMethod.valueOf(httpServletRequest.getMethod());
        HttpHeaders headers = new HttpHeaders();
        httpServletRequest.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> headers.add(headerName, httpServletRequest.getHeader(headerName)));
        headers.remove(HttpHeaders.HOST);
        headers.remove(HttpHeaders.CONTENT_LENGTH);

        ResponseEntity<String> block = webClient.method(method).uri(requestUri)
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(body)
                .retrieve()
                .toEntity(String.class).block();
        if (block != null && block.getHeaders().getContentType() != null) {
            httpServletResponse.setContentType(block.getHeaders().getContentType().toString());
        }
        return block;
    }

    public String getEurekaServiceUrl(String service) {
        if (eurekaClient.getApplication(service).getInstances().isEmpty()) {
            throw new RuntimeException("Service not found");
        }
        return eurekaClient.getApplication(service).getInstances().get(0).getHomePageUrl();
    }


}
