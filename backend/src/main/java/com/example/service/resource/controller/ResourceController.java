package com.example.service.resource.controller;

import com.example.service.resource.dto.ResourceCreateRequest;
import com.example.service.resource.dto.ResourceResponse;
import com.example.service.resource.dto.ResourceUpdateRequest;
import com.example.service.resource.service.ResourceService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping
    public List<ResourceResponse> listAll() {
        return resourceService.getAllResources();
    }

    @GetMapping("/search")
    public List<ResourceResponse> listByCategory(@RequestParam String category) {
        return resourceService.getResourcesByCategory(category);
    }

    @GetMapping("/{id}")
    public ResourceResponse detail(@PathVariable Long id) {
        return resourceService.getResource(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceResponse create(@Valid @RequestBody ResourceCreateRequest request) {
        return resourceService.addResource(request);
    }

    @PutMapping("/{id}")
    public ResourceResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ResourceUpdateRequest request
    ) {
        return resourceService.updateResource(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        resourceService.deleteResource(id);
    }
}
