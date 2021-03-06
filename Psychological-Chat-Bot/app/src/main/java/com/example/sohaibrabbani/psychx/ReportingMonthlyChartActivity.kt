package com.example.sohaibrabbani.psychx

import Model.DatabaseFilter
import android.app.DatePickerDialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.activity_reporting_monthly_chart.*
import java.text.DateFormatSymbols
import java.util.*


class ReportingMonthlyChartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporting_monthly_chart)
    }
/*
    override fun onStart() {
        super.onStart()
        val calendar = Calendar.getInstance()
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var month = calendar.get(Calendar.MONTH)
        var monthString = "01"
        var dayString = "01"
        if (month < 10)
            monthString = "0" + (month + 1).toString()
        if (day < 10)
            dayString = "0" + day.toString()

        var year = calendar.get(Calendar.YEAR)
        var date = year.toString() + "-" + monthString + "-" + day.toString()
        var userId = DatabaseFilter.getSharedPreference(this).uid
        piechart_monthly_edit_text_age.setText(date)
        PsychxWebService().getMonthWiseEmotions(this, userId, date)

    }

    fun monthListener(view: View) {
        val calendar = Calendar.getInstance()
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        var month = calendar.get(Calendar.MONTH)
        var year = calendar.get(Calendar.YEAR)

        var datePickerDialog = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, null, year, month, day)
        (datePickerDialog.datePicker as ViewGroup).findViewById<View>(Resources.getSystem().getIdentifier("day", "id", "android")).visibility = View.GONE
//        (datePickerDialog.datePicker as ViewGroup).findViewById<View>(Resources.getSystem().
//                getIdentifier("year", "id", "android")).visibility= View.GONE

        Log.d("Build Version", Build.VERSION.SDK_INT.toString() + ":" + Build.VERSION_CODES.N.toString())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->
                var mMon: String = (month + 1).toString()
                var mDay: String = dayOfMonth.toString()
                if (dayOfMonth < 10)
                    mDay = "0" + dayOfMonth
                if (month < 10) {
                    mMon = "0" + (month + 1)
                }
                var date = year.toString() + "-" + mMon + "-" + mDay
                var userId = DatabaseFilter.getSharedPreference(this).uid
                var monthString = DateFormatSymbols().months[month]
                piechart_monthly_edit_text_age.setText(monthString + " " + year.toString())
                PsychxWebService().getMonthWiseEmotions(this, userId, date)
            }
        }

        datePickerDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePickerDialog.show()

    }

    fun populatePieChart(anger: Double, disgust: Double, fear: Double,
                         joy: Double, sadness: Double, surprise: Double) {
        var pieChart = monthly_pie_chart as PieChart

        var percent_ratio = (100.0 / 360.0)
        try {
//            var array_percent = ArrayList<String>()
//            array_percent.add((anger*percent_ratio).toString() + "%")
//            array_percent.add((disgust*percent_ratio).toString() + "%")
//            array_percent.add((fear*percent_ratio).toString() + "%")
//            array_percent.add((joy*percent_ratio).toString() + "%")
//            array_percent.add((sadness*percent_ratio).toString() + "%")
//            array_percent.add((surprise*percent_ratio).toString() + "%")


            // array of graph drawing size according to design
            var total = anger + disgust + fear + joy + sadness + surprise
            var array_drawGraph = ArrayList<Float>()
            array_drawGraph.add(anger.toFloat())
            array_drawGraph.add(disgust.toFloat())
            array_drawGraph.add(fear.toFloat())
            array_drawGraph.add(joy.toFloat())
            array_drawGraph.add(sadness.toFloat())
            array_drawGraph.add(surprise.toFloat())
            array_drawGraph.add((360.0 - total).toFloat())

            // array of graph different colors
            var colors = ArrayList<Int>()
            colors.add(Color.RED)
            colors.add(Color.GREEN)
            colors.add(Color.DKGRAY)
            colors.add(Color.YELLOW)
            colors.add(Color.LTGRAY)
            colors.add(Color.CYAN)
            colors.add(Color.MAGENTA)
            var entries = ArrayList<PieEntry>()
            entries.add(PieEntry(array_drawGraph[0]))
            entries.add(PieEntry(array_drawGraph[1]))
            entries.add(PieEntry(array_drawGraph[2]))
            entries.add(PieEntry(array_drawGraph[3]))
            entries.add(PieEntry(array_drawGraph[4]))
            entries.add(PieEntry(array_drawGraph[5]))
            entries.add(PieEntry(array_drawGraph[6]))

            // initializing pie data set
            var dataset = PieDataSet(entries, "Daily Chart")
            dataset.colors = colors
            dataset.label = "Daily Chart"

            // set the data

            var data = PieData(dataset)  // initialize Piedata

            var legend = pieChart.legend
            legend.isEnabled = true
            var angerLegend = LegendEntry()
            var disgustLegend = LegendEntry()
            var fearLegend = LegendEntry()
            var joyLegend = LegendEntry()
            var sadnessLegend = LegendEntry()
            var surpriseLegend = LegendEntry()
            var noneLegend = LegendEntry()
            angerLegend.label = "Anger"
            angerLegend.formColor = Color.RED
            disgustLegend.label = "Disgust"
            disgustLegend.formColor = Color.GREEN
            fearLegend.label = "Fear"
            fearLegend.formColor = Color.DKGRAY
            joyLegend.label = "Joy"
            joyLegend.formColor = Color.YELLOW
            sadnessLegend.label = "Sadness"
            sadnessLegend.formColor = Color.LTGRAY
            surpriseLegend.label = "Surprise"
            surpriseLegend.formColor = Color.CYAN
            noneLegend.label = "None"
            noneLegend.formColor = Color.MAGENTA

            legend.setCustom(arrayOf(angerLegend, disgustLegend, fearLegend, joyLegend, sadnessLegend, surpriseLegend, noneLegend))
            pieChart.data = data
            pieChart.notifyDataSetChanged()
            pieChart.invalidate()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }*/
}
