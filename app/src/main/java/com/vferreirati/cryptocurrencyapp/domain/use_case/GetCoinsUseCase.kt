package com.vferreirati.cryptocurrencyapp.domain.use_case

import com.vferreirati.cryptocurrencyapp.common.Resource
import com.vferreirati.cryptocurrencyapp.data.remote.dto.toCoin
import com.vferreirati.cryptocurrencyapp.domain.model.Coin
import com.vferreirati.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())

            val coins = repository.getCoins()
            val mappedCoins = coins.map { it.toCoin() }

            emit(Resource.Success(mappedCoins))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}