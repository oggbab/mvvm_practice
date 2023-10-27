package com.example.mvvmpr.hilt.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * 우리가 의존성을 주입할 때 외부 라이브러리는 Hilt가 인스턴스를 생성하지 못하는 경우가 있다.
 * 해당 인스턴스는 어떻게 생성해야 하는지 개발자가 알려줘야 한다.
 * 그래서 Module을 어노테이션을 이용해 인스턴스를 만들라고 Hilt에게 알려줘야한다!!!
 *
 * @Module: 특정 유형의 인스턴스를 제공하는 방법을 Hilt에 알려줌
 * @InstallIn :각 모듈을 사용하거나 설치할 Android 클래스를 Hilt에 알려야함
 * SingletonComponent는 Application에 설치한다는 의미
 * @Singleton : Hilt로 제공할 컴포넌트가 Application 범위에 존재합니다. 즉 앱이 꺼질 때까지 유효합니다.
 * builder나 factory 패턴의 경우 이에 해당
 *
 * @Provides : 함수가 인스턴스를 제공함을 Hilt에 알려주는 역할을 합니다.
 외부라이브러리(Retrofit, OkHttpClient, Room) 또는 빌더 패턴으로 인스턴스를 생성하여야하는 경우 사용


 * **/
@Module
@InstallIn(SingletonComponent::class)
class DiModule {
    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context) : NoteDatabase {
        return Room
            .databaseBuilder(
                context,
                NoteDatabase::class.java,
                NoteDatabase.DATABASE_NAME)
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDAO(noteDB: NoteDatabase): NoteDAO {
        return noteDB.noteDao()
    }

    @Singleton
    @Provides
    fun provideNoteRepository(
        noteDAO: NoteDAO
    ) : NoteRepository {
        return NoteRepository(noteDAO)
    }
}