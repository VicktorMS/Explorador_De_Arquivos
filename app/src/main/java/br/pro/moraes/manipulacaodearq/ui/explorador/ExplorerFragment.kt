package br.pro.moraes.manipulacaodearq.ui.explorador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.pro.moraes.manipulacaodearq.R
import com.google.android.material.snackbar.Snackbar
import java.io.File

class ExplorerFragment : Fragment() {

    companion object {
        fun newInstance() = ExplorerFragment()
    }

    private lateinit var viewModel: ExplorerViewModel

    private lateinit var editTextExplorerSearch: EditText
    private lateinit var buttonExplorerSearch: Button
    private lateinit var textViewExplorerStatus: TextView
    private lateinit var buttonExplorerCreateFile: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_explorer, container, false)
        editTextExplorerSearch = view.findViewById(R.id.editTextExplorerSearch)
        buttonExplorerSearch = view.findViewById(R.id.buttonExplorerSearch)
        textViewExplorerStatus = view.findViewById(R.id.textViewExplorerStatus)
        buttonExplorerCreateFile = view.findViewById(R.id.buttonExplorerCreateFile)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonExplorerSearch.setOnClickListener {
            val userSearch = editTextExplorerSearch.text.toString()
            val file = File(context?.filesDir, userSearch)
            verificaArquivo(userSearch, file)
        }
        buttonExplorerCreateFile.setOnClickListener {
            val userSearch = editTextExplorerSearch.text.toString()
            val file = File(context?.filesDir, userSearch)
            if (!file.exists()){
                file.createNewFile()
                verificaArquivo(userSearch, file)
            } else{
                Snackbar.make(view, "Arquivo já existe", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun verificaArquivo(userSearch: String, file: File) {
        val context = activity?.applicationContext
        val statusText =
            "Arquivo Pesquisado: ${userSearch}\n" +
                    "Arquivo Existe: ${file.exists()}\n" +
                    "Arquivo é Legivel: ${file.canRead()}\n" +
                    "Arquivo é Editável: ${file.canWrite()}"
        textViewExplorerStatus.setText(statusText)
    }


}