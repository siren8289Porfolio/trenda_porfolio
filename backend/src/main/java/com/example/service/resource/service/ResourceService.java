package com.example.service.resource.service;

import com.example.service.common.exception.CustomException;
import com.example.service.resource.dto.ResourceCreateRequest;
import com.example.service.resource.dto.ResourceResponse;
import com.example.service.resource.dto.ResourceUpdateRequest;
import com.example.service.resource.entity.Resource;
import com.example.service.resource.repository.ResourceRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    public ResourceResponse getResource(Long id) {
        return ResourceResponse.from(getResourceEntity(id));
    }

    @Transactional
    public ResourceResponse addResource(ResourceCreateRequest request) {
        Resource resource = new Resource(
                request.getTitle().trim(),
                request.getUrl().trim(),
                request.getCategory().trim(),
                request.getDescription()
        );
        return ResourceResponse.from(resourceRepository.save(resource));
    }

    @Transactional
    public ResourceResponse updateResource(Long id, ResourceUpdateRequest request) {
        Resource resource = getResourceEntity(id);
        resource.update(
                request.getTitle().trim(),
                request.getUrl().trim(),
                request.getCategory().trim(),
                request.getDescription()
        );
        return ResourceResponse.from(resource);
    }

    @Transactional
    public void deleteResource(Long id) {
        resourceRepository.delete(getResourceEntity(id));
    }

    private Resource getResourceEntity(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new CustomException("RESOURCE_NOT_FOUND", "Resource not found", HttpStatus.NOT_FOUND));
    }
}
