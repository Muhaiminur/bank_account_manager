package com.bankaccount.bankaccountmanager.utitlity

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.bankaccount.bankaccountmanager.R
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*

class Utility(var context: Context) {
    lateinit var mProgressDialog: ProgressDialog
    var gson = Gson()

    init {
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        freeMemory()
    }

    fun freeMemory() {
        System.runFinalization()
        Runtime.getRuntime().gc()
        System.gc()
    }

    //================ Show Progress Dialog ===============
    fun showProgress(isCancelable: Boolean, message: String) {
        hideProgress()
        mProgressDialog = ProgressDialog(context, R.style.AppCompatAlertDialogStyle)
        mProgressDialog.isIndeterminate = true
        mProgressDialog.setCancelable(isCancelable)
        mProgressDialog.setMessage(message)
        mProgressDialog.show()
    }

    //================ Hide Progress Dialog ===============
    fun hideProgress() {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing) {
                mProgressDialog.dismiss()
            }
        } catch (e: Exception) {
            Log.d("Error Line Number", Log.getStackTraceString(e))
        }
    }

    /*
        ================ Network available or not ===============
        */
    fun isNetworkAvailable(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = manager.activeNetworkInfo
        return info != null && info.isConnected
    }

    /*
    ================ Show Toast Message ===============
    */
    fun showToast(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun showerror() {
        Toast.makeText(
            context,
            context.resources.getString(R.string.something_went_wrong),
            Toast.LENGTH_LONG
        ).show()
    }

    /*
    =============== Set Window FullScreen ===============
    */
    fun setFullScreen() {
        val activity = context as Activity
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    /*
        ================ Get Screen Width ===============
        */
    fun getScreenRes(): HashMap<String, Int> {
        val map = HashMap<String, Int>()
        val metrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(metrics)
        val width = metrics.widthPixels
        val height = metrics.heightPixels
        map[KeyWord.SCREEN_WIDTH()] = width
        map[KeyWord.SCREEN_HEIGHT()] = height
        map[KeyWord.SCREEN_DENSITY()] = metrics.density.toInt()
        return map
    }


    /*
    ================ Log function ===============
     */
    fun logger(message: String?) {
        Log.d(context.getString(R.string.app_name), message!!)
        val currentTime = System.currentTimeMillis()
        val sdf = SimpleDateFormat("MM/dd/yyyy hh:mm:ss")
        val date = sdf.format(Date())
        //writeToFile(date+" -> "+message);
    }

    /*
    ================ Clear Text for EditText, Button, TextView ===============
    */
    fun clearText(view: Array<View?>) {
        for (v in view) {
            if (v is EditText) {
                v.setText("")
            } else if (v is Button) {
                v.text = ""
            } else if (v is TextView) {
                v.text = ""
            }
        }
    }

    /*
    ================ Hide Keyboard from Screen ===============
    */
    fun hideKeyboard(view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    /*
    ================ Show Keyboard to Screen ===============
    */
    fun showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    /*
    ================ Hide & Show Views ===============
    */
    fun hideAndShowView(views: Array<View>, view: View) {
        for (i in views.indices) {
            views[i].visibility = View.GONE
        }
        view.visibility = View.VISIBLE
    }

    fun hideViews(views: Array<View>) {
        for (i in views.indices) {
            views[i].visibility = View.GONE
        }
    }

    fun getDeviceInfo(): HashMap<String, String>? {
        val map = HashMap<String, String>()
        map["Serial"] = Build.SERIAL
        map["Model"] = Build.MODEL
        //map.put("Id", Build.ID);
        map["Id"] = Build.SERIAL
        map["Manufacture"] = Build.MANUFACTURER
        map["Type"] = Build.TYPE
        map["User"] = Build.USER
        map["Base"] = Build.VERSION_CODES.BASE.toString()
        map["Incremental"] = Build.VERSION.INCREMENTAL
        map["Board"] = Build.BOARD
        map["Brand"] = Build.BRAND
        map["Host"] = Build.HOST
        map["Version Code"] = Build.VERSION.RELEASE
        return map
    }

    fun showDialog(message: String?) {
        val screen = getScreenRes()
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
        btnOk.setOnClickListener { dialog.dismiss() }
        dialog.setCancelable(false)
        dialog.show()
    }
}