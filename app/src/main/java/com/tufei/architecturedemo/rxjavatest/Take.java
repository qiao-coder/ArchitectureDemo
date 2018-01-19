package com.tufei.architecturedemo.rxjavatest;

import io.reactivex.Observable;

/**
 * @author tufei
 * @date 2018/1/19.
 */

public class Take {
    public static void main(String[] args) {
        Observable.range(1, 3)
                .take(0)
                .subscribe(integer -> System.out.println(integer));
        System.out.println("----------");
        Observable.range(1, 3)
                .take(1)
                .subscribe(integer -> System.out.println(integer));
        System.out.println("----------");
        Observable.range(1, 3)
                .take(2)
                .subscribe(integer -> System.out.println(integer));
    }
}
