package com.kosalaam.api.modules.file;


import com.kosalaam.api.modules.prayerroom.domain.PrayerroomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RequestMapping("/uploads")
@RestController
public class FileController {

    @GetMapping("{uuid}")
    public ResponseEntity<PlaceDto> getFileSource(
            @ApiParam(value="장소 ID") @PathVariable UUID id,
            @ApiIgnore @RequestAttribute(value="firebaseUuid") String firebaseUuid
    ) throws Exception {
        return new ResponseEntity<>(placeService.getPlace(id, firebaseUuid), HttpStatus.OK);
    }

    @GetMapping("{uuid.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
