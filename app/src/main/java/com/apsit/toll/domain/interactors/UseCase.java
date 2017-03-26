package com.apsit.toll.domain.interactors;


import com.apsit.toll.domain.executor.PostExecutionThread;
import com.apsit.toll.domain.executor.ThreadExecutor;
import com.apsit.toll.domain.model.Data;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T extends Object> {

  private Disposable subscription;

  protected abstract Observable buildUseCaseObservable();

  protected abstract Observable buildUseCaseObservable(T data);

  public void execute(Consumer useCaseSubscriber) {
    this.subscription = this.buildUseCaseObservable()
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(useCaseSubscriber);
  }

  public void executeWithInput(Consumer useCaseSubscriber, T data) {
    this.subscription = this.buildUseCaseObservable(data)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(useCaseSubscriber);
  }

  public void unsubscribe() {
    if (subscription != null) {
      subscription.dispose();
    }
  }
}
