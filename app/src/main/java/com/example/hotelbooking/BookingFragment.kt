package com.example.hotelbooking

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
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hotelbooking.databinding.FragmentBookingBinding
import dagger.hilt.android.AndroidEntryPoint
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

        expandableListViewAdapter.notifyDataSetChanged()

        binding.expandableListView.setOnGroupClickListener { parent, _, groupPosition, _ ->
            setListViewHeight(parent, groupPosition)
            false
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
            Toast.makeText(requireContext(), travelerList?.name, Toast.LENGTH_LONG).show()
                if(travelerList?.name.isNullOrEmpty() or travelerList?.surname.isNullOrEmpty()
                    or travelerList?.birthDay.isNullOrEmpty() or
                    travelerList?.citizenship.isNullOrEmpty() or
                    travelerList?.passportId.toString().isEmpty()
                    or travelerList?.issueDate.isNullOrEmpty())
                    Toast.makeText(requireContext(), "Не все поля заполнены", Toast.LENGTH_LONG).show()
                else
                    findNavController().navigate(R.id.paidFragment)
            }
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