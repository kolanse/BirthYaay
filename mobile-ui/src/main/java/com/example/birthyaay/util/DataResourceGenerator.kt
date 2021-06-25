package com.example.birthyaay.util

import com.example.birthyaay.R
import com.example.birthyaay.models.CurrentCelebrant
import com.example.birthyaay.models.UpComingCelebrant

object DataResourceGenerator {

    fun upcomingCelebrants() : MutableList<UpComingCelebrant> =
        mutableListOf(
            UpComingCelebrant(
                "https://i.picsum.photos/id/1005/5760/3840.jpg?hmac=2acSJCOwz9q_dKtDZdSB-OIK1HUcwBeXco_RMMTUgfY",
                "Ola Machiavelli",
                "20. 10. 2020",
                "30"
            ),
            UpComingCelebrant(
                "https://i.picsum.photos/id/1011/5472/3648.jpg?hmac=Koo9845x2akkVzVFX3xxAc9BCkeGYA9VRVfLE4f0Zzk",
                "Samuel Garfield",
                "20. 10. 2020",
                "30"
            ),
            UpComingCelebrant(
                "https://i.picsum.photos/id/1074/5472/3648.jpg?hmac=w-Fbv9bl0KpEUgZugbsiGk3Y2-LGAuiLZOYsRk0zo4A",
                "Kome Holmes",
                "20. 10. 2020",
                "30"
            )
            ,
            UpComingCelebrant(
                "https://i.picsum.photos/id/1011/5472/3648.jpg?hmac=Koo9845x2akkVzVFX3xxAc9BCkeGYA9VRVfLE4f0Zzk",
                "Omolade Rogers",
                "20. 10. 2020",
                "30"
            ),
            UpComingCelebrant(
                "https://i.picsum.photos/id/1005/5760/3840.jpg?hmac=2acSJCOwz9q_dKtDZdSB-OIK1HUcwBeXco_RMMTUgfY",
                "Samuel Garfield",
                "20. 10. 2020",
                "30"
            ),
            UpComingCelebrant(
                "https://i.picsum.photos/id/1062/5092/3395.jpg?hmac=o9m7qeU51uOLfXvepXcTrk2ZPiSBJEkiiOp-Qvxja-k",
                "Kome Holmes",
                "20. 10. 2020",
                "30"
            )
            ,
            UpComingCelebrant(
                "https://i.picsum.photos/id/1025/4951/3301.jpg?hmac=_aGh5AtoOChip_iaMo8ZvvytfEojcgqbCH7dzaz-H8Y",
                "Omolade Rogers",
                "20. 10. 2020",
                "30"
            )
        )

    fun currentCelebrants(): MutableList<CurrentCelebrant> =
        mutableListOf(
            CurrentCelebrant(
                "https://i.picsum.photos/id/1005/5760/3840.jpg?hmac=2acSJCOwz9q_dKtDZdSB-OIK1HUcwBeXco_RMMTUgfY",
                "Victor"
            ),
            CurrentCelebrant(
                "https://i.picsum.photos/id/1062/5092/3395.jpg?hmac=o9m7qeU51uOLfXvepXcTrk2ZPiSBJEkiiOp-Qvxja-k",
                "Ola"
            ),
            CurrentCelebrant(
                "https://i.picsum.photos/id/1074/5472/3648.jpg?hmac=w-Fbv9bl0KpEUgZugbsiGk3Y2-LGAuiLZOYsRk0zo4A",
                "Niccolo"
            ),
            CurrentCelebrant(
                "https://i.picsum.photos/id/1025/4951/3301.jpg?hmac=_aGh5AtoOChip_iaMo8ZvvytfEojcgqbCH7dzaz-H8Y",
                "Da Vinci"
            ),
            CurrentCelebrant(
                "https://i.picsum.photos/id/1011/5472/3648.jpg?hmac=Koo9845x2akkVzVFX3xxAc9BCkeGYA9VRVfLE4f0Zzk",
                "Van Gogh"
            ),
        )

}