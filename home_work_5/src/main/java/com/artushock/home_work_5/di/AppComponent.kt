package com.artushock.home_work_5.di

import android.content.Context
import com.artushock.home_work_5.MainActivity
import com.artushock.home_work_5.user.UserPresenter
import com.artushock.home_work_5.users.UsersPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    CiceroneModule::class,
    DataBaseModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    UserConverterModule::class,
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(userPresenter: UserPresenter)
    fun inject(usersPresenter: UsersPresenter)
}