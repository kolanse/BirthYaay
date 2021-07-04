package com.example.birthyaay.adapters.utils

import com.example.birthyaay.models.UpComingCelebrant
import com.example.birthyaay.util.DataResourceGenerator

object AdapterItemHelper {
    const val VIEW_TYPE_GROUP = 0
    const val VIEW_TYPE_PERSON = 1

    fun sortList(upComingCelebrants: MutableList<UpComingCelebrant>): MutableList<UpComingCelebrant> {
        upComingCelebrants.sortWith { upComingCelebrantOne, upComingCelebrantTwo ->
            upComingCelebrantOne!!.celebrantName!!.compareTo(upComingCelebrantTwo!!.celebrantName!!)
        }
        return upComingCelebrants
    }

    fun generateAlphabets(upComingCelebrants: MutableList<UpComingCelebrant>): MutableList<UpComingCelebrant> {

        var index = 0
        val generatedAlphabetsList = mutableListOf<UpComingCelebrant>()
        val firstCelebrant = UpComingCelebrant()

        /** Get first character */
        firstCelebrant.celebrantName = upComingCelebrants.first().celebrantName?.first().toString()

        /** set view type is header */
        firstCelebrant.viewType = VIEW_TYPE_GROUP

        generatedAlphabetsList.add(firstCelebrant)

        while (index < upComingCelebrants.lastIndex) {

            val celebrant = UpComingCelebrant()
            val currentCelebrantFirstChar = upComingCelebrants[index].celebrantName?.first()
            val nextCelebrantFirstChar = upComingCelebrants[index + 1].celebrantName?.first()

            if (currentCelebrantFirstChar == nextCelebrantFirstChar) {
                upComingCelebrants[index].viewType = VIEW_TYPE_PERSON
                generatedAlphabetsList.add(upComingCelebrants[index])
            } else {
                upComingCelebrants[index].viewType = VIEW_TYPE_PERSON
                generatedAlphabetsList.add(upComingCelebrants[index])

                celebrant.celebrantName = nextCelebrantFirstChar.toString()
                celebrant.viewType = VIEW_TYPE_GROUP
                generatedAlphabetsList.add(celebrant)
            }
            index ++
        }

        upComingCelebrants[index].viewType = VIEW_TYPE_PERSON
        generatedAlphabetsList.add(upComingCelebrants[index])
        return generatedAlphabetsList
    }

    val upComingCelebrantsWithSortedGroupAlphabets = generateAlphabets(sortList(DataResourceGenerator.upcomingCelebrants()))

}