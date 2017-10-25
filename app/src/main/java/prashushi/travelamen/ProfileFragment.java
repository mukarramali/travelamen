package prashushi.travelamen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import prashushi.travelamen.utils.Params;

/**
 * Created by Dell User on 3/29/2017.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    Context context;
    Params params;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context=getActivity();
        params=new Params(context);
        init(view);
        view.findViewById(R.id.action_logout).setOnClickListener(this);
        return view;
    }

    private void init(View view) {
        TextView name, email, phone;
        name= (TextView) view.findViewById(R.id.tv_name);
        email= (TextView) view.findViewById(R.id.tv_email);
        phone= (TextView) view.findViewById(R.id.tv_phone);
        name.setText(params.getName());
        email.setText(params.getEmail());
        phone.setText(params.getPhone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_logout:
                params.clear();
                startActivity(new Intent(context, LoginActivity.class));
                ((Activity)context).finish();
                break;
        }
    }
}