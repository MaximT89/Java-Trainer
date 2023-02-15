package com.second.world.javatest.ui.main_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.second.world.javatest.R;
import com.second.world.javatest.core.App;
import com.second.world.javatest.data.remote.BaseResult;
import com.second.world.javatest.data.remote.ImplRepository;
import com.second.world.javatest.data.remote.ItemFromServer;
import com.second.world.javatest.databinding.ActivityMainBinding;
import com.second.world.javatest.ui.screens.first_screen.FirstFragment;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    ImplRepository repository;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private ActivityMainBinding binding;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ((App) getApplication()).getAppComponent().inject(this);

        Bundle bundle = new Bundle();

        Fragment fragment = new FirstFragment();
        fragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();

        Observable<Integer> observable = Observable.just(5,6,7,8,9);
        observable.startWith(10)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Integer integer) {
                        Log.d("TAG", "onNext: " + integer.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        mDisposable.add(repository.getData()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(this::handleLoading)
//                .subscribe(this::handleResponse, this::handleError, this::onComplete));
    }

    private void handleLoading(Observer<? super BaseResult<List<ItemFromServer>>> observer) {
        Log.d("TAG", "handleLoading:");
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void handleLoading(Disposable disposable) {
        Log.d("TAG", "handleLoading:");
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void onComplete() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void handleError(Throwable throwable) {
        binding.progressBar.setVisibility(View.GONE);
        Log.d("TAG", "handleError: " + "error in activity");

    }

    private void handleResponse(BaseResult<List<ItemFromServer>> listBaseResult) {
        binding.progressBar.setVisibility(View.GONE);
        for (int i = 0; i < listBaseResult.getData().size(); i++) {
            Log.d("TAG", "handleResponse: " + listBaseResult.getData().get(i).getName());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDisposable.clear();
    }
}