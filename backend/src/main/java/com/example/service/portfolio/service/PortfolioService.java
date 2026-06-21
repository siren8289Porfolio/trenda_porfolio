package com.example.service.portfolio.service;

import com.example.service.common.exception.CustomException;
import com.example.service.portfolio.dto.PortfolioCreateRequest;
import com.example.service.portfolio.dto.PortfolioResponse;
import com.example.service.portfolio.dto.PortfolioUpdateRequest;
import com.example.service.portfolio.entity.Portfolio;
import com.example.service.portfolio.repository.PortfolioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public List<PortfolioResponse> findByUser(Long userId) {
        return portfolioRepository.findByUserId(userId)
                .stream()
                .map(PortfolioResponse::from)
                .toList();
    }

    public PortfolioResponse getPortfolio(Long userId) {
        Portfolio portfolio = portfolioRepository.findTopByUserIdOrderByCreatedAtDesc(userId)
                .orElseThrow(() -> new CustomException("PORTFOLIO_NOT_FOUND", "포트폴리오가 아직 생성되지 않았습니다.", HttpStatus.NOT_FOUND));
        return PortfolioResponse.from(portfolio);
    }

    public PortfolioResponse getPortfolioById(Long id) {
        return PortfolioResponse.from(getPortfolioEntity(id));
    }

    public PortfolioResponse createOrUpdatePortfolio(PortfolioCreateRequest request) {
        Portfolio portfolio = portfolioRepository.findTopByUserIdOrderByCreatedAtDesc(request.getUserId())
                .orElseGet(() -> new Portfolio(request.getUserId(), request.getTitle().trim(), request.getSummary().trim()));

        portfolio.update(request.getTitle().trim(), request.getSummary().trim());
        return PortfolioResponse.from(portfolioRepository.save(portfolio));
    }

    @Transactional
    public PortfolioResponse updatePortfolio(Long id, PortfolioUpdateRequest request) {
        Portfolio portfolio = getPortfolioEntity(id);
        portfolio.update(request.getTitle().trim(), request.getSummary().trim());
        return PortfolioResponse.from(portfolio);
    }

    @Transactional
    public void deletePortfolio(Long id) {
        portfolioRepository.delete(getPortfolioEntity(id));
    }

    private Portfolio getPortfolioEntity(Long id) {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new CustomException("PORTFOLIO_NOT_FOUND", "Portfolio not found", HttpStatus.NOT_FOUND));
    }
}
