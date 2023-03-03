package com.example.sumdu_kotlin_datacollector

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.util.*
/**
 * Class that Adapts the ExpandableList to our needs using the List of Groups
 **/
class ExpandableListAdapter(private val groups: List<Group>) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return groups.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return groups[groupPosition].children.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return groups[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return groups[groupPosition].children[childPosition]
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

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val inflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null)
        }
        val group = getGroup(groupPosition) as Group
        (view as TextView).text = group.name
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val inflater = parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.child_row, null)
        }
        val textView = view?.findViewById<TextView>(R.id.child_text_view)
        if (textView != null) {
            val group = groups[groupPosition]
            when (group.name) {
                "Phone Number" -> {
                    // Set the phone number text
                    textView.text = group.children[0]
                    // Make the text clickable and open the phone app
                    textView.setOnClickListener {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + group.children[0]))
                        textView.context.startActivity(intent)
                    }
                }
                "Web URL" -> {
                    // Set the web URL text
                    textView.text = group.children[0]
                    // Make the text clickable and open the URL in a browser
                    textView.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(group.children[0]))
                        textView.context.startActivity(intent)
                    }
                }
                "Map Location" -> {
                    // Set the map coordinates text
                    val lat = group.children[0].split(",")[0]
                    val lon = group.children[0].split(",")[1]
                    textView.text = "Latitude: $lat, Longitude: $lon"

                    // Make the text clickable and open the coordinates in a map app
                    textView.setOnClickListener {
                        val uri = "geo:$lat,$lon"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                        textView.context.startActivity(intent)
                    }
                }
                else -> {
                    // Set the default text
                    textView.text = group.children[childPosition]
                }
            }
        }
        return view!!
    }


    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}