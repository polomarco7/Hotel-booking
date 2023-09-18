package com.example.hotelbooking.ui.booking

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.hotelbooking.models.HintData
import com.example.hotelbooking.R
import com.example.hotelbooking.models.TextViewHint
import com.example.hotelbooking.databinding.FragmentBookingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


@AndroidEntryPoint
class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookingViewModel by viewModels()
    private val hintDataList = mutableListOf<HintData>()
    private lateinit var expandableListViewAdapter: ExpandableListViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
        expandableListViewAdapter = ExpandableListViewAdapter(hintDataList)
        binding.expandableListView.setAdapter(expandableListViewAdapter)

        hintDataList.add(
            HintData("Первый турист", listOf(TextViewHint()))
        )

        binding.expandableListView.setOnGroupClickListener { parent, _, groupPosition, _ ->
            setListViewHeight(parent, groupPosition)
            false
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        StateBooking.Loading -> {
                        }
                        is StateBooking.Success -> {
                            binding.nameLayout.textViewRating.text = state.rooms.horating.toString()
                            binding.nameLayout.textViewRatingDesc.text = state.rooms.ratingName
                            binding.nameLayout.textViewHotelName.text = state.rooms.hotelName
                            binding.nameLayout.textViewHotelAddress.text = state.rooms.hotelAddress
                            binding.textViewDepartureFrom.text = state.rooms.departure
                            binding.textViewArrival.text = state.rooms.arrivalCountry
                            binding.textViewNightsCount.text = state.rooms.numberOfNights.toString()
                            binding.textViewHotelName.text = state.rooms.hotelName
                            binding.textViewRoomType.text = state.rooms.room
                            binding.textViewNutrition.text = state.rooms.nutrition
                            binding.textViewTour.text = getString(R.string.tour_sum, state.rooms.tourPrice)
                            binding.textViewFuelCharge.text = getString(R.string.fuel_sum, state.rooms.fuelCharge)
                            binding.textViewServiceCharge.text = getString(R.string.service_sum, state.rooms.serviceCharge)
                            binding.textViewTotal.text = getString(R.string.total_sum, state.rooms.tourPrice)
                            binding.payBtn.text = getString(R.string.to_pay, state.rooms.tourPrice)
                        }
                    }
                }
            }
        }
        binding.addTouristBtn.setOnClickListener {
           val exp = ExpandableListView(requireContext())
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.topToBottom = binding.expandableListView.id
            layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID
            exp.layoutParams = layoutParams
            binding.paidConstraitLayout.addView(exp)
        }
        binding.editTextPhoneNumber.addTextChangedListener {
            val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
            mask.placeholder = '*'
            mask.isShowingEmptySlots = true
            val watcher: FormatWatcher = MaskFormatWatcher(mask)
            watcher.installOn(binding.editTextPhoneNumber)
        }
        binding.editTextEmail.addTextChangedListener {
            if (it.toString().isValidEmail()) {
                binding.editTextEmail.background = resources.getDrawable(R.color.white, null)
            }
            else {
                binding.editTextEmail.setBackgroundColor(Color.parseColor("#EB5757"))
                binding.editTextEmail.alpha = 0.15f
            }
        }

        binding.payBtn.setOnClickListener {
            val travelerList = expandableListViewAdapter.travelersList.firstOrNull()
            Log.d("Person data", travelerList.toString())
                if(travelerList?.name.isNullOrEmpty() or travelerList?.surname.isNullOrEmpty()
                    or travelerList?.birthDay.isNullOrEmpty() or
                    travelerList?.citizenship.isNullOrEmpty() or
                    travelerList?.passportId.toString().isEmpty()
                    or travelerList?.issueDate.isNullOrEmpty())
                    Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show()
                else
                    findNavController().navigate(R.id.paidFragment)
            }

        expandableListViewAdapter.notifyDataSetChanged()
    }
    private fun String.isValidEmail() =
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    private fun setListViewHeight(
        listView: ExpandableListView,
        group: Int
    ) {
        val listAdapter = listView.expandableListAdapter as ExpandableListAdapter
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(
            listView.width,
            View.MeasureSpec.EXACTLY
        )
        for (i in 0 until listAdapter.groupCount) {
            val groupItem = listAdapter.getGroupView(i, false, null, listView)
            groupItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += groupItem.measuredHeight
            if (listView.isGroupExpanded(i) && i != group || !listView.isGroupExpanded(i) && i == group) {
                for (j in 0 until listAdapter.getChildrenCount(i)) {
                    val listItem = listAdapter.getChildView(
                        i, j, false, null,
                        listView
                    )
                    listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
                    totalHeight += listItem.measuredHeight
                }
            }
        }
        val params = listView.layoutParams
        var height = totalHeight + listView.dividerHeight * (listAdapter.groupCount - 1)
        if (height < 10) height = 200
        params.height = height
        listView.layoutParams = params
        listView.requestLayout()
    }
}