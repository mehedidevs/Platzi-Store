package com.mehedi.platzistore.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mehedi.platzistore.model.data.product.ResponseProductItem
import com.mehedi.platzistore.model.repositories.ProductRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repo: ProductRepo) : ViewModel() {

    private val _response = MutableLiveData<Response<List<ResponseProductItem>>>()

    val productResponse: LiveData<Response<List<ResponseProductItem>>> = _response


    private val _responseSingle = MutableLiveData<Response<ResponseProductItem>>()

    val productResponseSingle: LiveData<Response<ResponseProductItem>> = _responseSingle


    val data = repo.getData().cachedIn(viewModelScope)


    fun getAllProduct() {

        viewModelScope.launch {
            _response.postValue(repo.getAllProduct())
        }


    }

    fun getProductByID(id: Int) {

        viewModelScope.launch {
            _responseSingle.postValue(repo.getProductByID(id))

        }


    }


    private val _productID = MutableLiveData<Int>()
    val productID: LiveData<Int> = _productID

    fun setClickedProductID(id: Int) {
        _productID.postValue(id)
    }


}