package be.thomasmore.logopedieproject.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;
import be.thomasmore.logopedieproject.R;


public class AdapterWoordInMeting extends ArrayAdapter<WoordInMeting>  {

    private final Context context;
    private final List<WoordInMeting> values;


    public AdapterWoordInMeting(Context context, List<WoordInMeting> values)
    {
        super(context, R.layout.adapter_woordinmeting_listview, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.adapter_kind_listview, parent, false);

        final TextView textViewName = (TextView) rowView.findViewById(R.id.naam);
        final WoordInMeting woordInMeting = values.get(position);




        textViewName.setText(woordInMeting.getWoordId() + ": " + woordInMeting.isJuistOfFout());


        return rowView;
    }

}
