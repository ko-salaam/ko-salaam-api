package com.kosalaam.api.modules.kouser;

import com.kosalaam.api.utils.AuthUtils;
import org.springframework.stereotype.Service;

@Service
public class KoUserService {

    public void getUser() throws Exception {
        AuthUtils.initFireBaseSDK();
    }
}
