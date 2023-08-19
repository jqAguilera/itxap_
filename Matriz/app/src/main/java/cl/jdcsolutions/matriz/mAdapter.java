package cl.jdcsolutions.matriz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class mAdapter extends BaseAdapter {

    private int[][] matriz;
    private Context context;

    public mAdapter(int[][] matriz) {
        this.matriz = matriz;
    }

    @Override
    public int getCount() {
        return matriz.length * matriz[0].length;
    }

    @Override
    public Object getItem(int pos) {
        int fila = pos / matriz[0].length;
        int columna = pos % matriz[0].length;
        return matriz[fila][columna];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            context = parent.getContext();
            convertView = new TextView(context);
            ((TextView) convertView).setTextSize(18);
        }

        int fila = pos / matriz[0].length;
        int columna = pos % matriz[0].length;
        ((TextView) convertView).setText(String.valueOf(matriz[fila][columna]));

        return convertView;
    }
}
