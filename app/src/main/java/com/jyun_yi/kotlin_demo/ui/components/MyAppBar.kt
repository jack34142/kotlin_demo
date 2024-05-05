package com.jyun_yi.kotlin_demo.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.jyun_yi.kotlin_demo.R

class MyAppBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    init {
        initView(attrs)
    }

    private var canPop = false
    private var title = ""

    lateinit var btnBack: ImageButton
    private lateinit var textTitle: TextView
    private var actions: LinearLayout? = null

    private fun initView(attrs: AttributeSet?){
        inflate(context, R.layout.component_app_bar, this)
        actions = findViewById(R.id.app_bar_actions)
        btnBack = findViewById(R.id.app_bar_btn_back)
        textTitle = findViewById(R.id.app_bar_text_title)

        val ta = context.obtainStyledAttributes(attrs, R.styleable.MyAppBar)
        try {
            canPop = ta.getBoolean(R.styleable.MyAppBar_canPop, false)
            title = ta.getString(R.styleable.MyAppBar_title) ?: ""

            btnBack.visibility = if(canPop) View.VISIBLE else View.GONE
            textTitle.text = title
        } finally {
            ta.recycle()
        }
    }

    fun setCanPop(canPop: Boolean) {
        this.canPop = canPop
        btnBack.visibility = if(canPop) View.VISIBLE else View.GONE
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        if( actions == null){
            super.addView(child, index, params)
        }else{
            actions!!.addView(child, index, params)
        }
    }
}