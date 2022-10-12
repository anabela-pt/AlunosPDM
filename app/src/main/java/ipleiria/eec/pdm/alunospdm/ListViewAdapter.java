package ipleiria.eec.pdm.alunospdm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements AdapterView.OnItemClickListener{

    private ArrayList<Pessoa> listaPessoas;
    private Context context;

    public ListViewAdapter(ArrayList<Pessoa> listaPessoas, Context context) {
        this.listaPessoas = listaPessoas;
        this.context = context;
    }


    @Override
    public int getCount() {
        return listaPessoas.size();
    }

    @Override
    public Object getItem(int i) {
        return listaPessoas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
        }
        // get current item to be displayed
        Pessoa currentItem = (Pessoa) getItem(i);

        // get the TextView for item number and item name
        TextView textViewItemNumber = view.findViewById(R.id.textViewNumero);
        TextView textViewItemName = view.findViewById(R.id.textViewNome);
        ImageView imageView = view.findViewById(R.id.imageView);
        //sets the text for item number and item name from the current item object
        textViewItemNumber.setText(Integer.toString(currentItem.getNumero()));
        textViewItemName.setText(currentItem.getNome());
        if (currentItem.getTipo()=='A'){
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.aluno));
        }
        else
            imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.professor));
        // returns the view for the current row
        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder al = new AlertDialog.Builder(context);
        al.setMessage(listaPessoas.get(i).getNome());
        al.setNeutralButton("Ok", null);
        al.show();
    }
}
