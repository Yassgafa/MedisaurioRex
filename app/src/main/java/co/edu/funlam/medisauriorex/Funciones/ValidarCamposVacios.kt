package co.edu.funlam.medisauriorex.Funciones

import android.widget.EditText

class ValidarCamposVacios {

    companion object {
        fun todosLlenos(campos: List<EditText>): Boolean {
            for (editText in campos) {
                if (editText.text.isEmpty()) {
                    return false
                }
            }
            return true
        }
    }
}