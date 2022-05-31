package com.leahhumlelu.kotlinprogram.rolling

import com.leahhumlelu.kotlinprogram.R
import kotlin.random.Random

data class Rolling(var nextInt: Int,var imgResourceId:Int) {

    fun rollDice(){
        nextInt = Random.nextInt(6)+1
        when(nextInt){
            1->imgResourceId = R.drawable.dice_1
            2->imgResourceId = R.drawable.dice_2
            3->imgResourceId = R.drawable.dice_3
            4->imgResourceId = R.drawable.dice_4
            5->imgResourceId = R.drawable.dice_5
            6->imgResourceId = R.drawable.dice_6
        }
    }
}