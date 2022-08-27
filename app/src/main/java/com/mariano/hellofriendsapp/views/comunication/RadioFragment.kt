package com.mariano.hellofriendsapp.views.comunication

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Environment
import android.view.MotionEvent
import com.mariano.hellofriendsapp.databinding.FragmentRadioBinding
import com.mariano.hellofriendsapp.views.BaseFragment
import android.view.View.OnTouchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioFragment : BaseFragment<FragmentRadioBinding>() {

    var estadoBtn: Boolean = false
    var audioUrl = "http://192.168.1.6/AmorConHieloMorat.mp3"

    lateinit var grabacion: MediaRecorder;
    var player: MediaPlayer = MediaPlayer();
    var outpodFile: String? = null;

    @SuppressLint("ClickableViewAccessibility")
    override fun setUpViews() {
        grabacion = MediaRecorder();
        outpodFile = Environment.getExternalStorageDirectory().absolutePath + "/grabacion" + ".mp3"

        binding.btnTrans.setOnClickListener {
            estadoBtn = false
        }

        binding.btnTrans.setOnTouchListener(OnTouchListener
        { _,
          motionEvent ->

            when (motionEvent.action) {

                MotionEvent.ACTION_UP -> {

                    if (estadoBtn)
                    {
                        stopRecorder()
                        binding.btnTrans.text = "SE DEJO DE GRABAR"
                    }
                }
            }
            false
        })

        binding.btnTrans.setOnLongClickListener {
            binding.btnTrans.text = "SE ESTA GRABANDO"
            seTypes()
            initRecorder()
            return@setOnLongClickListener true
        }

        binding.btnVolmenMenos.setOnClickListener {
            //player.setDataSource(outpodFile)
            this.context?.let { it1 -> player.setDataSource(it1, Uri.parse(audioUrl)) }
            reproducer()
        }
    }

    override fun observeData() {

    }

    private fun initRecorder()
    {
        grabacion.setOutputFile(outpodFile)
        grabacion.prepare()
        grabacion.start()
        estadoBtn = true
    }

    private fun stopRecorder()
    {
        grabacion.stop()
        grabacion.reset();
        player.reset()
    }

    private fun seTypes()
    {
        grabacion.setAudioSource(MediaRecorder.AudioSource.MIC)
        grabacion.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        grabacion.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
    }

    private fun reproducer()
    {
        player.prepare();
        player.start()
    }

    override val viewBinding: FragmentRadioBinding
        get() = FragmentRadioBinding.inflate(layoutInflater)
}