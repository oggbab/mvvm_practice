package com.mvvm

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers.single
import org.junit.Test


class RxTest {


    /**
     * 리액티브 연산자
     *
     * **/

    //데이터 가공하게 해주는 함수
    fun map() {
        val arr = arrayOf(1,2,3)
        arr.toObservable()
            .map { it * 10 }
            .subscribe {
                println("map(): $it")
            }
    }

    //데이터 가공 결과가 단순 데이터가 아닌 Observable 형태로 나온다는 특징
    //flatMap{} 괄호 안에서 Observable 아이템이 2개가 생성되어 총 6개의 아이템이 만들어진
    fun flatMap() {
        val arr = arrayOf("꽃순이", "몽구", "누렁이")
        val source = arr.toObservable().flatMap {
            Observable.just("${it}1", "${it}2")
        }
        source.subscribe {
            println(it)
        }
    }

    //이름대로 필터링해준다
    @Test
    fun filter() {
        val originItem = Observable.just(1, 2, 3, 4, 5)
        var filteredItem = originItem

        //filter
        filteredItem.filter { it > 4 }
        .subscribe { it -> println("filter: ${it!!} ") }

        // first 첫번째값을 걸러줌, 없으면 기본값
        filteredItem.first(-1)
        .subscribe { it -> println("first: ${it!!} ") }

        // last 마지막값을 걸러줌, 없으면 기본값
        filteredItem.last(-2)
        .subscribe { it -> println("last: ${it!!} ") }

        //take N개의 값만큼 걸러줌
        filteredItem.take(3)
        .subscribe { it -> println("take 3: ${it!!} ") }

        //takeLast 마지막으로부터 N개 걸러줌
        filteredItem.takeLast(2)
        .subscribe { it -> println("takeLast 2 : ${it!!} ") }

        //skip N개만큼 건너뛰고 걸러줌
        filteredItem.skip(3)
        .subscribe { it -> println("skip 3 : ${it!!} ") }

        //skipLast 마지막으로부터 N개만큼 값을 건너뛰고 걸러줌
        filteredItem.skipLast(2)
        .subscribe { it -> println("skip 2 : ${it!!} ") }

    }



}









