package be.thomasmore.logopedieproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.logopedieproject.Activities.DetailsKindActivity;
import be.thomasmore.logopedieproject.Activities.OverzichtKinderenActivity;
import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.R;

public class AdapterKind extends ArrayAdapter<Kind> {
    private final Context context;
    private final List<Kind> values;


    public AdapterKind(Context context, List<Kind> values)
    {
        super(context, R.layout.adapterkindlistviewitem, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.adapterkindlistviewitem, parent, false);

        final TextView textViewName = (TextView) rowView.findViewById(R.id.naam);

        textViewName.setText(values.get(position).toString());


        return rowView;
    }



}
