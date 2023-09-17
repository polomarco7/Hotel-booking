package com.example.hotelbooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hotelbooking.databinding.FragmentRoomBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoomViewModel by viewModels()

    private val roomListAdapter = RoomsListAdapter{ _ -> onItemClick()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Room fragment"

        binding.viewModel = viewModel

        binding.roomRecycler.adapter = roomListAdapter
        viewModel.roomsData.onEach {
            roomListAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun onItemClick() {
        findNavController().navigate(R.id.bookingFragment)
    }

}