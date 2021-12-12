package com.example.lemonade_app


import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    // state definitions for various state of lemonadeState
    private val CUT = "cut"
    private val SQUEEZE = "squeeze"
    private val DRINK = "drink"
    private val RESTART =  "restart"

    private var lemonadeState = "cut"

    private var lemonSize = -1
    private var squeezeCount = -1


    private var lemonTree = LemonTree()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lemonSize = lemonTree.pick()

        var lemonImage :  ImageView = findViewById(R.id.image_lemon_state)
        setViewContent()
        lemonImage.setOnClickListener {
            lemonImageClicked()
        }
    }

    // Change lemonadeState to next State
    private fun lemonImageClicked(){
        if (lemonadeState == CUT){
            lemonadeState = SQUEEZE
        }else if (lemonadeState == SQUEEZE){
            if (lemonSize == 0){
                lemonadeState = DRINK
                lemonSize = lemonTree.pick()
            }else{
                lemonSize--
            }
        }else if (lemonadeState == DRINK){
            lemonadeState = RESTART
        }else {
            lemonadeState = CUT
        }

        setViewContent()
    }

    // Set New Image and Text according to State
    private fun setViewContent(){
        var image = when(lemonadeState){
            CUT -> R.drawable.lemon_tree
            SQUEEZE -> R.drawable.lemon_squeeze
            DRINK -> R.drawable.lemon_drink
            else -> R.drawable.lemon_restart
        }
        var lemonImage :ImageView = findViewById(R.id.image_lemon_state)
        lemonImage.setImageResource(image)

        var text = when(lemonadeState){
            CUT -> R.string.lemon_cut
            SQUEEZE -> R.string.lemon_squeeze
            DRINK -> R.string.lemon_drink
            else -> R.string.lemon_restart
        }

        var lemonText : TextView = findViewById(R.id.text_lemon_state)
        lemonText.text = getResources().getString(text)
    }

}


class LemonTree{
    fun pick() : Int {
        return (2..6).random()
    }
}