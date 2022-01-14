package com.kosalaam.api.modules.place;

import com.kosalaam.api.modules.accommodation.AccommodationService;
import com.kosalaam.api.modules.accommodation.domain.Accommodation;
import com.kosalaam.api.modules.accommodation.domain.AccommodationRepository;
import com.kosalaam.api.modules.place.dto.PlaceDto;
import com.kosalaam.api.modules.prayerroom.PrayerroomService;
import com.kosalaam.api.modules.prayerroom.domain.Prayerroom;
import com.kosalaam.api.modules.prayerroom.domain.PrayerroomRepository;
import com.kosalaam.api.modules.restaurant.RestaurantService;
import com.kosalaam.api.modules.restaurant.domain.MuslimFriendlies;
import com.kosalaam.api.modules.restaurant.domain.Restaurant;
import com.kosalaam.api.modules.restaurant.domain.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PrayerroomService prayerroomService;

    private final RestaurantService restaurantService;

    private final AccommodationService accommodationService;

    private final PrayerroomRepository prayerroomRepository;

    private final RestaurantRepository restaurantRepository;

    private final AccommodationRepository accommodationRepository;


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
    @Transactional
    public List<PlaceDto> getPlaces(double latitude, double longitude, int distance, String keyword, Boolean isMuslimFriendly, int pageNum, int pageSize, String firebaseUuid) throws Exception {

        List<PlaceDto> placeDtos = prayerroomService.getPrayerrooms(
                latitude, longitude, distance, keyword, pageNum, pageSize, firebaseUuid
        );

        List<String> muslimFriendlies = new ArrayList(
                List.of(MuslimFriendlies.MUSLIM_FRIENDLY,
                        MuslimFriendlies.HALAL_CERTIFIED,
                        MuslimFriendlies.SELF_CERTIFIED,
                        MuslimFriendlies.FORK_FREE));
        placeDtos.addAll(restaurantService.getRestaurants(
                latitude, longitude, distance, keyword, muslimFriendlies, pageNum, pageSize, firebaseUuid
        ));

        placeDtos.addAll(accommodationService.getAccommodations(
                latitude, longitude, distance, keyword, isMuslimFriendly, pageNum, pageSize, firebaseUuid
        ));

        placeDtos.stream()
                .map(p -> p.toLimitedImages())
                .collect(Collectors.toList());

        Collections.sort(placeDtos, Comparator.comparing(PlaceDto::getLikedCount));
        return placeDtos;
    }

    /**
     * 장소 조회
     * @param id 장소 ID
     * @param firebaseUuid Firebase UUID
     * @return 장소 DTO
     * @throws Exception 존재하지 않는 ID
     */
    public PlaceDto getPlace(UUID id, String firebaseUuid) throws Exception {

        Prayerroom prayerroom = prayerroomRepository.findById(id)
                .orElseGet(null);

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseGet(null);

        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseGet(null);

        if (prayerroom != null) {
            return prayerroomService.writePrayreroomDto(prayerroom, firebaseUuid);
        } else if (restaurant != null) {
            return restaurantService.writeRestaurantDto(restaurant, firebaseUuid);
        } else if (accommodation != null) {
            return accommodationService.writeAccommodationDto(accommodation, firebaseUuid);
        } else {
            throw new IllegalArgumentException("'"+id+"' 는 존재하지 않는 기도실 ID 입니다.");
        }
    }

}
