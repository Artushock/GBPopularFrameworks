package com.artushock.home_work_5.di

import android.content.Context
import com.artushock.home_work_5.MainActivity
import com.artushock.home_work_5.user.di.UserFragmentComponent
import com.artushock.home_work_5.users.UsersPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideUserFragmentComponent(): UserFragmentComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setContext(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(usersPresenter: UsersPresenter)
}