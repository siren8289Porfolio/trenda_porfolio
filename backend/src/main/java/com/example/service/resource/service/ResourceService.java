package com.example.service.resource.service;

import com.example.service.resource.dto.ResourceCreateRequest;
import com.example.service.resource.dto.ResourceResponse;
import com.example.service.resource.entity.Resource;
import com.example.service.resource.repository.ResourceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public List<ResourceResponse> getAllResources() {
        return resourceRepository.findAll()
                .stream()
                .map(ResourceResponse::from)
                .toList();
    }

    public List<ResourceResponse> getResourcesByCategory(String category) {
        return resourceRepository.findByCategory(category)
                .stream()
                .map(ResourceResponse::from)
                .toList();
    }

    public ResourceResponse addResource(ResourceCreateRequest request) {
        Resource resource = new Resource(
                request.getTitle().trim(),
                request.getUrl().trim(),
                request.getCategory().trim(),
                request.getDescription()
        );
        return ResourceResponse.from(resourceRepository.save(resource));
    }
}
