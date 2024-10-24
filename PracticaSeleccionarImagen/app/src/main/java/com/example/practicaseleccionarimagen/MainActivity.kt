package com.example.practicaseleccionarimagen

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnSelectPicture: Button
    private lateinit var imgPictureSelected: ImageView
    private var fileChooserResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val bitmap = getBitmapFromIntent(data)
                //TODO: aqui enviarían al API el bitmap
                imgPictureSelected.setImageBitmap(bitmap)

            }
        }
    private fun getBitmapFromIntent(data: Intent?): Bitmap? {
        val imgUrl = data?.data

        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

        val cursor = contentResolver.query(
            imgUrl!!,
            filePathColumn, null, null, null
        )
        cursor!!.moveToFirst()

        val columnIndex = cursor.getColumnIndex(filePathColumn[0])
        val picturePath = cursor.getString(columnIndex)
        cursor.close()

        val bitmap = BitmapFactory.decodeFile(picturePath)
        return bitmap
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnSelectPicture = findViewById(R.id.btnSelectPicture)
        imgPictureSelected = findViewById(R.id.imgPictureSelected)

        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnSelectPicture.setOnClickListener { selectPicture() }
    }

    private fun selectPicture() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        fileChooserResultLauncher.launch(intent)
    }
}