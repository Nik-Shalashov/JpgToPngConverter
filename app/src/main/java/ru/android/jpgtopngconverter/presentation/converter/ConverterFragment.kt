package ru.android.jpgtopngconverter.presentation.converter

import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.android.jpgtopngconverter.R
import ru.android.jpgtopngconverter.R.layout.view_converter
import ru.android.jpgtopngconverter.click
import ru.android.jpgtopngconverter.databinding.ViewConverterBinding
import ru.android.jpgtopngconverter.models.ConverterFactory
import ru.android.jpgtopngconverter.presentation.scheduler.SchedulersFactory

class ConverterFragment : MvpAppCompatFragment(view_converter), ConverterView {

    companion object {
        fun newInstance(): Fragment = ConverterFragment()
    }

    private val presenter by moxyPresenter {
        ConverterPresenter(
            converter = ConverterFactory.create(requireContext()),
            schedulers = SchedulersFactory.create()
        )
    }

    private val vb: ViewConverterBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.converter_title)
        vb.button.click(::pickImage)
    }

    private fun pickImage() {
        val getIntent = Intent(ACTION_GET_CONTENT)
        getIntent.type = "image/*"
        startActivityForResult(getIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        data?.data?.let(presenter::convert)
            ?: Toast.makeText(requireContext(), "Изображение не выбрано", Toast.LENGTH_SHORT).show()
    }

    override fun showContent(uri: Uri?) {
        val bitmap: Bitmap? =
            uri?.let { MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri) }

        vb.progress.visibility = View.GONE
        vb.imageView.setImageBitmap(bitmap)

        vb.button.click(::pickImage)
        vb.button.text = getString(R.string.choose_image)
    }

    override fun showLoading() {
        vb.progress.visibility = View.VISIBLE

        vb.button.click(presenter::cancel)
        vb.button.text = getString(R.string.cancel)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
    }
}