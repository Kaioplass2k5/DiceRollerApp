package com.example.diceroller

import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        val rollButton: Button = findViewById(R.id.button)
        
        rollButton.setOnClickListener {
            rollDice()
            rollDice1()
        }
        
        // Đảm bảo padding đúng khi có system bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    
    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        
        val diceImage1: ImageView = findViewById(R.id.imageView)
        
        // Tạo hiệu ứng động xoay trước
        val rotateAnimator = ObjectAnimator.ofFloat(diceImage1, "rotation", 0f, 180f)
        rotateAnimator.duration = 500
        rotateAnimator.repeatCount = 1
        
        // Thêm lắng nghe kết thúc của hiệu ứng xoay
        rotateAnimator.addListener(object : AnimatorListenerAdapter() {
            // Ghi đè phương thức onAnimationEnd
            override fun onAnimationEnd(animation: android.animation.Animator) {
                super.onAnimationEnd(animation)
                // Khi xoay kết thúc, cập nhật hình ảnh xúc xắc
                val drawableResource = when (diceRoll) {
                    1 -> R.drawable.number1
                    2 -> R.drawable.number2
                    3 -> R.drawable.number3
                    4 -> R.drawable.number4
                    5 -> R.drawable.number5
                    else -> R.drawable.number6
                }
                diceImage1.setImageResource(drawableResource)
            }
        })
        
        // Bắt đầu xoay
        rotateAnimator.start()
    }
    
    private fun rollDice1() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        
        val diceImage1: ImageView = findViewById(R.id.imageView2)
        
        // Tạo hiệu ứng động xoay trước
        val rotateAnimator = ObjectAnimator.ofFloat(diceImage1, "rotation", 0f, 180f)
        rotateAnimator.duration = 500
        rotateAnimator.repeatCount = 1
        
        // Thêm lắng nghe kết thúc của hiệu ứng xoay
        rotateAnimator.addListener(object : AnimatorListenerAdapter() {
            // Ghi đè phương thức onAnimationEnd
            override fun onAnimationEnd(animation: android.animation.Animator) {
                super.onAnimationEnd(animation)
                // Khi xoay kết thúc, cập nhật hình ảnh xúc xắc
                val drawableResource = when (diceRoll) {
                    1 -> R.drawable.number1
                    2 -> R.drawable.number2
                    3 -> R.drawable.number3
                    4 -> R.drawable.number4
                    5 -> R.drawable.number5
                    else -> R.drawable.number6
                }
                diceImage1.setImageResource(drawableResource)
            }
        })
        
        // Bắt đầu xoay
        rotateAnimator.start()
    }
    
    // Lớp Dice
    class Dice(val numSides: Int) {
        fun roll(): Int {
            return (1..numSides).random()
        }
    }
}
