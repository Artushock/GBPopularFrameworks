package com.artushock.home_work_5.di

import dagger.Module

@Module(includes = [
    CiceroneModule::class,
    DataBaseModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    UserConverterModule::class,
    UserDaoModule::class])

class AppModule