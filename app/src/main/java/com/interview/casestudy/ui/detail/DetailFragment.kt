package com.interview.casestudy.ui.detail

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.interview.casestudy.R
import com.interview.casestudy.databinding.FragmentDetailBinding
import com.interview.casestudy.ui.MainViewModel
import com.interview.casestudy.util.AppConstant.GENERAL_ERROR
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), MediaPlayer.OnCompletionListener {

    private lateinit var binding: FragmentDetailBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var player: MediaPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        player = MediaPlayer().apply {
            setOnCompletionListener(this@DetailFragment)
            try {
                setDataSource("https://d2r0ihkco3hemf.cloudfront.net/bgxupraW2spUpr_y2.mp3")
                prepare()
            } catch (e: Exception) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.title_error))
                    .setMessage(GENERAL_ERROR)
                    .show()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = mainViewModel
            lifecycleOwner = viewLifecycleOwner
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            btnPlayPause.setOnClickListener {
                player.apply {
                    if(!player.isPlaying){
                        player.start()
                        btnPlayPause.setImageResource(R.drawable.pause)
                    }else {
                        player.pause();
                        btnPlayPause.setImageResource(R.drawable.play)
                    }
                }
            }
        }

    }

    override fun onCompletion(mp: MediaPlayer?) {
        binding.btnPlayPause.setImageResource(R.drawable.play)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
    }

}