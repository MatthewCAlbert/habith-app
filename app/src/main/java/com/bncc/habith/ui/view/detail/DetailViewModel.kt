package com.bncc.habith.ui.view.detail

import androidx.lifecycle.ViewModel

class DetailViewModel: ViewModel() {
    lateinit var targetType: String
    var targetNum = 0
    var targetProgress = 0

    fun increaseTargetProgress(): Int{
        return if(targetProgress<targetNum){
            ++targetProgress
        }else{
            targetProgress
        }
    }

    fun decreaseTargetProgress(): Int{
        return if(targetProgress>0){
            --targetProgress
        }else{
            targetProgress
        }
    }
}