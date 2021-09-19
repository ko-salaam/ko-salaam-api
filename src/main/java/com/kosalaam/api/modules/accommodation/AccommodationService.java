package com.kosalaam.api.modules.accommodation;

import com.kosalaam.api.modules.accommodation.domain.Accommodation;
import com.kosalaam.api.modules.accommodation.domain.AccommodationRepository;
import com.kosalaam.api.modules.accommodation.dto.AccommodationRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    @Transactional
    public AccommodationRespDto getAccommodation(Long id) throws Exception {
        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'"+id+"' 는 존재하지 않는 숙소 ID 입니다."
                ));

        return new AccommodationRespDto(accommodation);

    }
}
