package com.example.service.user.service;

import com.example.service.common.exception.CustomException;
import com.example.service.user.dto.UserCreateRequest;
import com.example.service.user.dto.UserResponse;
import com.example.service.user.dto.UserSearchRequest;
import com.example.service.user.dto.UserUpdateRequest;
import com.example.service.user.entity.User;
import com.example.service.user.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // [0] Pagination 적용으로 Full Table Scan 방지 (리뷰 반영)
    public Page<UserResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserResponse::from);
    }

    // [0] 검색 DTO(UserSearchRequest) 기반 이메일 검색 (리뷰 반영)
    public UserResponse search(UserSearchRequest condition) {
        String searchEmail = condition.normalizedEmail();
        return userRepository.findByEmail(searchEmail)
                .map(UserResponse::from)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }

    // [0] UNIQUE 제약 + 예외 기반 중복 이메일 처리 (리뷰 반영)
    @Transactional
    public UserResponse create(UserCreateRequest request) {
        // [리뷰 반영] DB UNIQUE 제약 조건과 연계한 동시성 보호
        try {
            User user = User.create(request.normalizedEmail(), request.normalizedDisplayName());
            return UserResponse.from(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new CustomException("Email already exists", HttpStatus.CONFLICT);
        }
    }

    /** ID로 사용자 조회. API/다른 서비스용 DTO 반환. */
    public UserResponse getById(Long id) {
        User user = getUserEntity(id);
        return UserResponse.from(user);
    }

    @Transactional
    public UserResponse update(Long id, UserUpdateRequest request) {
        User user = getUserEntity(id);
        user.changeDisplayName(request.normalizedDisplayName());
        return UserResponse.from(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = getUserEntity(id);
        userRepository.delete(user);
    }

    /** 같은 애플리케이션 내 다른 서비스에서 User 엔티티가 필요할 때만 사용. API는 getById 사용. */
    public User getUserEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
    }
}
