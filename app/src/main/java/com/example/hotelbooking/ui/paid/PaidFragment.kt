package com.example.hotelbooking.ui.paid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.FragmentHotelBinding
import com.example.hotelbooking.databinding.FragmentPaidBinding
import com.example.hotelbooking.ui.hotel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaidFragment : Fragment() {
    private var _binding: FragmentPaidBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PaidViewModel by viewModels()
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaidBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.payBtn.setOnClickListener {
            findNavController().navigate(R.id.hotelFragment)
        }
    }
}