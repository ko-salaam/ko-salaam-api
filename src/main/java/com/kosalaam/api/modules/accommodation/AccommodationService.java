package com.kosalaam.api.modules.accommodation;

import com.kosalaam.api.common.UnauthorizedException;
import com.kosalaam.api.modules.accommodation.domain.Accommodation;
import com.kosalaam.api.modules.accommodation.domain.AccommodationRepository;
import com.kosalaam.api.modules.accommodation.dto.AccommodationDto;
import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.kosalaam.api.common.ExceptionFunction.wrapper;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    private final KoUserRepository koUserRepository;

    /**
     * 숙소 정보 조회
     * @param id 숙소 ID
     * @param firebaseUuid Firebase UUID
     * @return 숙소 DTO
     * @throws Exception 존재하지 않는 숙소 ID
     */
    @Transactional
    public AccommodationDto getAccommodation(Long id, String firebaseUuid) throws Exception {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 숙소 ID 입니다."
                ));

        return writeDto(accommodation, firebaseUuid);

    }

    /**
     * 숙소 리스트 조회
     * @param latitude 위도
     * @param longitude 경도
     * @param distance 반경 Nkm
     * @param keyword 검색 키워드
     * @param pageNum 페이지 번호
     * @param pageSize 페이지 사이즈
     * @param firebaseUuid Firebase UUID
     * @return DTO 리스트
     */
    @Transactional
    public List<AccommodationDto> getAccommodations(double latitude, double longitude, int distance, String keyword, int pageNum, int pageSize, String firebaseUuid) {

        List<Accommodation> accommodations = accommodationRepository.findByLocation(
                latitude,
                longitude,
                distance,
                keyword,
                PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "liked_count")
        ).getContent();

        return accommodations.stream()
                .map(wrapper(a -> writeDto(a, firebaseUuid)))
                .collect(Collectors.toList());
    }

    /**
     * 숙소 저장
     * @param accommodationDto 숙소 DTO
     * @return 숙소 DTO
     * @throws Exception Auth 에러
     */
    @Transactional
    public AccommodationDto saveAccommodation(AccommodationDto accommodationDto) throws Exception {
        Accommodation accommodation = accommodationRepository.save(accommodationDto.toEntity());
        return writeDto(accommodation, "");
    }

    /**
     * 숙소 정보 수정
     * @param id 숙소 ID
     * @param accommodationDto 숙소 DTO
     * @return 숙소 DTO
     * @throws Exception 존재하지 않는 숙소 ID
     */
    @Transactional
    public AccommodationDto updateAccommodation(Long id, AccommodationDto accommodationDto) throws Exception {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 숙소 ID 입니다."
                ));

        // update
        accommodation.update(accommodationDto);
        return writeDto(accommodation, "");
    }


    /**
     * Entity to DTO + 좋아요 상태 반영
     * @param accommodation 숙소 Entity
     * @param firebaseUuid Firebase UUID
     * @return 숙소 DTO
     * @throws Exception Auth 에러
     */
    private AccommodationDto writeDto(Accommodation accommodation, String firebaseUuid) throws Exception {

        AccommodationDto accommodationDto = new AccommodationDto(accommodation);

        // 좋아요 체크
        if (firebaseUuid.equals("")) {
            KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                    .orElseThrow(() -> new UnauthorizedException("존재하지 않는 사용자입니다."));
        }

        return accommodationDto;

    }
}
