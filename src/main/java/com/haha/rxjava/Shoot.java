package com.haha.rxjava;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;

public class Shoot {
    public static void main(String[] args) throws InterruptedException {
        Flowable.just("Hello Mr ANderson").subscribe(System.out::println);
//        Flowable.range(1, 5).map(v -> v * v).filter(v -> v % 3 == 0).subscribe(System.out::println);

//        Observable.create(observableEmitter -> {
//            while (!observableEmitter.isDisposed()) {
//                long time = System.currentTimeMillis();
//                observableEmitter.onNext(time);
//                if (time % 2 !=0) {
//                    observableEmitter.onError(new IllegalStateException("Odd number"));
//                    break;
//                }
//            }
//        }).subscribe(System.out::println, Throwable::printStackTrace);

//        Flowable.fromCallable(() -> {
//           Thread.sleep(1000);
//           return "Done";
//        }).subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.single()).subscribe(System.out::println, Throwable::printStackTrace);
//        Thread.sleep(2000);
//        Flowable.range(1,10).subscribeOn(Schedulers.computation())
//                .map(v -> v*v).blockingSubscribe(System.out::println);

//        Flowable.range(1, 10).flatMap(v ->
//            Flowable.just(v).subscribeOn(Schedulers.computation())
//                    .map(w -> w * w))
//                .blockingSubscribe(System.out::println);
        Flowable.range(1,10).parallel().runOn(Schedulers.computation()).map(v->v*v).sequential().blockingSubscribe(System.out::println);

    }

}
