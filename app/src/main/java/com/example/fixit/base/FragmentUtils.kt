package com.example.fixit.base

import android.app.AlertDialog
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment

object FragmentUtils {

    fun Fragment.bitmapToUri(bitmap: Bitmap, filePrefix: String = "Image"): Uri {
        val path = MediaStore.Images.Media.insertImage(
            requireContext().contentResolver,
            bitmap,
            "${filePrefix}_${System.currentTimeMillis()}",
            null
        )
        return Uri.parse(path)
    }

    fun Fragment.showImageSourceDialog(
        cameraLauncher: ActivityResultLauncher<Void?>,
        imagePickerLauncher: ActivityResultLauncher<String>
    ) {
        val options = arrayOf("Take Photo", "Choose from Gallery")
        AlertDialog.Builder(requireContext())
            .setTitle("Select Image Source")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> cameraLauncher.launch(null)
                    1 -> imagePickerLauncher.launch("image/*")
                }
            }
            .show()
    }
}