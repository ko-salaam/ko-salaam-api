package com.kosalaam.api.modules.prayerroom;

import com.kosalaam.api.common.UnauthorizedException;
import com.kosalaam.api.modules.kouser.domain.KoUser;
import com.kosalaam.api.modules.kouser.domain.KoUserRepository;
import com.kosalaam.api.modules.place.dto.PlaceDto;
import com.kosalaam.api.modules.prayerroom.domain.*;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomDto;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomReviewRespDto;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomReviewSaveDto;
import com.kosalaam.api.modules.prayerroom.dto.PrayerroomReviewsRespDto;
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
public class PrayerroomService {

    private final PrayerroomRepository prayerroomRepository;

    private final PrayerroomLikeRepository prayerroomLikeRepository;

    private final PrayerroomReviewRepository prayerroomReviewRepository;

    private final KoUserRepository koUserRepository;

    /**
     * 기도실 조회
     * @param id 기도실 ID
     * @param firebaseUuid Firebase UUID
     * @return 기도실 DTO
     * @throws IllegalArgumentException 존재하지 않는 기도실 ID or Auth 에러
     */
    @Transactional
    public PrayerroomDto getPrayerroom(UUID id, String firebaseUuid) throws IllegalArgumentException {

        Prayerroom prayerroom = prayerroomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 기도실 ID 입니다."
                ));

        return writePrayreroomDto(prayerroom, firebaseUuid);

    }

    /**
     * 기도실 리스트 조회
     * @param latitude 위도
     * @param longitude 경도
     * @param distance 반경 Nkm
     * @param keyword 검색 키워드
     * @param pageNum 페이지 번호
     * @param pageSize 페이지 사이즈
     * @param firebaseUuid firebase uuid
     * @return DTO 리스트
     */
    @Transactional
    public List<PlaceDto> getPrayerrooms(double latitude, double longitude, int distance, String keyword, int pageNum, int pageSize, String firebaseUuid) {

        if (keyword == null) { keyword = ""; }
        List<Prayerroom> prayerrooms = prayerroomRepository.findByLocation(
                latitude,
                longitude,
                distance,
                keyword,
                PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "liked_count")
        ).getContent();


        return prayerrooms.stream()
                .map(wrapper(r -> writePrayreroomDto(r, firebaseUuid)))
                .map(r -> r.toLimitedImages())
                .collect(Collectors.toList());

    }

    /**
     * 기도실 저장
     * @param prayerroomDto 기도실 DTO
     * @return 기도실 DTO
     */
    @Transactional
    public PrayerroomDto savePrayerroom(PrayerroomDto prayerroomDto) {
        Prayerroom prayerroom = prayerroomRepository.save(prayerroomDto.toEntity());
        return writePrayreroomDto(prayerroom, "");
    }

    /**
     * 기도실 정보 수정
     * @param id 기도실 ID
     * @param prayerroomDto 기도실 DTO
     * @return 기도실 DTO
     * @throws UnauthorizedException 존재하지 않는 기도실 ID
     */
    @Transactional
    public PrayerroomDto updatePrayerroom(UUID id, PrayerroomDto prayerroomDto) throws UnauthorizedException {
        Prayerroom prayerroom = prayerroomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 기도실 ID 입니다."
                ));

        // update
        prayerroom.update(prayerroomDto);
        return writePrayreroomDto(prayerroom, "");
    }

    /**
     * 기도실 삭제
     * @param id 기도실 ID
     * @throws IllegalArgumentException 존재하지 않는 기도실 ID
     */
    @Transactional
    public void deletePrayerroom(UUID id) throws IllegalArgumentException {
        Prayerroom Prayerroom = prayerroomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 기도실 ID 입니다."
                ));
        prayerroomRepository.delete(Prayerroom);
    }


    /**
     * 좋아요 등록
     * @param prayerroomId 기도실 ID
     * @param firebaseUuid Firebase UUID
     */
    @Transactional
    public void setLikePrayerroom(UUID prayerroomId, String firebaseUuid) {

        // user
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));

        // Prayerroom
        Prayerroom Prayerroom = prayerroomRepository.findById(prayerroomId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+prayerroomId+"' 는 존재하지 않는 기도실 ID 입니다."
                ));

        // like
        if (prayerroomLikeRepository.findByKoUserIdAndPrayerroomId(
                koUser.getId(), prayerroomId
        ).isPresent()) {
            throw new IllegalArgumentException("이미 좋아요로 등록한 기도실입니다.");
        }
        PrayerroomLike prayerroomLike = new PrayerroomLike(koUser.getId(), prayerroomId);
        prayerroomLikeRepository.save(prayerroomLike);

        // 좋아요 수 반영
        Prayerroom.setLikedCount(Prayerroom.getLikedCount() + 1);

    }

    /**
     * 좋아요 취소
     * @param prayerroomId 기도실 ID
     * @param firebaseUuid Firebase UUID
     * @throws RuntimeException 존재하지 않는 기도실 ID or Auth 에러
     */
    @Transactional
    public void deleteLikePrayerroom(UUID prayerroomId, String firebaseUuid) throws RuntimeException {

        // user
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));

        // Prayerroom
        Prayerroom prayerroom = prayerroomRepository.findById(prayerroomId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+prayerroomId+"' 는 존재하지 않는 기도실 ID 입니다."
                ));

        // cancel like
        PrayerroomLike prayerroomLike = prayerroomLikeRepository
                .findByKoUserIdAndPrayerroomId(koUser.getId(), prayerroomId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "좋아요로 등록되지 않은 기도실입니다."
                ));

        prayerroomLikeRepository.deleteById(prayerroomLike.getId());

        // 좋아요 수 반영
        prayerroom.setLikedCount(prayerroom.getLikedCount() - 1);
    }

    /**
     * 기도실 리뷰 리스트 조회
     * @param id 기도실 ID
     * @return PrayerroomReviewsDto 기도실 리뷰 DTO
     */
    @Transactional
    public PrayerroomReviewsRespDto getPrayerroomReviews(UUID id) {

        List<PrayerroomReviewRespDto> prayerroomReviewRespDtos = Optional.ofNullable(
                prayerroomReviewRepository.findByPrayerroomId(id)
        ).orElseGet(ArrayList::new)
                .stream()
                .map(PrayerroomReviewRespDto::new)
                .collect(Collectors.toList());

        return PrayerroomReviewsRespDto.builder()
                .reviewCnt(prayerroomReviewRespDtos.size())
                .reviews(prayerroomReviewRespDtos)
                .build();

    }

    /**
     * 기도실 리뷰 등록
     * @param prayerroomReviewSaveDto 리뷰 등록용 DTO
     * @return 기도실 리뷰 DTO
     * @throws UnauthorizedException Auth 에러
     */
    @Transactional
    public Long savePrayerroomReview(PrayerroomReviewSaveDto prayerroomReviewSaveDto, String firebaseUuid) throws UnauthorizedException {

        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));
        PrayerroomReview PrayerroomReview = prayerroomReviewSaveDto.toEntity(koUser.getId());
        return prayerroomReviewRepository.save(PrayerroomReview).getId();

    }

    /**
     * 기도실 리뷰 삭제
     * @param id 기도실 리뷰 ID
     * @return 기도실 리뷰 ID
     * @throws RuntimeException 존재하지 않는 기도실 리뷰 ID
     */
    @Transactional
    public Long deletePrayerroomReview(Long id, String firebaseUuid) throws RuntimeException {

        PrayerroomReview prayerroomReview = prayerroomReviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "존재하지 않는 기도실 리뷰 ID 입니다."
                ));

        // 삭제 권한 확인
        KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                .orElseThrow(() -> new UnauthorizedException(
                        "존재하지 않는 사용자입니다."
                ));
        if (prayerroomReview.getKoUserId().equals(koUser.getId())) {
            throw new UnauthorizedException("삭제 권한이 없는 기도실 리뷰입니다.");
        }

        // 삭제
        prayerroomReviewRepository.delete(prayerroomReview);

        return id;
    }

    /**
     * Prayerroom 엔티티를 DTO 로 변환하고 좋아요 상태를 반영
     * @param prayerroom 기도실 Entity
     * @param firebaseUuid Firebase UUID
     * @return 기도실 DTO
     * @throws UnauthorizedException Auth 에러
     */
    public PrayerroomDto writePrayreroomDto(Prayerroom prayerroom, String firebaseUuid) throws UnauthorizedException {

        PrayerroomDto prayerroomDto = new PrayerroomDto(prayerroom);

        // 좋아요 체크
        if (!firebaseUuid.equals("")) {
            KoUser koUser = koUserRepository.findByFirebaseUuid(firebaseUuid)
                    .orElseThrow(() -> new UnauthorizedException("존재하지 않는 사용자입니다."));

            if (prayerroomLikeRepository.findByKoUserIdAndPrayerroomId(koUser.getId(), prayerroom.getId()).isPresent()) {
                prayerroomDto.setIsLiked(Boolean.TRUE);
            }
        }

        return prayerroomDto;
    }

}
