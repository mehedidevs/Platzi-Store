package com.mehedi.platzistore.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mehedi.platzistore.model.data.product.ResponseProductItem
import com.mehedi.platzistore.services.ProductService
import javax.inject.Inject

class PagingSource
@Inject constructor(private val service: ProductService) :
    PagingSource<Int, ResponseProductItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseProductItem> {
        val position = params.key ?: 0

        return try {
            val response = service.getAllProductPaging(params.loadSize, 10)

            Log.d("TAG", "load: ${response.size} ")



            LoadResult.Page(
                data = response,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )


        } catch (e: Exception) {

            Log.d("TAG", "Exception: ${e.message} ")

            LoadResult.Error(e)
        }


    }


    override fun getRefreshKey(state: PagingState<Int, ResponseProductItem>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(10) ?: anchorPage?.nextKey?.minus(10)
        }


    }
}