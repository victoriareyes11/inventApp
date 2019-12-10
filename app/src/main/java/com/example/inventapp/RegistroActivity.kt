package com.example.inventapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.example.inventapp.utils.Constantes.Companion.EMPTY
import com.example.inventapp.utils.Constantes.Companion.INTERLIN
import com.example.inventapp.utils.Constantes.Companion.SPACE
import kotlinx.android.synthetic.main.activity_registro.*
import java.text.SimpleDateFormat
import java.util.*

class RegistroActivity : AppCompatActivity() {

    private var cal = Calendar.getInstance()
    private lateinit var fecha : String //para mo inicializar con vacio lateinit y global



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        var sexo ="masculino"
        rb_masculino.setOnClickListener {
            sexo="masculino"
        }

        rb_femenino.setOnClickListener {
            sexo="femenino"
        }
        val dataSetListener = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                cal.set(Calendar.YEAR,year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                val format ="MM/dd/yyyy"
                val sdf = SimpleDateFormat(format,Locale.US)
                    fecha =sdf.format(cal.time).toString()
                    tv_showPicker.text = fecha


            }
        }
        tv_showPicker.setOnClickListener {
            DatePickerDialog(
                this,
                dataSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        bt_registrar.setOnClickListener {
            val nombre=et_nombre.text.toString()
            val correo=et_correo.text.toString()
            val telefono=et_telefono.text.toString().toInt()
            val password=et_password.text.toString()
            val repassword=et_repassword.text.toString()
            var pasatiempos=EMPTY

            /*var sexo="Masculino"
            if(rb_masculino.isChecked)sexo="Masculino"
            else sexo="Femenino"*/

            if (cb_cine.isChecked)pasatiempos= pasatiempos+ SPACE+ getString(R.string.cine)
            if (cb_gimnasio.isChecked)pasatiempos= pasatiempos+ SPACE+ getString(R.string.gimnasio)
            if (cb_leer.isChecked)pasatiempos= pasatiempos+ SPACE+ getString(R.string.leer)
            if (cb_series.isChecked)pasatiempos= pasatiempos+ SPACE+ getString(R.string.series)


            if (nombre.isEmpty() || correo.isEmpty() || et_telefono.text.toString().isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Debe digitar todos los campos",Toast.LENGTH_SHORT).show()
            }
            else {

                tv_resultado.text = getString(R.string.nombre_lb) + SPACE+ nombre +INTERLIN+
                        getString(R.string.correo_lb) + SPACE +correo + INTERLIN+
                        getString(R.string.telefono_lb) + SPACE+telefono + INTERLIN+
                        getString(R.string.password_lb)+SPACE+ password+ INTERLIN+
                        getString(R.string.sexo)+ SPACE+ sexo+ INTERLIN+
                        getString(R.string.pasatiempos)+ SPACE + pasatiempos+ INTERLIN+
                        getString(R.string.fecha_de_nacimiento)+ SPACE + fecha
            }
        }
    }


}
