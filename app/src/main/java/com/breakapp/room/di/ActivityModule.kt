package com.breakapp.room.di

import com.breakapp.room.Repository.TaskRepo
import com.breakapp.room.Repository.TaskRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.sql.DataSource


@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: TaskRepoImpl):TaskRepo

    @Binds
    abstract fun bindDataSource(dataSource: DataSource) :DataSource
}