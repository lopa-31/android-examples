package com.example.advancedRecyclerview

fun listQueAns(size : Int): List<Question>{
    val list = mutableListOf<Question>()
    for (i in 1..size){
        val que = when{
            i%4 == 0 -> "You are approaching a narrow bridge," +
                    " another vehicle is about to enter the bridge from" +
                    "opposite side, you should?"

            i%4 == 1 -> "While turning to a road to the left of the road " +
                    "in which you are going, you should?"

            i%4 == 2 -> "While you are driving with the head " +
                    "light in high beam during night, a vehicle approaches " +
                    "from opposite direction, you should?"

            else -> "You hold a learner's licence for motor cycle. " +
                    "Then are you allowed to carry a person with you or not?"
        }

        val ans = when{
            i%4 == 0 -> "Slow down the vehicle and let the other vehicle proceed."

            i%4 == 1 -> "Show the turn signal, keep to the left side of the road " +
                    "and turn to the left."

            i%4 == 2 -> "Dim the head light till the vehicle passes"

            else -> "Yes, only if he is instructing you to drive and holds a valid driving " +
                    "licence to drive motor cycle,else no"
        }

        list.add(Question(i, que, ans))
    }
    return list
}