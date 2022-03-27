package com.example.simpleweather.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.simpleweather.R
import com.example.simpleweather.business.model.DailyWeatherModel
import com.example.simpleweather.view.*
import com.google.android.material.textview.MaterialTextView
import java.lang.StringBuilder

class MainDailyListAdapter : BaseAdapter<DailyWeatherModel>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_main_daily, parent, false)
        return DailyViewHolder (view)
    }



    @SuppressLint("NonConstantResourceId")
    inner class DailyViewHolder(view : View) : BaseViewHolder(view) {

        @BindView(R.id.item_daily_date_tv)
        lateinit var date : MaterialTextView
        @BindView(R.id.item_daily_pop_tv)
        lateinit var popRate : MaterialTextView
        @BindView(R.id.item_daily_max_temp)
        lateinit var maxTemp : MaterialTextView
        @BindView(R.id.item_daily_min_temp)
        lateinit var minTemp : MaterialTextView
        @BindView(R.id.item_daily_weather_condition_icon)
        lateinit var icon : ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        override fun bindView(position: Int) {
            mData[position].apply {
                date.text = dt.toDataFormatOf(DAY_WEEK_NAME_LONG)
                popRate.text = pop.toPercentString("'")
                maxTemp.text = StringBuilder().append(temp.max.toDegree()).append("'").toString()
                minTemp.text = StringBuilder().append(temp.min.toDegree()).append("'").toString()
                icon.setImageResource(weather[0].icon.provideIcon())
            }


        }

    }




}