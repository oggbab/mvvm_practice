package basic_test.rx

import com.orhanobut.logger.Logger
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers.single
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class RxJavaUtil {

    /**
        Observabled은 관찰 대상이다. 객체 생성을 발행이라고 부른
        Observable은 팩토리함수(just등)를 이용해 생성하고 subscribe()함수를 통해 발행된다.
        3가지 알림을 구독자에게 전달한다 (onNext onComplete onError)
        onNext 아이템을 추가하는 과정
        onComplete 발행완료
        onError 발행에 문제가 생겼을때

        구독상태 확인할떄는 아래와 같다
        관찰대상.dispose() 구독해지(onComplete 알림을 받아도 자동 해지된다)
        관찰대상.isDisposed() 데이터발행여부(구독해지상태) 를 boolean으로 받는다
     * **/

    class default {

        //가장 간단한 방법
        //인지만 넣어주면 자동으로 알림을 준
        fun just() {

            val just = Observable.just(
                "A", "B", "C"
            )
                .subscribe {
                    Logger.d("RxJava just() : $it")
                }
        }

        //알람을 직접 구현해야함
        fun create() {
            val create = Observable.create<String> {
                it.onNext("A")
                it.onNext("B")
                it.onNext("C")
            }
            create.subscribe { it: String? ->
                Logger.d("RxJava create() : $it")
            }
            //자바였다면


        }

        fun fromArray() {
            //문자열일때
            val arrString = arrayOf<String>("A", "B", "C")
            val fromArray = arrString.toObservable()
            fromArray.subscribe {
                Logger.d("RxJava fromArray() String : $it")
            }
            val arrInt = intArrayOf(1, 2, 3)
            val intArray: Observable<Int> = arrInt.toObservable()
            intArray.subscribe {
                Logger.d("RxJava fromArray() Int : $it")
            }

            //정수형일떄 자바
//        int[] arr2 = {1, 2, 3};
//        Observable<Integer> source2 = Observable.fromArray(toIntegerArray(arr2)); //int[] -> Integer[]  everything is fine!
//        source2.subscribe(System.out::print); // 결과 123
        }

        //배열과 정적타입이 아닌 대부분의 타입을 처리 ex) HashMap stack
        fun iteratarble() {
            val arrArrayList = arrayListOf<String>().apply {
                add("Charles")
                add("Soon2")
                add("Woong")
            }
            Observable.fromIterable(arrArrayList).subscribe {
                Logger.d("RxJava Iteratable() : $it")
            }
        }
    }

    class Callable {
        fun callable() {
            //fromCallable()
            val callable = Callable<String> {
                Logger.d("RxJava callable() : 5sec")
                Thread.sleep(5000)
                "Hello World"
            }
            Observable.fromCallable(callable).subscribe {
                Logger.d("RxJava callable() : $it")
            }
        }
    }


    class Future {

        fun fromFuture() {
            val future = Executors.newSingleThreadExecutor().submit(Callable {
                Thread.sleep(5000)
                "hello"
            })
            Observable.fromFuture(future).subscribe {
                Logger.d("RxJava fromFuture() : $it")
            }
        }
    }


    /**
     *  JAVA 9 API
        모르겠
     **/
    class Publisher {
        fun fromPublisher() {
            val publisher = Publisher {
                s: Subscriber<in String>? ->
                s?.onNext("Hello Publisher")
                s?.onComplete()
            }
            Observable.fromPublisher(publisher).subscribe {
                Logger.d("RxJava fromPublisher() : $it")
            }
        }

    }


    /**
     *  Maybe
     *  single과 다른 점은 발행 없이도 완료할수 있다는 점
        Single 무조건 1개 발행 Maybe 0 or 1
     **/

    class Maybe {
        fun maybe() {
    //        Maybe.just()
        }
    }



    /**
        single class
        1개의 데이터만 발행 한정(1개 발행후 자동 종료됨)
        api 호출시 주로 사용
     * **/

    fun singleClass() {
        //Single 객체 이용
        Single.just("Hello Single").subscribe { success: String, err: Throwable ->
            Logger.d("RxJava single() success : $success")
            Logger.d("RxJava single() error : $err")
        }
        //아래는 에러 (IllegalArgumentException: Sequence contains more than one element!)
        //Observable.just("one","two").single("default item")

        //옵저버블을 이용해서
        val source = Observable.just("First")
        Single.fromObservable(source).subscribe { success: String?, err: Throwable? ->
            println("RxJava single() by Observable success : $success")
            println("RxJava single() by Observable error : $err")
        }

        //해당 인자를 첫 발행 인자로 갖는다는 의미
        Observable.just("HelloWorld").single("defalt").subscribe { it: String? ->
            println("RxJava single() by $it")
        }

        //첫번째 인자로 Single 객체 생성
        arrayOf("A", "B", "C").toObservable().first("default")
            .subscribe { success: String?, err: Throwable? ->
                println("RxJava single() by 인자 지정 success : $success")
                println("RxJava single() by 인자 지정 error : $err")
            }


        Observable.empty<String>().single("default")
            .subscribe { success: String?, err: Throwable? ->
                println("RxJava single() by 인자 지정 success : $success")
                println("RxJava single() by 인자 지정 error : $err")
            }
    }



    /**
     * Subject
     * Observer와 Observable 의 속성을 모두 가지고 있다
     * 마지막 발행 아이템을 얻어오는 클래스
     * 중간에 구독(subscribe)이 가능하지만 모든 구독자 마지막 발행 아이템만을 갖는다가(동기화)
     * **/

    class Subject {

        fun subject() {
            val subject = AsyncSubject.create<String>()
            subject.subscribe { color: String? ->
                println("Subject AsyncSubject 구독자 1 : $color")
            }
            subject.onNext("Red")
            subject.onNext("Green")
            subject.subscribe { color: String ->
                println("Subject AsyncSubject 구독자 2 : $color")
            }
            subject.onNext("Blue")
            subject.onComplete()

            subject.onNext("Yellow")
            subject.subscribe { color: String ->
                println("Subject AsyncSubject 구독자 3 : $color(onComlete 이후 발행)")
            }
        }

        //구독을 하면 가장 최근 값 or 기본값을 넘겨주는 클래스
        //구독 시점 가장 최근 값부터 받는다. (3번째 이후 부터 구독했으면 3번부터)
        //구독 이후의 아이템은 모두 똑같이 가져간다
        fun behaviorSubject() {
            val subject = BehaviorSubject.createDefault("Pink")
            subject.subscribe {
                println("behaviorSubject 구독자 1 : $it")
            }
            subject.onNext("Red")
            subject.onNext("Green")
            subject.subscribe {
                println("behaviorSubject 구독자 2 : $it")
            }
            subject.onNext("Blue")
            subject.onComplete()
        }

        //기본형
        //구독 이후 값부터 가져온다
        fun publishSubject() {
            val subject = PublishSubject.create<String>()
            subject.subscribe {
                println("PublishSubject 구독자 1 : $it")
            }
            subject.onNext("Red")
            subject.onNext("Green")
            subject.subscribe {
                println("PublishSubject 구독자 2 : $it")
            }
            subject.onNext("Blue")
            subject.onComplete()
        }

        //Cold Observable 타입
        //구독 시점과 상관없이 모든 데이터를 가져간다
        //메모리 누수 조심
        fun replaySubject() {
            val subject = ReplaySubject.create<String>()
            subject.subscribe {
                println("replaySubject 구독자 1: $it")
            }
            subject.onNext("Red")
            subject.onNext("Green")
            subject.subscribe {
                println("replaySubject 구독자 2: $it")
            }
            subject.onNext("Red")
            subject.onComplete()
        }
    }


    /**
     * @author Soon2
     * ConnectableObeservable
        subject와 유사
        구독자 모두에게 동시에 데이터를 발행하는 방식
        connect()를 반드시 써야 구독됨

        1초마다 아이템을 발행하고(interval)
        구독후(connect실행) 2.5초 이후 면 2개만 발행되어있다
        3번 구독자가 +1초이후(총 3.5초) 마지막 1개 아이템만 가져간다.
     * **/

    fun connectableObservable() {
        val arr = arrayOf("Red", "Green", "Blue")
        val colors = Observable.interval(1000L, TimeUnit.MILLISECONDS)
            .map { time : Long -> arr[time.toInt()] }
            .take(arr.size.toLong())
        val source = colors.publish()
        source.subscribe {
            println("구독자 1 : $it")
        }
        source.subscribe {
            println("구독자 2 : $it")
        }
        source.connect()
        Thread.sleep(2500)
        source.subscribe {
            println("구독자 3 : $it")
        }
        Thread.sleep(1000)
    }
}