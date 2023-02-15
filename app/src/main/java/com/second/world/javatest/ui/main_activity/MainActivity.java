package com.second.world.javatest.ui.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.second.world.javatest.R;
import com.second.world.javatest.core.App;
import com.second.world.javatest.core.base_result.BaseResult;
import com.second.world.javatest.data.remote.ImplRepository;
import com.second.world.javatest.data.remote.ItemFromServer;
import com.second.world.javatest.databinding.ActivityMainBinding;
import com.second.world.javatest.ui.screens.first_screen.FirstFragment;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

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

//        BaseResult<ItemFromServer> base1 = new BaseResult.Success<>(new ItemFromServer());
//        System.out.println("base1 " + base1.getCode().toString());
//
//        BaseResult<String> base2 = new BaseResult.Error<>("Empty");
//        System.out.println("base1 " + base2.getCode().toString());
//
//        BaseResult<Object> base3 = new BaseResult.Loading<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDisposable.clear();
    }
}













