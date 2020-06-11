package com.timo.dream.bean.api;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 蔡永汪 on 2020/3/31.
 */

public interface GetDataService {
    @GET("/timo/game/auth")
    Observable<String> getData();
}
