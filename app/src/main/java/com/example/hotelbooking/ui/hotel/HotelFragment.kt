package com.example.hotelbooking.ui.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.FragmentHotelBinding
import com.example.hotelbooking.databinding.IntroAppDesignBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HotelFragment : Fragment() {
    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HotelViewModel by viewModels()
    private var bundle: Bundle = Bundle()
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        State.Loading -> {
                            binding.linearFirst.visibility = View.GONE
                            binding.linearSecond.visibility = View.GONE
                            binding.linearThird.visibility = View.GONE
                            binding.progressCircular.visibility = View.VISIBLE
                        }
                        is State.Success -> {
                            binding.linearFirst.visibility = View.VISIBLE
                            binding.linearSecond.visibility = View.VISIBLE
                            binding.linearThird.visibility = View.VISIBLE
                            binding.progressCircular.visibility = View.GONE
                            binding.photoSlider.viewPager2.adapter =
                                AppIntroViewPager2Adapter(state.hotels.imageUrls)
                            binding.nameLayout.textViewRating.text = state.hotels.rating.toString()
                            binding.nameLayout.textViewRatingDesc.text = state.hotels.ratingName
                            binding.nameLayout.textViewHotelName.text = state.hotels.name
                            binding.nameLayout.textViewHotelAddress.text = state.hotels.address
                            binding.textViewHotelCost.text = "от ${state.hotels.minimalPrice}"
                            binding.textViewHotelCostPer.text = state.hotels.priceForIt
                            binding.recyclerViewHotelAdv.adapter = state.hotels.aboutTheHotel?.let {
                                HotelDescAdapter(
                                    it.peculiarities
                                )
                            }
                            binding.textViewHotelDesc.text =
                                state.hotels.aboutTheHotel?.description ?: " "
                            TabLayoutMediator(
                                binding.photoSlider.tabLayout,
                                binding.photoSlider.viewPager2
                            ) { _, _ ->
                            }.attach()
                            bundle = bundleOf(Pair("hotel_name", state.hotels.name))
                        }
                    }
                }
            }
        }
        binding.selectRoomBtn.setOnClickListener {

            findNavController().navigate(R.id.roomFragment, bundle)
        }

    }
}
class AppIntroViewPager2Adapter(private val listImages: List<String>) : RecyclerView.Adapter<PagerVH2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH2 {
        return PagerVH2(
            IntroAppDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun getItemCount(): Int = listImages.size
    override fun onBindViewHolder(holder: PagerVH2, position: Int) {

        with(holder.bindingDesign) {
            listImages[position].let {
                Glide
                    .with(introImage.context)
                    .load(it)
                    .into(introImage)
            }
        }
    }
}
class PagerVH2(val bindingDesign: IntroAppDesignBinding) : RecyclerView.ViewHolder(bindingDesign.root)

