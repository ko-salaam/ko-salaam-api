package com.kosalaam.api.modules.common;

import com.kosalaam.api.modules.common.dto.CommonDto;
import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import com.kosalaam.api.modules.restaurant.domain.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommonService {

    private final RestaurantRepository restaurantRepository;

    /**
     * 장소 리스트 통합 검색
     * @param latitude 위도
     * @param longitude 경도
     * @param distance 반경 Nkm
     * @param keyword 검색 keyword
     * @param pageNum 페이지 번호
     * @param pageSize 페이지 사이즈
     * @param firebaseUuid Firebase UUID
     * @return
     * @throws Exception
     */
//    @Transactional
//    public List<CommonDto> getCommons(double latitude, double longitude, int distance, String keyword, int pageNum, int pageSize, String firebaseUuid) throws Exception {
//
//        List<Restaurant> restaurants = restaurantRepository.findByLocation(
//                latitude,
//                longitude,
//                distance,
//                keyword,
//                PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, "liked_count")
//        ).getContent();
//    }
}
