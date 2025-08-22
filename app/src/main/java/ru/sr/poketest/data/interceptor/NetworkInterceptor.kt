package ru.sr.poketest.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.sr.poketest.domain.networChecker.NetworkChecker
import ru.sr.poketest.domain.networChecker.NoNetworkException

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
