package com.arthpatel.unitconverterapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.round
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var numInFeetEditText: EditText
    private lateinit var convertToTextView: TextView
    private lateinit var selectUnitRadioGroup: RadioGroup
    var unitvalue = 0
    var mediaPlayer=MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numInFeetEditText = findViewById(R.id.numinfeet_edit_text)
        convertToTextView = findViewById(R.id.final_ans_text_view)
        selectUnitRadioGroup = findViewById(R.id.convert_radio_group)
        registerForContextMenu(numInFeetEditText)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?,
                                     v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean{
        return when (item.itemId) {
            R.id.add_one -> {
                unitvalue = numInFeetEditText.text.toString().toInt()
                unitvalue+=10
                Toast.makeText(applicationContext,"10 Added To The Entered Value",Toast.LENGTH_LONG).show()
                true
            }
            R.id.subtract_one -> {
                unitvalue = numInFeetEditText.text.toString().toInt()
                unitvalue-=10
                Toast.makeText(applicationContext,"10 Subtracted To The Entered Value",Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun convertClick(view: View) {

        var numFeetFloat = if (unitvalue>0)
        { unitvalue.toFloat() }
        else { numInFeetEditText.text.toString().toFloat() }

        val convertMultiplier = when (selectUnitRadioGroup.checkedRadioButtonId) {
            R.id.inch_radio_button -> 12.00
            R.id.cm_radio_button -> 30.48
            else -> 0.33
        }

        val finalAns = round(convertMultiplier * numFeetFloat*100)/100
        convertToTextView.text = "Converted Value : $finalAns"

        val dialog = WarningDialogFragment()
        dialog.show(supportFragmentManager, "warningDialog")

        Toast.makeText(this,"Hurray, Button Clicked!",Toast.LENGTH_SHORT).show()
        mediaPlayer = MediaPlayer.create(this, R.raw.single_clap_sound)
        mediaPlayer.start()
    }
}