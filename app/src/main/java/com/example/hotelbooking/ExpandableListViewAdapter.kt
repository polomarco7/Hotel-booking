package com.example.hotelbooking

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
class ExpandableListViewAdapter(private val hintDataList: List<HintData>) : BaseExpandableListAdapter() {

    val travelersList = mutableListOf<Traveler>()
    private var traveler = Traveler()

    override fun getGroupCount(): Int {
        return hintDataList.size
    }
    override fun getChildrenCount(groupPosition: Int): Int {
        val tvHintList = hintDataList[groupPosition].textViewHint
        return tvHintList.size
    }
    override fun getGroup(groupPosition: Int): Any {
        return hintDataList[groupPosition]
    }
    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        val textViewHintList = hintDataList[groupPosition].textViewHint
        return textViewHintList[childPosition]
    }
    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }
    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }
    override fun hasStableIds(): Boolean {
        return true
    }
    @SuppressLint("InflateParams")
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        // on below line we are getting our group from tech stack item
        val hintData: HintData = getGroup(groupPosition) as HintData
        // on below line we are creating a layout inflater variable to inflate our layout file.
        val inflater =
            parent!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // on below line we are inflating our layout file for our tech stack item.
        val view = inflater.inflate(R.layout.list_group, null) as View
        // on below line we are creating and initializing variable for our tech stack tv.
        val techStackTV: TextView = view.findViewById(R.id.textViewTourist)
        // on below line we are setting text for our tech stack text view.
        techStackTV.text = hintData.groupName
        // on below line returning the view.
        return view
    }
    @SuppressLint("InflateParams", "UseCompatLoadingForDrawables")
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val textViewHint: TextViewHint = getChild(groupPosition, childPosition) as TextViewHint
        val inflater =
            parent?.context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.expandle_list_item, null) as View

        val editTextName: EditText = view.findViewById(R.id.editTextName)
        editTextName.hint = textViewHint.name
        editTextName.setText(travelersList.firstOrNull()?.name)

        val editTextSurname: EditText = view.findViewById(R.id.editTextSurname)
        editTextSurname.hint = textViewHint.surname
        editTextSurname.setText(travelersList.firstOrNull()?.surname)

        val editTextBirthDate: TextView = view.findViewById(R.id.editTextBirthDate)
        editTextBirthDate.hint = textViewHint.birthDay
        editTextBirthDate.text = travelersList.firstOrNull()?.birthDay

        val editTextCitizenship: TextView = view.findViewById(R.id.editTextCitizenship)
        editTextCitizenship.hint = textViewHint.citizenship
        editTextCitizenship.text = travelersList.firstOrNull()?.citizenship

        val editTextPassId: TextView = view.findViewById(R.id.editTextPassId)
        editTextPassId.hint = textViewHint.passportId
        editTextPassId.text = travelersList.firstOrNull()?.passportId

        val editTextPassIssueId: TextView = view.findViewById(R.id.editTextPassIssueId)
        editTextPassIssueId.hint = textViewHint.issueDate
        editTextPassIssueId.text = travelersList.firstOrNull()?.issueDate

        editTextName.addTextChangedListener {
            if (it?.length!! > 1) {
                editTextName.background = parent.resources.getDrawable(R.color.white, null)
            }
            else {
                editTextName.setBackgroundColor(Color.parseColor("#EB5757"))
                editTextName.alpha = 0.15f
            }
            traveler.id = groupPosition
            traveler.name = editTextName.text.toString()
            travelersList.add(traveler)
        }
        editTextSurname.addTextChangedListener {
            if (it?.length!! > 1) {
                editTextSurname.background = parent.resources.getDrawable(R.color.white, null)
            }
            else {
                editTextSurname.setBackgroundColor(Color.parseColor("#EB5757"))
                editTextSurname.alpha = 0.15f
            }
            traveler.surname = editTextSurname.text.toString()
            travelersList.add(traveler)
        }
        editTextBirthDate.addTextChangedListener {
            if (it?.length!! > 1) {
                editTextBirthDate.background = parent.resources.getDrawable(R.color.white, null)
            }
            else {
                editTextBirthDate.setBackgroundColor(Color.parseColor("#EB5757"))
                editTextBirthDate.alpha = 0.15f
            }
            traveler.birthDay = editTextBirthDate.text.toString()
            travelersList.add(traveler)
        }
        editTextCitizenship.addTextChangedListener {
            if (it?.length!! > 1) {
                editTextCitizenship.background = parent.resources.getDrawable(R.color.white, null)
            }
            else {
                editTextCitizenship.setBackgroundColor(Color.parseColor("#EB5757"))
                editTextCitizenship.alpha = 0.15f
            }
            traveler.citizenship = editTextCitizenship.text.toString()
            travelersList.add(traveler)
        }
        editTextPassId.addTextChangedListener {
            if (it?.length!! > 1) {
                editTextPassId.background = parent.resources.getDrawable(R.color.white, null)
            }
            else {
                editTextPassId.setBackgroundColor(Color.parseColor("#EB5757"))
                editTextPassId.alpha = 0.15f
            }
            traveler.passportId = editTextPassId.text.toString()
            travelersList.add(traveler)
        }
        editTextPassIssueId.addTextChangedListener {
            if (it?.length!! > 1) {
                editTextPassIssueId.background = parent.resources.getDrawable(R.color.white, null)
            }
            else {
                editTextPassIssueId.setBackgroundColor(Color.parseColor("#EB5757"))
                editTextPassIssueId.alpha = 0.15f
            }
            traveler.issueDate = editTextPassIssueId.text.toString()
            travelersList.add(traveler)
            Log.d("Person data", travelersList.toString())
        }
        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}