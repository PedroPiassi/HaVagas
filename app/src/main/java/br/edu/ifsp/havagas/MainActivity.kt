package br.edu.ifsp.havagas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import br.edu.ifsp.havagas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        with (activityMainBinding) {
            celularCb.setOnClickListener {
                if (celularCb.isChecked) {
                    celularLL.visibility = View.VISIBLE
                } else {
                    celularLL.visibility = View.GONE
                }
            }

            var auxPosition = 0

            formacaoSp.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected (
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    auxPosition = position
                    if (position == 0 || position == 1) {
                        formacaoLL.visibility = android.view.View.VISIBLE
                        fundMedLL.visibility = android.view.View.VISIBLE
                        GradEspLL.visibility = android.view.View.GONE
                        nestDoutLL.visibility = android.view.View.GONE

                    } else if (position == 2 || position == 3) {
                        formacaoLL.visibility = android.view.View.VISIBLE
                        fundMedLL.visibility = android.view.View.GONE
                        GradEspLL.visibility = android.view.View.VISIBLE
                        nestDoutLL.visibility = android.view.View.GONE
                    } else if (position == 4 || position == 5) {
                        formacaoLL.visibility = android.view.View.VISIBLE
                        fundMedLL.visibility = android.view.View.GONE
                        GradEspLL.visibility = android.view.View.GONE
                        nestDoutLL.visibility = android.view.View.VISIBLE
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
            salvarBt.setOnClickListener {
                var linha = ""
                val msgEmail = if (emailCb.isChecked) "Sim" else "Não"
                val typePhone = if (comercialRb.isChecked) "Comercial" else "Residencial"

                linha = "$linha Nome completo: ${nomeEt.text} \n" +
                        "E-mail: ${emailEt.text} \n" +
                        "Receber e-mail: $msgEmail \n" +
                        "Telefone: ${telefoneEt.text} \n" +
                        "Tipo telefone: $typePhone \n"

                if (celularCb.isChecked) {
                    linha += "Celular: ${celularEt.text} \n"
                } else {
                    celularEt.setText("")
                }

                if (sexoRg.checkedRadioButtonId == femininoRb.id) {
                    linha += "Sexo: Feminino \n"
                } else {
                    linha += "Sexo: Masculino \n"
                }

                linha += "Data nascimento: ${nascimentoEt.text} \n"

                if (auxPosition == 0 || auxPosition == 1) {
                    linha += "Ano formação: ${dataFormacaoEd.text} \n"
                }

                if (auxPosition == 2 || auxPosition == 3) {
                    linha += "Ano formação: ${anoConclusaoEd.text} \n" +
                            "Instituição: ${instituicaoEd.text} \n"
                }

                if (auxPosition == 4 || auxPosition == 5) {
                    linha += "Ano formação: ${anoConclusaoEd2.text} \n" +
                            "Instituição: ${instituicaoEd2.text} \n" +
                            "Titulo de monografia: ${tituloEd.text} \n" +
                            "Orientador: ${orientadorEd.text} \n"
                }

                linha += "Vagas de interesse: ${vagasEt.text} \n"

                Toast.makeText(this@MainActivity, linha, Toast.LENGTH_SHORT).show()
            }

            limparBt.setOnClickListener() {
                nomeEt.setText("")
                emailEt.setText("")
                emailCb.isChecked = false
                telefoneEt.setText("")
                comercialRb.isChecked = true

                celularCb.isChecked = false
                celularEt.setText("")
                celularLL.visibility = View.GONE

                femininoRb.isChecked = true
                nascimentoEt.setText("")

                formacaoSp.setSelection(0)

                formacaoLL.visibility = android.view.View.VISIBLE
                fundMedLL.visibility = android.view.View.VISIBLE
                GradEspLL.visibility = android.view.View.GONE
                nestDoutLL.visibility = android.view.View.GONE

                dataFormacaoEd.setText("")
                anoConclusaoEd.setText("")
                instituicaoEd.setText("")
                anoConclusaoEd2.setText("")
                instituicaoEd2.setText("")
                tituloEd.setText("")
                orientadorEd.setText("")

                vagasEt.setText("")
            }
        }
    }
}