package com.mehedi.platzistore.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mehedi.platzistore.model.data.login.RequestLogin
import com.mehedi.platzistore.model.data.login.ResponseLogin
import com.mehedi.platzistore.model.data.product.ResponseProduct
import com.mehedi.platzistore.model.data.profile.ResponseProfile
import com.mehedi.platzistore.model.repositories.AuthRepo
import com.mehedi.platzistore.model.repositories.ProductRepo
import com.mehedi.platzistore.model.repositories.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repo: ProductRepo) : ViewModel() {

    private val _response = MutableLiveData<Response<List<ResponseProduct>>>()

    val productResponse: LiveData<Response<List<ResponseProduct>>> = _response


    private val _responseSingle = MutableLiveData<Response<ResponseProduct>>()

    val productResponseSingle: LiveData<Response<ResponseProduct>> = _responseSingle


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