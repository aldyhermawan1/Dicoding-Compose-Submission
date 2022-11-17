package com.hermawan.compose.moviedb.di

import androidx.room.Room
import com.hermawan.compose.moviedb.BuildConfig
import com.hermawan.compose.moviedb.data.ISeriesRepository
import com.hermawan.compose.moviedb.data.SeriesRepository
import com.hermawan.compose.moviedb.data.local.LocalDataSource
import com.hermawan.compose.moviedb.data.local.database.SeriesDatabase
import com.hermawan.compose.moviedb.data.remote.RemoteDataSource
import com.hermawan.compose.moviedb.data.remote.api.ApiClient
import com.hermawan.compose.moviedb.domain.SeriesInteractor
import com.hermawan.compose.moviedb.domain.SeriesUseCase
import com.hermawan.compose.moviedb.utils.Constants.BASE_URL
import com.hermawan.compose.moviedb.utils.Constants.DB_KEY
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<SeriesDatabase>().seriesDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(DB_KEY.toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            SeriesDatabase::class.java,
            "Series.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = BASE_URL
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/p+WeEuGncQbjSKYPSzAaKpF/iLcOjFLuZubtsXupYSI=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else HttpLoggingInterceptor.Level.NONE
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiClient::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<ISeriesRepository> { SeriesRepository(get(), get()) }
}

val useCaseModule = module {
    factory<SeriesUseCase> { SeriesInteractor(get()) }
}

val viewModelModule = module {

}