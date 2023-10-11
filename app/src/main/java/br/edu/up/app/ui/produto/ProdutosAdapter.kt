package br.edu.up.app.ui.produto

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import br.edu.up.app.data.Fotos
import br.edu.up.app.data.Produto
//import br.edu.up.app.ui.produto.databinding.FragmentItemProdutoBinding
import br.edu.up.app.databinding.FragmentItemProdutoBinding

class ProdutosAdapter(
    private val produtos: List<Produto>,
    val viewModel: ProdutoViewModel
    ) :
    RecyclerView.Adapter<ProdutosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemProdutoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemProduto = produtos[position]

        val idFoto = Fotos.get(itemProduto.foto)

        holder.imgFoto.setImageResource(idFoto)
        holder.txtNome.text = itemProduto.nome
        holder.txtPreco.text = itemProduto.preco.toString()

        //clique para editar item da lista
        holder.itemView.setOnClickListener { view ->
            viewModel.editar(itemProduto)
            val action = ProdutosFragmentDirections.actionNavHomeToProdutoFragment()
            view.findNavController().navigate(action)
        }

        //clique para excluir item da lista
        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("ATENÇÃO: Tem certeza que deseja excluir?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(itemProduto)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                    //ignorar
                }
                .create()
                .show()
            true
        }
    }

    override fun getItemCount(): Int = produtos.size

    inner class ViewHolder(binding: FragmentItemProdutoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgFoto: ImageView = binding.imgFoto
        val txtNome: TextView = binding.txtNome
        val txtPreco: TextView = binding.txtPreco
    }

}