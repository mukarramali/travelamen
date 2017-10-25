package prashushi.travelamen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import prashushi.travelamen.utils.RecyclerAdapter;

/**
 * Created by Dell User on 3/26/2017.
 */
public class MainFragment extends Fragment {
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context=getActivity();
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager2
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        RecyclerAdapter adapter=new RecyclerAdapter(context, 5);
        RecyclerView popularList = (RecyclerView) view.findViewById(R.id.recycler_destinations);
        popularList.setLayoutManager(layoutManager1);
//        popularList.getLayoutParams().height = viewHeight;
        popularList.setAdapter(adapter);
      // System.out.println("*List Init*");
      RecyclerView packageList = (RecyclerView) view.findViewById(R.id.recycler_packages);
       packageList.setLayoutManager(layoutManager2);
//        popularList.getLayoutParams().height = viewHeight;
       packageList.setAdapter(adapter);
      // System.out.println("*List Init*");

        return view;
    }
}