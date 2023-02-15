package com.second.world.javatest.data.remote;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class ImplRepository {

    RemoteDataSource mRemoteDataSource;

    @Inject
    public ImplRepository(RemoteDataSource remoteDataSource) {
        this.mRemoteDataSource = remoteDataSource;
    }

    public Observable<BaseResult<List<ItemFromServer>>> getData() {
        return mRemoteDataSource.getServerData()
                .map(listResponse -> {
                    if (listResponse.isSuccessful()) {
                        if (listResponse.body() != null) {
                            return BaseResult.SUCCESS(listResponse.body());
                        } else {
                            Log.d("TAG", "getData: " + "error in repository body");
                            return BaseResult.ERROR("body error");
                        }
                    } else {
                        Log.d("TAG", "getData: " + "error in repository");
                        return BaseResult.ERROR("error response");
                    }
                });
    }
}