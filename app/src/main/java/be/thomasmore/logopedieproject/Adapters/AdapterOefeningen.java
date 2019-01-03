package be.thomasmore.logopedieproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;


public class AdapterOefeningen extends ArrayAdapter<OefeningListItem>  {
    DatabaseHelper db;

    private final Context context;

    private final List<OefeningListItem> listItems;

    public AdapterOefeningen(Context context, List<OefeningListItem> listItems)
    {
        super(context, R.layout.adapter_oefening_listview, listItems);
        this.context = context;
        this.listItems = listItems;

        db = new DatabaseHelper(getContext());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_oefening_listview, parent, false);

        final TextView textView = (TextView) rowView.findViewById(R.id.naam);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.afbeelding);
        final OefeningListItem oefeningListItem = listItems.get(position);

        textView.setText(oefeningListItem.naam);
        imageView.setImageResource(
                getContext().getResources().getIdentifier(oefeningListItem.afbeelding, "drawable", getContext().getPackageName())
        );


        return rowView;
    }

}
