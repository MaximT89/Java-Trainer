package com.second.world.javatest.data.remote;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/comments")
    Observable<Response<List<ItemFromServer>>> getServerData();
}
