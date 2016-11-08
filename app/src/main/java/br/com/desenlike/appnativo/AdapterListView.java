package br.com.desenlike.appnativo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by erick on 08/09/2016.
 */
public class AdapterListView extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ItemListView> itens;

    public AdapterListView(Context context, List<ItemListView> itens) {
        //Itens do listview
        this.itens = itens;
        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return itens.size();
    }
    public ItemListView getItem(int position) {
        return itens.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;
        //se a view estiver nula (nunca criada), inflamos o layout nela.
        if (view == null) {
            //infla o layout para podermos pegar as views
            view = mInflater.inflate(R.layout.itemlistview, null);

            //cria um item de suporte para não precisarmos sempre
            //inflar as mesmas informacoes
            itemHolder = new ItemSuporte();
            itemHolder.nome = ((TextView) view.findViewById(R.id.nomelv));
            itemHolder.sobrenome = ((TextView) view.findViewById(R.id.sobrenomelv));
            itemHolder.cpf = ((TextView) view.findViewById(R.id.cpflv));

            //define os itens na view;
            view.setTag(itemHolder);
        } else {
            //se a view já existe pega os itens.
            itemHolder = (ItemSuporte) view.getTag();
        }

        //pega os dados da lista
        //e define os valores nos itens.
        ItemListView item = itens.get(position);
        itemHolder.nome.setText(item.getNome());
        itemHolder.sobrenome.setText(item.getSobrenome());
        itemHolder.cpf.setText(item.getCpf());

        //retorna a view com as informações
        return view;
    }

    /**
     * Classe de suporte para os itens do layout.
     */
    private class ItemSuporte {
        TextView nome;
        TextView sobrenome;
        TextView cpf;
    }
}
