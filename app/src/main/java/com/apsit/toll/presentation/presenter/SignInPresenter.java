package com.apsit.toll.presentation.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;

import com.apsit.toll.data.network.pojo.autocomplete.Addresses;
import com.apsit.toll.data.network.pojo.login.Login;
import com.apsit.toll.data.network.pojo.register.Register;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.domain.model.Credentials;
import com.apsit.toll.presentation.view.BaseView;
import com.apsit.toll.presentation.view.DirectionSelectView;
import com.apsit.toll.presentation.view.SignInView;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by adityathanekar on 31/03/17.
 */

public class SignInPresenter extends BasePresenter<SignInView> {
    private UseCase useCase;
    private Context context;

    @Inject
    public SignInPresenter(UseCase useCase, Context context) {
        this.useCase = useCase;
        this.context = context;
    }

    public void getSignInStatus(String phone, String password) {
        useCase.executeWithInput(new AutocompleteObservable(), new Credentials(phone, password));
    }

    private void saveToken(String token) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("token", token);
        editor.commit();
    }

    private final class AutocompleteObservable implements Consumer<Login> {

        @Override
        public void accept(Login login) throws Exception {
            SignInView view = getView().get();
            if(view != null) {
                int status = login.getStatus();
                switch(status) {
                    case 0:
                        saveToken(login.getToken());
                        view.showSuccess();
                        break;
                    case 1:
                        view.showFailure();
                }
            }
        }
    }
}
