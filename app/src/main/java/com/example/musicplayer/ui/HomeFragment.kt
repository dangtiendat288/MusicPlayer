package com.example.musicplayer.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musicplayer.R
import com.example.musicplayer.adapters.SongAdapter
import com.example.musicplayer.other.Status
import com.example.musicplayer.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var songAdapter: SongAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        setupRecyclerView()
        subcribeToObservers()

        songAdapter.setItemClickListener {
            mainViewModel.playOrToggleSong(it)
        }
    }

    private fun setupRecyclerView() = rvAllSongs.apply{
        adapter = songAdapter
    }

    private fun subcribeToObservers() {
        mainViewModel.mediaItems.observe(viewLifecycleOwner){result ->
            when (result.status){
                Status.SUCCESS ->{
                    allSongsProgressBar.isVisible = false
                    result.data?.let{ songs ->
                        songAdapter.songs = songs
                    }
                }
                Status.ERROR -> Unit
                Status.LOADING -> allSongsProgressBar.isVisible = true
            }
        }
    }
}