package com.apsit.toll.presentation.presenter;

import com.apsit.toll.data.network.pojo.autocomplete.Addresses;
import com.apsit.toll.domain.interactors.AutocompleteInteractor;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.presentation.view.DirectionSelectView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by adityathanekar on 23/03/17.
 */

public class DirectionPresenter extends BasePresenter<DirectionSelectView> {

    private UseCase useCase;

    @Inject
    public DirectionPresenter(UseCase useCase) {
        this.useCase = useCase;
    }

    public void getAutocompleteList(String input) {
        useCase.executeWithInput(new AutocompleteObservable(), input);
    }

    private final class AutocompleteObservable implements Consumer<Addresses> {

        @Override
        public void accept(Addresses addresses) throws Exception {
            DirectionSelectView view = getView().get();
            if(view != null)
                view.setAutocompleteList(addresses.getPredictions());
        }
    }
}
