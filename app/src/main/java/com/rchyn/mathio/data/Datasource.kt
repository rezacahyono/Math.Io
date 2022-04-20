package com.rchyn.mathio.data

import com.rchyn.mathio.R
import com.rchyn.mathio.model.Subject

object Datasource {

    private val imageSubject = listOf(
        R.drawable.ic_rectangle,
        R.drawable.ic_square,
        R.drawable.ic_triangle,
        R.drawable.ic_circle,
        R.drawable.ic_parallelogram,
        R.drawable.ic_rhombus
    )

    private val titleSubject = listOf(
        R.string.rectangle_title,
        R.string.square_title,
        R.string.triangle_title,
        R.string.circle_title,
        R.string.parallelogram_title,
        R.string.rhombus_title
    )


    fun getDatasetSubject(): List<Subject> {
        val data: ArrayList<Subject> = arrayListOf()
        for (i in titleSubject.indices) {
            val subject = Subject(
                imageSubject = imageSubject[i],
                title = titleSubject[i]
            )
            data.add(subject)
        }
        return data
    }
}