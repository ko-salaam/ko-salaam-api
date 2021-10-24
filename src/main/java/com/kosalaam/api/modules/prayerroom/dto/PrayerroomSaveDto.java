package com.kosalaam.api.modules.prayerroom.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosalaam.api.common.StorageUtils;
import com.kosalaam.api.modules.place.domain.PraySupplies;
import com.kosalaam.api.modules.place.dto.PlaceDto;
import com.kosalaam.api.modules.prayerroom.domain.Prayerroom;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//@Getter
@AllArgsConstructor
//@ApiModel
public class PrayerroomSaveDto extends PlaceDto {


//    @ApiModelProperty(notes = "코살람 등록 기도실 여부", position = 2)
    public Boolean isKosalaamRoom;

//    @ApiModelProperty(notes = "가격", position = 14)
    public Integer price;

//    @ApiModelProperty(notes = "이미지 리스트")
//    private List<MultipartFile> imageFiles;

    @ApiModelProperty(notes = "기도 물품 구비 여부")
    private String praySupplies;

    @ApiModelProperty(notes = "기도실 운영 타입", position = 14)
    public String managingType;

    @ApiModelProperty(notes = "호스트 ID", position = 16)
    public Long hostId;




    public String[] saveImages(List<MultipartFile> imageFiles) throws Exception {

        if (imageFiles == null) {
            return new String[]{};
        }
        return StorageUtils.save(imageFiles);
//        List<String> imagePaths = new ArrayList<String>();
//        for (MultipartFile image : imageFiles) {
////            String orgName = image.getOriginalFilename();
////            String filePath = uploadPath + orgName;
////            File dest = new File(filePath);
////            image.transferTo(dest);
////            imagePaths.add(filePath);
//            imagePaths.add(StorageUtils.save(image));
//        }

//        return imagePaths.toArray(new String[imagePaths.size()]);
    }

    public Prayerroom toEntity(List<MultipartFile> imageFiles) throws Exception {

        final ObjectMapper mapper = new ObjectMapper();
        PraySupplies newPraySupplies = mapper.readValue(praySupplies, PraySupplies.class);

        return Prayerroom.builder()
                .isKosalaamRoom(isKosalaamRoom)
                .name(name)
                .latitude(latitude)
                .longitude(longitude)
                .address(address)
                .phoneNumber(phoneNumber)
                .images(saveImages(imageFiles))
                .likedCount(likedCount)
                .openingHours(openingHours)
                .isParkingLot(isParkingLot)
                .managingType(managingType)
                .isKoran(newPraySupplies.getIsKoran())
                .isMat(newPraySupplies.getIsMat())
                .isQibla(newPraySupplies.getIsQibla())
                .isWashingRoom(newPraySupplies.getIsWashingRoom())
                .hostId(hostId)
                .detailInfo(detailInfo)
                .build();
    }
}
