package com.apsit.toll.data.network.service;

import com.apsit.toll.data.network.pojo.login.Login;
import com.apsit.toll.domain.model.Credentials;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class SignInService {

    private LoginService loginService;

    @Inject
    public SignInService(Retrofit login) {
        loginService = login.create(LoginService.class);
    }

    public Observable<Login> getDirectionObservable(Credentials credentials) {
        return loginService.getLoginStatus(credentials.getPhone(), credentials.getPassword());
    }

    private interface LoginService {
        @GET("login.php")
        Observable<Login> getLoginStatus(@Query("phone") String phone, @Query("password") String password);
    }

}
