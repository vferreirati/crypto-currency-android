package com.vferreirati.cryptocurrencyapp.domain.use_case

import com.vferreirati.cryptocurrencyapp.common.Resource
import com.vferreirati.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.vferreirati.cryptocurrencyapp.domain.model.CoinDetail
import com.vferreirati.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())

            val coin = repository.getCoinById(coinId)
            val mappedCoin = coin.toCoinDetail()

            emit(Resource.Success(mappedCoin))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}