package ru.sr.poketest.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.sr.poketest.data.database.PokemonDatabase
import ru.sr.poketest.data.network.PokeApi
import ru.sr.poketest.data.repository.PokemonRepositoryImpl
import ru.sr.poketest.domain.interactor.PokemonInteractor
import ru.sr.poketest.domain.interactor.impl.PokemonInteractorImpl
import ru.sr.poketest.domain.repository.PokemonRepository
import ru.sr.poketest.presentation.screen.home.PokemonListViewModel
import ru.sr.poketest.presentation.screen.search.SearchVewModel


const val BASE_URL = "https://pokeapi.co/api/"
const val DATABASE_NAME = "pokemon_database"

fun pokeModule() = module {

    single {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(PokeApi::class.java) }

    single {
        val room = Room.databaseBuilder(
            context = get(),
            klass = PokemonDatabase::class.java,
            name = DATABASE_NAME
        ).build()

        room.pokemonDao()
    }

    factoryOf(::PokemonRepositoryImpl) { bind<PokemonRepository>() }
    factoryOf(::PokemonInteractorImpl) { bind<PokemonInteractor>() }

    viewModelOf(::PokemonListViewModel)
    viewModelOf(::SearchVewModel)
}
