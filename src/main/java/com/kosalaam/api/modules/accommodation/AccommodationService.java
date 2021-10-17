package com.kosalaam.api.modules.accommodation;

import com.kosalaam.api.common.UnauthorizedException;
import com.kosalaam.api.modules.accommodation.domain.*;
import com.kosalaam.api.modules.accommodation.dto.AccommodationDto;
import com.kosalaam.api.modules.accommodation.dto.AccommodationReviewRespDto;
import com.kosalaam.api.modules.accommodation.dto.AccommodationReviewSaveDto;
import com.kosalaam.api.modules.accommodation.dto.AccommodationReviewsRespDto;
import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import com.kosalaam.api.modules.place.dto.PlaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.kosalaam.api.common.ExceptionFunction.wrapper;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    private final AccommodationLikeRepository accommodationLikeRepository;

    private final AccommodationReviewRepository accommodationReviewRepository;

    private final KoUserRepository koUserRepository;

    /**
     * 숙소 정보 조회
     * @param id 숙소 ID
     * @param firebaseUuid Firebase UUID
     * @return 숙소 DTO
     * @throws Exception 존재하지 않는 숙소 ID
     */
    @Transactional
    public AccommodationDto getAccommodation(UUID id, String firebaseUuid) throws Exception {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 숙소 ID 입니다."
                ));

        return writeAccommodationDto(accommodation, firebaseUuid);

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
    public List<PlaceDto> getAccommodations(double latitude, double longitude, int distance, String keyword, Boolean isMuslimFriendly, int pageNum, int pageSize, String firebaseUuid) {

        if (keyword == null) { keyword = ""; }

//        String muslimFriendyFilter = "";
//        if (isMuslimFriendly != null) {
//            muslimFriendyFilter += "AND is_muslim_friendly=" + isMuslimFriendly.toString();
//        }

        List<Accommodation> accommodations = accommodationRepository.findByLocation(
                latitude,
                longitude,
                distance,
                keyword,
//                muslimFriendyFilter,
                PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "liked_count")
        ).getContent();

        return accommodations.stream()
                .map(wrapper(a -> writeAccommodationDto(a, firebaseUuid)))
                .map(r -> r.toLimitedImages())
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
        return writeAccommodationDto(accommodation, "");
    }

    /**
     * 숙소 정보 수정
     * @param id 숙소 ID
     * @param accommodationDto 숙소 DTO
     * @return 숙소 DTO
     * @throws Exception 존재하지 않는 숙소 ID
     */
    @Transactional
    public AccommodationDto updateAccommodation(UUID id, AccommodationDto accommodationDto) throws Exception {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 숙소 ID 입니다."
                ));

        // update
        accommodation.update(accommodationDto);
        return writeAccommodationDto(accommodation, "");
    }

    /**
     * 숙소 삭제
     * @param id 숙소 ID
     * @throws IllegalArgumentException 존재하지 않는 숙소 ID
     */
    @Transactional
    public void deleteAccommodation(UUID id) throws IllegalArgumentException{
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 숙소 ID 입니다."
                ));
        accommodationRepository.delete(accommodation);
    }

    /**
     * 좋아요 등록
     * @param accommodationId 숙소 ID
     * @param firebaseUuid Firebase UUID
     * @throws RuntimeException 존재하지 않는 숙소 ID or 사용자
     */
    @Transactional
    public void setLikeAccommodation(UUID accommodationId, String firebaseUuid) throws RuntimeException {
        // user
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));

        // accommodation
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+accommodationId+"' 는 존재하지 않는 숙소 ID 입니다."
                ));

        // like
        if (accommodationLikeRepository.findByKoUserIdAndAccommodationId(
                koUser.getId(), accommodationId
        ).isPresent()) {
            throw new IllegalArgumentException("이미 좋아요로 등록한 숙소입니다.");
        }
        AccommodationLike accommodationLike = new AccommodationLike(accommodationId, koUser.getId());
        accommodationLikeRepository.save(accommodationLike);

        // 좋아요 수 반영
        accommodation.setLikedCount(accommodation.getLikedCount() + 1);

    }

    /**
     * 좋아요 취소
     * @param accommodationId 숙소 ID
     * @param firebaseUuid Firebase UUID
     */
    public void deleteLikeAccommodation(UUID accommodationId, String firebaseUuid) {
        // user
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));

        // accommodation
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+accommodationId+"' 는 존재하지 않는 숙소 ID 입니다."
                ));

        // cancel like
        AccommodationLike accommodationLike = accommodationLikeRepository
                .findByKoUserIdAndAccommodationId(koUser.getId(), accommodationId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "좋아요로 등록되지 않은 숙소입니다."
                ));
        accommodationLikeRepository.delete(accommodationLike);

        // 좋아요 수 반영
        accommodation.setLikedCount(accommodation.getLikedCount() - 1);

    }

    /**
     * 숙소 리뷰 리스트 조회
     * @param id 숙소 ID
     * @return 숙소 리뷰 DTO
     */
    public AccommodationReviewsRespDto getAccommodationReviews(UUID id) {

        List<AccommodationReviewRespDto> accommodationReviewRespDtos = Optional.ofNullable(
                accommodationReviewRepository.findByAccommodationId(id))
                .orElseGet(ArrayList::new)
                .stream()
                .map(AccommodationReviewRespDto::new)
                .collect(Collectors.toList());

        return AccommodationReviewsRespDto.builder()
                .reviewCnt(accommodationReviewRespDtos.size())
                .reviews(accommodationReviewRespDtos)
                .build();
    }

    /**
     * 숙소 리뷰 등록
     * @param accommodationReviewSaveDto 리뷰 등록용 DTO
     * @param firebaseUuid Firebase UUID
     * @return 리뷰 ID
     * @throws Exception Auth Error
     */
    public Long saveAccommodationReview(AccommodationReviewSaveDto accommodationReviewSaveDto, String firebaseUuid) throws Exception {
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));
        AccommodationReview accommodationReview = accommodationReviewSaveDto.toEntity(koUser.getId());
        return accommodationReviewRepository.save(accommodationReview).getId();
    }

    /**
     * 숙소 리뷰 삭제
     * @param id 숙소 리뷰 ID
     * @param firebaseUuid Firebase UUID
     * @return 숙소 리뷰 ID
     * @throws Exception Auth Error
     */
    public Long deleteAccommodationReview(Long id, String firebaseUuid) throws Exception {
        AccommodationReview accommodationReview = accommodationReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 숙소 리뷰 ID 입니다."
                ));

        // 삭제 권한 확인
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));
        if (accommodationReview.getKoUserId().equals(koUser.getId())) {
            throw new UnauthorizedException("삭제 권한이 없는 식당 리뷰입니다.");
        }

        // 삭제
        accommodationReviewRepository.delete(accommodationReview);

        return id;
    }

    /**
     * Entity to DTO + 좋아요 상태 반영
     * @param accommodation 숙소 Entity
     * @param firebaseUuid Firebase UUID
     * @return 숙소 DTO
     * @throws Exception Auth 에러
     */
    public AccommodationDto writeAccommodationDto(Accommodation accommodation, String firebaseUuid) throws Exception {

        AccommodationDto accommodationDto = new AccommodationDto(accommodation);

        // 좋아요 체크
        if (!firebaseUuid.equals("")) {
            System.out.println(firebaseUuid);
            KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                    .orElseThrow(() -> new UnauthorizedException("존재하지 않는 사용자입니다."));

            if (accommodationLikeRepository.findByKoUserIdAndAccommodationId(koUser.getId(), accommodation.getId()).isPresent()) {
                accommodationDto.setIsLiked(Boolean.TRUE);
            }

        }

        return accommodationDto;

    }
}
