package com.kosalaam.api.modules.file;

import com.google.common.net.HttpHeaders;
import com.kosalaam.api.common.StorageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "File")
@RequestMapping("/uploads")
@RestController
public class FileController {

    @GetMapping("{folder}/{filename}")
    public ResponseEntity<Resource> getFile(
            @PathVariable String folder,
            @PathVariable String filename
    ) throws Exception {
        Resource file = StorageUtils.load(folder + "/" + filename);
        System.out.println(file);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "a")
                .body(file);

    }
}
