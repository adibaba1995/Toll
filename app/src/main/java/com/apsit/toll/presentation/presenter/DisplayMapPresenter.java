package com.apsit.toll.presentation.presenter;

import com.apsit.toll.data.network.pojo.autocomplete.Addresses;
import com.apsit.toll.data.network.pojo.payment.Payment;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.domain.interactors.UseCase;
import com.apsit.toll.domain.model.Direction;
import com.apsit.toll.domain.model.DirectionData;
import com.apsit.toll.domain.model.MinMaxLatLong;
import com.apsit.toll.domain.model.PaymentDetails;
import com.apsit.toll.presentation.view.DirectionSelectView;
import com.apsit.toll.presentation.view.DisplayMapView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by adityathanekar on 24/03/17.
 */

public class DisplayMapPresenter extends BasePresenter<DisplayMapView> {

    private UseCase directionUseCase, tollUseCase, paymentUseCase;

    @Inject
    public DisplayMapPresenter(UseCase directionUseCase, UseCase tollUseCase, UseCase paymentUseCase) {
        this.directionUseCase = directionUseCase;
        this.tollUseCase = tollUseCase;
        this.paymentUseCase = paymentUseCase;
    }

    public void getPath(DirectionData data) {
        directionUseCase.executeWithInput(new DirectionObservable(), data);
    }

    public void getTolls(MinMaxLatLong data) {
        tollUseCase.executeWithInput(new TollObservable(), data);
    }

    public void makePayment(PaymentDetails paymentDetails) {
        paymentUseCase.executeWithInput(new PaymentObservable(), paymentDetails);
    }

    private final class DirectionObservable implements Consumer<List<Direction>> {

        @Override
        public void accept(List<Direction> direction) throws Exception {
            DisplayMapView view = getView().get();
            if(view != null)
                view.setPath(direction);
        }
    }

    private final class TollObservable implements Consumer<List<Toll>> {

        @Override
        public void accept(List<Toll> tolls) throws Exception {
            DisplayMapView view = getView().get();
            if(view != null)
                view.setTolls(tolls);
        }
    }

    private final class PaymentObservable implements Consumer<Payment> {

        @Override
        public void accept(Payment payment) throws Exception {
            DisplayMapView view = getView().get();
            if(view != null)
                view.showPaymentStatus(payment);
        }
    }
}