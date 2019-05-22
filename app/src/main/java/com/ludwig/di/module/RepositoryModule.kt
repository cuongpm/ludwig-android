package com.ludwig.di.module

import com.ludwig.data.local.LocalDataSource
import com.ludwig.data.local.LocalDataSourceImpl
import com.ludwig.data.remote.RemoteDataSource
import com.ludwig.data.remote.RemoteDataSourceImpl
import com.ludwig.data.repository.LudwigRepository
import com.ludwig.data.repository.LudwigRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by cuongpm on 5/11/19.
 */

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

    @Singleton
    @Binds
    abstract fun bindLudwigRepository(ludwigRepository: LudwigRepositoryImpl): LudwigRepository
}
