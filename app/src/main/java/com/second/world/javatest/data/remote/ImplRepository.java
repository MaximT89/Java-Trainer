package com.second.world.javatest.data.remote;

import android.util.Log;

import com.second.world.javatest.core.base_result.BaseResult;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ImplRepository {

    RemoteDataSource mRemoteDataSource;

    @Inject
    public ImplRepository(RemoteDataSource remoteDataSource) {
        this.mRemoteDataSource = remoteDataSource;
    }

    public Observable<BaseResult<Object>> getData() {
        return mRemoteDataSource.getServerData()
                .map(listResponse -> {
                    if (listResponse.isSuccessful()) {
                        if (listResponse.body() != null) {
                            return new BaseResult.Success<>(listResponse.body());
                        } else {
                            Log.d("TAG", "getData: " + "error in repository body");
                            return new BaseResult.Error<>("body error");
                        }
                    } else {
                        Log.d("TAG", "getData: " + "error in repository");
                        return new BaseResult.Error<>("body error");
                    }
                });
    }
}