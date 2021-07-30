package com.dvalic.appaudiclass.core

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.dvalic.appaudiclass.R
import com.dvalic.appaudiclass.databinding.ActivityPdfViewerBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File


class PdfViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfViewerBinding
    private var ruta: String? = ""
    private var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)
        binding = ActivityPdfViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        PRDownloader.initialize(applicationContext)

        intent.extras?.let { bundle ->
            ruta = bundle.getString("Ruta")
            type = bundle.getInt("Type")
        }

        when (type) {
            0 -> ruta?.let { getPdfNameFromAssets(it) }
            1 -> selectPdfFromStorage()
            2 -> checkPermission()
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission()
        } else {
            binding.progressIndicator.visibility = View.VISIBLE
            ruta?.let {
                downloadPdfFromInternet(it, this.externalCacheDir?.absolutePath.toString())
            }
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //El usuario ya ha rechazado el permiso anteriormente, debemos informarle que vaya a ajustes.
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 225)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            225 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    binding.progressIndicator.visibility = View.VISIBLE
                    ruta?.let {
                        downloadPdfFromInternet(it, this.externalCacheDir?.absolutePath.toString())
                    }
                } else {
                    //El usuario ha rechazado el permiso, podemos desactivar la funcionalidad o mostrar una vista/diálogo.
                    finish()
                }
                return
            }
            else -> {
                // Este else lo dejamos por si sale un permiso que no teníamos controlado.
            }
        }
    }

    fun getPdfNameFromAssets(pdfName: String) {
        binding.PdfViewer.fromAsset(pdfName)
                .pages(0, 2, 1, 3, 3, 3)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .spacing(0)
                .load()
    }

    companion object {
        private const val PDF_SELECTION_CODE = 99
    }

    private fun selectPdfFromStorage() {
        Toast.makeText(this, "selectPDF", Toast.LENGTH_LONG).show()
        val browseStorage = Intent(Intent.ACTION_GET_CONTENT)
        browseStorage.type = "application/pdf"
        browseStorage.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(Intent.createChooser(browseStorage, "Select PDF"), PDF_SELECTION_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PDF_SELECTION_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedPdfFromStorage = data.data
            showPdfFromUri(selectedPdfFromStorage)
        }
    }

    private fun showPdfFromUri(uri: Uri?) {
        binding.PdfViewer.fromUri(uri)
                .pages(0, 2, 1, 3, 3, 3)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .spacing(0)
                .load()
    }

    private fun downloadPdfFromInternet(url: String, dirPath: String) {
        PRDownloader.download(url, dirPath, "AppAudiClass.pdf").build()
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        val downloadedFile = File(dirPath, "AppAudiClass.pdf")
                        binding.progressIndicator.visibility = View.GONE
                        showPdfFromFile(downloadedFile)
                    }

                    override fun onError(error: com.downloader.Error?) {
                        Snackbar.make(binding.PdfViewer, "Problema en archivo PDF", Snackbar.LENGTH_SHORT).show()
                        finish()
                    }
                })
    }

    private fun showPdfFromFile(file: File) {
        binding.PdfViewer.fromFile(file)
                .pages(0, 2, 1, 3, 3, 3)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .spacing(0)
                .load()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}