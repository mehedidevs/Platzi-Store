package com.mehedi.platzistore.ui.file

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels

import com.github.dhaval2404.imagepicker.ImagePicker
import com.mehedi.platzistore.R
import com.mehedi.platzistore.databinding.FragmentUplaodBinding
import com.mehedi.platzistore.utils.load
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.net.URI


@AndroidEntryPoint
class UplaodFragment : Fragment() {


    lateinit var binding: FragmentUplaodBinding

    val viewModel: UploadViewModel by viewModels()

    lateinit var imageUri: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentUplaodBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pIV.setOnClickListener {

            ImagePicker.with(this)
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    1080,
                    1080
                )  //Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }

        }

        binding.uploadBtn.setOnClickListener {

            val fileDir = requireActivity().filesDir
            val file = File(fileDir, "product${System.currentTimeMillis()}.png")

            val inputStream = requireActivity().contentResolver.openInputStream(imageUri)

            val outputStream = FileOutputStream(file)
            inputStream!!.copyTo(outputStream)

            val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

            val part = MultipartBody.Part.createFormData("file", file.name, requestBody)


            viewModel.uploadFile(part)

            inputStream.close()
            outputStream.close()


        }

        viewModel.uploadResponse.observe(viewLifecycleOwner) {


            if (it.isSuccessful) {

                Log.d("TAG", "${it.body()}")

                binding.responseLayout.visibility = View.VISIBLE

                binding.pIVOnline.load(it.body()?.location!!)


            }


        }


    }


    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data!!
                    binding.pIV.setImageURI(fileUri)

                    imageUri = fileUri

                    binding.uploadBtn.visibility = View.VISIBLE
                }

                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {
                    Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }


}