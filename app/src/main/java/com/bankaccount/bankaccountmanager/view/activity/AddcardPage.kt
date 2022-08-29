package com.bankaccount.bankaccountmanager.view.activity

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bankaccount.bankaccountmanager.R
import com.bankaccount.bankaccountmanager.database.AccountRepository
import com.bankaccount.bankaccountmanager.database.BankAccount
import com.bankaccount.bankaccountmanager.databinding.ActivityAddcardPageBinding
import com.bankaccount.bankaccountmanager.databinding.ActivityMainBinding
import com.bankaccount.bankaccountmanager.utitlity.BankaccountApplication
import com.bankaccount.bankaccountmanager.utitlity.KeyWord
import com.bankaccount.bankaccountmanager.utitlity.Utility
import com.bankaccount.bankaccountmanager.view.viewmodel.AddcardviewModel
import com.bankaccount.bankaccountmanager.view.viewmodel.addcardViewModelFactory

class AddcardPage : AppCompatActivity() {
    lateinit var utility: Utility
    lateinit var context: Context
    lateinit var binding: ActivityAddcardPageBinding
    private val accountviewModel: AddcardviewModel by viewModels {
        addcardViewModelFactory((application as BankaccountApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddcardPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            context = this
            utility = Utility(context)
            accountviewModel.allaccount.observe(this) { account ->
                account.let { utility.logger("lunber" + it.size) }
            }
            binding.bankAdd.setOnClickListener {
                if (!TextUtils.isEmpty(binding.bankNumber.editableText.toString())) {
                    if (!TextUtils.isEmpty(binding.bankName.editableText.toString())) {
                        if (!TextUtils.isEmpty(binding.bankBalance.editableText.toString())) {
                            accountviewModel.insert(
                                BankAccount(
                                    binding.bankName.editableText.toString(),
                                    binding.bankNumber.editableText.toString(),
                                    binding.bankBalance.editableText.toString()
                                )
                            )
                            showDialog("Your Account is Added")
                        } else {
                            binding.bankBalance.error =
                                context.resources.getString(R.string.enter_available_balance_string)
                            binding.bankBalance.requestFocusFromTouch()
                        }
                    } else {
                        binding.bankName.error =
                            context.resources.getString(R.string.enter_account_name_string)
                        binding.bankName.requestFocusFromTouch()
                    }
                } else {
                    binding.bankNumber.error =
                        context.resources.getString(R.string.enter_account_number_string)
                    binding.bankNumber.requestFocusFromTouch()
                }
            }
        } catch (e: Exception) {
            Log.d("Error Line Number", Log.getStackTraceString(e))
        }
    }

    fun showDialog(message: String?) {
        val screen = utility.getScreenRes()
        val width = screen[KeyWord.SCREEN_WIDTH()]!!
        val height = screen[KeyWord.SCREEN_HEIGHT()]!!
        val mywidth = width / 10 * 9
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setContentView(R.layout.dialog_toast)
        val tvMessage = dialog.findViewById<TextView>(R.id.tv_message)
        val btnOk = dialog.findViewById<Button>(R.id.btn_ok)
        tvMessage.text = message
        val ll = dialog.findViewById<LinearLayout>(R.id.dialog_layout_size)
        val params = ll.layoutParams as LinearLayout.LayoutParams
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        params.width = mywidth
        ll.layoutParams = params
        btnOk.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.setCancelable(false)
        dialog.show()
    }

}