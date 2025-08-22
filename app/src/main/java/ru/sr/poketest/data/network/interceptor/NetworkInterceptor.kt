package ru.sr.poketest.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.sr.poketest.data.network.networChecker.NetworkChecker
import ru.sr.poketest.data.network.networChecker.NoNetworkException

class NetworkInterceptor(
    private val networkChecker: NetworkChecker
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!networkChecker.isNetworkAvailable()) {
            throw NoNetworkException()
        }

        val request = chain.request()
        return chain.proceed(request)
    }
}
