package com.example.birthyaay.util

import com.example.birthyaay.R
import com.example.birthyaay.models.ExploreGift
import com.example.navigation.navigation.model.Celebrant
import com.example.navigation.navigation.model.Content
import com.example.navigation.navigation.model.ContentType

object DataResourceGenerator {

    val interestsList = mutableListOf<String>()
    val giftsList = mutableListOf<String>()
    val notSuggestedGiftList = mutableListOf<String>()
    val addedSuggestedGifts = mutableListOf<String>()
    val pictureStorage = mutableListOf<String>()

    val notSuggestedList = mutableListOf<String>()
    val addedSuggestedInterests = mutableListOf<String>()
    var listener: Int = 0

    const val CHECKED_GIFT: Int = 1
    const val UNCHECKED_GIFT: Int = 0

    const val CHECKED_INTEREST: Int = 11
    const val UNCHECKED_INTEREST: Int = 10


    fun celebrants(): MutableList<Celebrant> =
        mutableListOf(
            Celebrant(
                name = "Ichabod Victor",
                phoneNumber = "08110000000",
                email = "celebrant@birthyaay.ca",
                dateOfBirth = "01-01-1990",
                daysLeftToBirthday = "30",
                interests = listOf(
                    Content("Games", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Music", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Photography", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Books", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Arts", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_interest,
                    )
                ),
                gifts = listOf(
                    Content("Cake", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Shoes", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Headsets", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Mobile Phone", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Books", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_gift_boxes
                    )
                ),
                image = listOf("https://i.picsum.photos/id/1005/5760/3840.jpg?hmac=2acSJCOwz9q_dKtDZdSB-OIK1HUcwBeXco_RMMTUgfY"),
                note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                viewType = -1
            ),
            Celebrant(
                name = "Ola Machiavelli",
                phoneNumber = "08110000000",
                email = "celebrant@birthyaay.ca",
                dateOfBirth = "01-01-1990",
                daysLeftToBirthday = "30",
                interests = listOf(
                    Content("Games", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Music", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Photography", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Books", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Arts", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_interest,
                    )
                ),
                gifts = listOf(
                    Content("Cake", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Shoes", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Headsets", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Mobile Phone", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Books", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_gift_boxes
                    )
                ),
                image = listOf("https://i.picsum.photos/id/1062/5092/3395.jpg?hmac=o9m7qeU51uOLfXvepXcTrk2ZPiSBJEkiiOp-Qvxja-k"),
                note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                viewType = -1
            ),
            Celebrant(
                name = "Niccolo Newton",
                phoneNumber = "08110000000",
                email = "celebrant@birthyaay.ca",
                dateOfBirth = "01-01-1990",
                daysLeftToBirthday = "30",
                interests = listOf(
                    Content("Games", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Music", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Photography", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Books", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Arts", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_interest,
                    )
                ),
                gifts = listOf(
                    Content("Cake", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Shoes", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Headsets", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Mobile Phone", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Books", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_gift_boxes
                    )
                ),
                image = listOf("https://i.picsum.photos/id/1074/5472/3648.jpg?hmac=w-Fbv9bl0KpEUgZugbsiGk3Y2-LGAuiLZOYsRk0zo4A"),
                note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                viewType = -1
            ),
            Celebrant(
                name = "Da Vinci",
                phoneNumber = "08110000000",
                email = "celebrant@birthyaay.ca",
                dateOfBirth = "01-01-1990",
                daysLeftToBirthday = "30",
                interests = listOf(
                    Content("Games", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Music", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Photography", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Books", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Arts", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_interest,
                    )
                ),
                gifts = listOf(
                    Content("Cake", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Shoes", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Headsets", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Mobile Phone", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Books", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_gift_boxes
                    )
                ),
                image = listOf("https://i.picsum.photos/id/1025/4951/3301.jpg?hmac=_aGh5AtoOChip_iaMo8ZvvytfEojcgqbCH7dzaz-H8Y"),
                note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                viewType = -1
            ),
            Celebrant(
                name = "Van Gogh",
                phoneNumber = "08110000000",
                email = "celebrant@birthyaay.ca",
                dateOfBirth = "01-01-1990",
                daysLeftToBirthday = "30",
                interests = listOf(
                    Content("Games", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Music", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Photography", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Books", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content("Arts", false, ContentType.INTEREST, R.drawable.ic_interest),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_interest,
                    )
                ),
                gifts = listOf(
                    Content("Cake", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Shoes", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Headsets", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Mobile Phone", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content("Books", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
                    Content(
                        "Not Suggested? Add yours...",
                        false,
                        ContentType.NEUTRAL,
                        R.drawable.ic_gift_boxes
                    )
                ),
                image = listOf("https://i.picsum.photos/id/1011/5472/3648.jpg?hmac=Koo9845x2akkVzVFX3xxAc9BCkeGYA9VRVfLE4f0Zzk"),
                note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                viewType = -1
            ),
        )

    fun provideInterests(): MutableList<Content> =
        mutableListOf(
            Content("Games", false, ContentType.INTEREST, R.drawable.ic_interest),
            Content("Music", false, ContentType.INTEREST, R.drawable.ic_interest),
            Content("Photography", false, ContentType.INTEREST, R.drawable.ic_interest),
            Content("Books", false, ContentType.INTEREST, R.drawable.ic_interest),
            Content("Arts", false, ContentType.INTEREST, R.drawable.ic_interest),
            Content(
                "Not Suggested? Add yours...",
                false,
                ContentType.NEUTRAL,
                R.drawable.ic_interest,
            )
        )

    fun provideGifts(): MutableList<Content> =
        mutableListOf(
            Content("Cake", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
            Content("Shoes", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
            Content("Headsets", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
            Content("Mobile Phone", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
            Content("Books", false, ContentType.GIFT, R.drawable.ic_gift_boxes),
            Content(
                "Not Suggested? Add yours...",
                false,
                ContentType.NEUTRAL,
                R.drawable.ic_gift_boxes
            )
        )

    fun provideExploreGift(): MutableList<ExploreGift> =
        mutableListOf(
            ExploreGift(
                giftImage = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixid=MnwyNDkyNzB8MHwxfHNlYXJjaHwyfHxwcm9kdWN0fGVufDB8fHx8MTYyNzI4OTYwMQ&ixlib=rb-1.2.1",
                giftTitle = "HeadPhone"
            ),
            ExploreGift(
                giftImage = "https://images.unsplash.com/photo-1572635196237-14b3f281503f?ixid=MnwyNDkyNzB8MHwxfHNlYXJjaHw1fHxwcm9kdWN0fGVufDB8fHx8MTYyNzI4OTYwMQ&ixlib=rb-1.2.1",
                giftTitle = "HeadPhone"
            ),
            ExploreGift(
                giftImage = "https://images.unsplash.com/photo-1487700160041-babef9c3cb55?ixid=MnwyNDkyNzB8MHwxfHNlYXJjaHw2fHxwcm9kdWN0fGVufDB8fHx8MTYyNzI4OTYwMQ&ixlib=rb-1.2.1",
                giftTitle = "HeadPhone"
            ),
            ExploreGift(
                giftImage = "https://images.unsplash.com/photo-1527176930608-09cb256ab504?ixlib=rb-1.2.1",
                giftTitle = "HeadPhone"
            ),
            ExploreGift(
                giftImage = "https://images.unsplash.com/photo-1497250681960-ef046c08a56e?ixlib=rb-1.2.1",
                giftTitle = "HeadPhone"
            ),
            ExploreGift(
                giftImage = "https://images.unsplash.com/photo-1553456558-aff63285bdd1?ixid=MnwyNDkyNzB8MHwxfHNlYXJjaHw3fHxwcm9kdWN0fGVufDB8fHx8MTYyNzI4OTYwMQ&ixlib=rb-1.2.1",
                giftTitle = "HeadPhone"
            ),
            ExploreGift(
                giftImage = "https://images.unsplash.com/photo-1503602642458-232111445657?ixid=MnwyNDkyNzB8MHwxfHNlYXJjaHw4fHxwcm9kdWN0fGVufDB8fHx8MTYyNzI4OTYwMQ&ixlib=rb-1.2.1",
                giftTitle = "HeadPhone"
            )
        )

}