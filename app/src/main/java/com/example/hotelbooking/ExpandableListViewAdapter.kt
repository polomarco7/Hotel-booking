package com.example.hotelbooking

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.EditText
import android.widget.TextView

class ExpandableListViewAdapter(private val hintDataList: List<HintData>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return hintDataList.size
    }

    // below method is use to return size of child list in our case is language list
    override fun getChildrenCount(groupPosition: Int): Int {
        val tvHintList = hintDataList[groupPosition].textViewHint
        return tvHintList.size
    }

    // on below line we are returning the group from our tech stack list.
    override fun getGroup(groupPosition: Int): Any {
        return hintDataList[groupPosition]
    }

    // below method is use to return the item for our child list
    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        // on below line we are getting our programming language list from tech stack list
        val textViewHintList = hintDataList[groupPosition].textViewHint
        // on below line e are getting item from our programming language list which we are taking from tech stack list
        return textViewHintList[childPosition]
    }

    // below method is use to get group position
    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    // below method is use to get child position.
    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    // below method is use to return true for stable ids.
    override fun hasStableIds(): Boolean {
        return true
    }

    // below method is use to inflate layout file for our group items.
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

    // below method is use to inflate layout file for our child view.
    @SuppressLint("InflateParams")
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        // on below line we are getting language from our group
        val textViewHint: TextViewHint = getChild(groupPosition, childPosition) as TextViewHint
        // on below line we are creating a variable for our inflater.
        val inflater =
            parent?.context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        // on below line we are inflating a layout file for programming language list item.
        val view = inflater.inflate(R.layout.expandle_list_item, null) as View
        // on below line we are creating and initializing our text view for programming language.
        val editTextName: EditText = view.findViewById(R.id.editTextName)
        editTextName.hint = textViewHint.name
        val editTextSurname: EditText = view.findViewById(R.id.editTextSurname)
        editTextSurname.hint = textViewHint.surname
        val editTextBirthDate: TextView = view.findViewById(R.id.editTextBirthDate)
        editTextBirthDate.hint = textViewHint.birthDay
        val editTextCitizenship: TextView = view.findViewById(R.id.editTextCitizenship)
        editTextCitizenship.hint = textViewHint.citizenship
        val editTextPassId: TextView = view.findViewById(R.id.editTextPassId)
        editTextPassId.hint = textViewHint.passportId
        val editTextPassIssueId: TextView = view.findViewById(R.id.editTextPassIssueId)
        editTextPassIssueId.hint = textViewHint.issueDate
        // on below line returning the view.
        return view
    }

    // below method is use to specify weather the child item will be selectable or not
    // in our case we are specifying it as selectable.
    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }}