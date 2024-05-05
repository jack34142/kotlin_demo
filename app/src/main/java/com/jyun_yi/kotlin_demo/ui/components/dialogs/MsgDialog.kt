package com.jyun_yi.kotlin_demo.ui.components.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.Window
import com.jyun_yi.kotlin_demo.databinding.DialogMsgBinding

class MsgDialog(context: Context, private val msg: String): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val binding = DialogMsgBinding.inflate(LayoutInflater.from(context))
        binding.dialogMsgBg.clipToOutline = true
        binding.dialogMsgText.movementMethod = ScrollingMovementMethod()
        binding.dialogMsgClose.setOnClickListener {
            dismiss()
        }
        binding.dialogMsgText.text = msg
        setContentView(binding.root)
    }
}