package com.temzu.market_project.routinglib.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ms-auth")
public interface AuthClient {
}
