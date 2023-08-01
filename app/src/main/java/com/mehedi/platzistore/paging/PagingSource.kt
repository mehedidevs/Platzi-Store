package com.mehedi.platzistore.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mehedi.platzistore.model.data.product.ResponseProductItem
import com.mehedi.platzistore.services.ProductService
import kotlinx.coroutines.delay
import javax.inject.Inject

class PagingSource @Inject constructor(private val service: ProductService) :
    PagingSource<Int, ResponseProductItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseProductItem> {
        val page = params.key ?: 0

        Log.d("TAG", "page: $page")

        Log.d("TAG", "offset: ${page * params.loadSize}")
        val response = service.getAllProductPaging(page * params.loadSize, params.loadSize)
        // val response = service.getAllProductPaging(170, params.loadSize)

        return try {


            //Log.d("TAG", "load: ${response.size} ")
          //  if (page != 0) delay(1000)


            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )


        } catch (e: Exception) {

            Log.d("TAG", "Exception: ${e.message} ")

            LoadResult.Error(e)
        }


    }


    override fun getRefreshKey(state: PagingState<Int, ResponseProductItem>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }


    }
}