package com.temzu.market_project.mscore.interfaces;



import com.temzu.market_project.mscore.entities.UserInfo;

import java.util.Date;

public interface ITokenService {

    String generateToken(UserInfo user);

    UserInfo parseToken(String token);

    Date getExpirationDate(String token);
}
