package com.example.service.portfolio.controller;

import com.example.service.portfolio.dto.PortfolioCreateRequest;
import com.example.service.portfolio.dto.PortfolioResponse;
import com.example.service.portfolio.dto.PortfolioUpdateRequest;
import com.example.service.portfolio.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public PortfolioResponse view(@PathVariable Long userId) {
        return portfolioService.getPortfolio(userId);
    }

    @GetMapping("/items/{id}")
    public PortfolioResponse detail(@PathVariable Long id) {
        return portfolioService.getPortfolioById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioResponse generate(@Valid @RequestBody PortfolioCreateRequest request) {
        return portfolioService.createOrUpdatePortfolio(request);
    }

    @PostMapping(params = {"userId", "title", "summary"})
    @ResponseStatus(HttpStatus.CREATED)
    public PortfolioResponse generateFromParams(
            @RequestParam Long userId,
            @RequestParam String title,
            @RequestParam String summary
    ) {
        return portfolioService.createOrUpdatePortfolio(new PortfolioCreateRequest(userId, title, summary));
    }

    @PutMapping("/items/{id}")
    public PortfolioResponse update(
            @PathVariable Long id,
            @Valid @RequestBody PortfolioUpdateRequest request
    ) {
        return portfolioService.updatePortfolio(id, request);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        portfolioService.deletePortfolio(id);
    }
}
