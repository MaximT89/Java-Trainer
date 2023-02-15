package com.second.world.javatest.data.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class RemoteDataSource {

    ApiService mApiService;

    @Inject
    public RemoteDataSource(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<Response<List<ItemFromServer>>> getServerData() {

        return mApiService.getServerData();

    }
}