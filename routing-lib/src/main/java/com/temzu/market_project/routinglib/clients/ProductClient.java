package com.temzu.market_project.routinglib.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("ms-product")
public interface ProductClient {
}
