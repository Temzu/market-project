package com.temzu.market_project.mscore.interfaces;

import com.temzu.market_project.mscore.model.UserInfo;

public interface ITokenService {

    String generateToken(UserInfo user);

    UserInfo parseToken(String token);
}
