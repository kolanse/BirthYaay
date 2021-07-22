package com.example.birthyaay.adapters.utils

import com.example.birthyaay.util.DataResourceGenerator
import com.example.navigation.navigation.model.Celebrant

object AdapterItemHelper {
    const val VIEW_TYPE_GROUP = 0
    const val VIEW_TYPE_PERSON = 1
    const val VIEW_TYPE_ADD_INTEREST = 0
    const val VIEW_TYPE_DETAILS_INTEREST = 1

    fun sortList(upComingCelebrants: MutableList<Celebrant>): MutableList<Celebrant> {
        upComingCelebrants.sortWith { upComingCelebrantOne, upComingCelebrantTwo ->
            upComingCelebrantOne!!.name!!.compareTo(upComingCelebrantTwo!!.name!!)
        }
        return upComingCelebrants
    }

    fun generateAlphabets(upComingCelebrants: MutableList<Celebrant>): MutableList<Celebrant> {

        var index = 0
        val generatedAlphabetsList = mutableListOf<Celebrant>()
        val firstCelebrant = Celebrant()

        /** Get first character */
        firstCelebrant.name = upComingCelebrants.first().name?.first().toString()

        /** set view type is header */
        firstCelebrant.viewType = VIEW_TYPE_GROUP

        generatedAlphabetsList.add(firstCelebrant)

        while (index < upComingCelebrants.lastIndex) {

            val celebrant = Celebrant()
            val currentCelebrantFirstChar = upComingCelebrants[index].name?.first()
            val nextCelebrantFirstChar = upComingCelebrants[index + 1].name?.first()

            if (currentCelebrantFirstChar == nextCelebrantFirstChar) {
                upComingCelebrants[index].viewType = VIEW_TYPE_PERSON
                generatedAlphabetsList.add(upComingCelebrants[index])
            } else {
                upComingCelebrants[index].viewType = VIEW_TYPE_PERSON
                generatedAlphabetsList.add(upComingCelebrants[index])

                celebrant.name = nextCelebrantFirstChar.toString()
                celebrant.viewType = VIEW_TYPE_GROUP
                generatedAlphabetsList.add(celebrant)
            }
            index ++
        }

        upComingCelebrants[index].viewType = VIEW_TYPE_PERSON
        generatedAlphabetsList.add(upComingCelebrants[index])
        return generatedAlphabetsList
    }

    val upcomingCelebrants = DataResourceGenerator.celebrants()

    val upComingCelebrantsWithSortedGroupAlphabets = generateAlphabets(sortList(upcomingCelebrants))

}