package com.kosalaam.api.modules.prayerroom.dto;

import com.kosalaam.api.modules.place.dto.PlaceDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@ApiModel
public class PrayerroomSaveDto extends PrayerroomDto {

    private List<MultipartFile> images;

}
