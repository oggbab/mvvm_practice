package basic_test.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerAppCompatActivity
import java.util.stream.DoubleStream.builder
import javax.inject.Singleton

class DaggerUtil {

}

/*
@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class)])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector<App>()
}

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppCompatActivity.builder().create(this)
    }
}

@Module
abstract class MainActivityModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideMainActivityBinding
    }
}*/
