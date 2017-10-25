package prashushi.travelamen.utils;

/**
 * Created by Dell User on 9/2/2016.
 */

        import android.annotation.TargetApi;
        import android.content.Context;
        import android.os.Build;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.RecyclerView.Adapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.View.OnClickListener;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;

        import prashushi.travelamen.R;
        import prashushi.travelamen.model.Post;

public class RecyclerAdapter extends Adapter<RecyclerAdapter.ViewHolder> implements OnClickListener {

    private Context mContext;
    Post[] posts;
    int size=0;
    public RecyclerAdapter(Context context, int size) {
        mContext = context;
        this.size=size;
        init(size);
    }

    private void init(int size) {
        posts=new Post[size];
        String[] urls=new String[]{"http://travelamen.com/wp-content/uploads/2017/03/IMG_1553-1.jpg"};
        for (int i=0;i<size;i++)
        {
         posts[i]=new Post(i+"");
         posts[i].setTitle("Title "+(i+1));
         posts[i].setUrl(urls[0]);
        }
        System.out.println("initialized1");
    }


    @Override
    public int getItemCount() {
            return size;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_popular, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView tv_title= (TextView) holder.itemView.findViewById(R.id.title);
        tv_title.setText(posts[position].getTitle());


        ImageView im= (ImageView) holder.itemView.findViewById(R.id.im_icon);
        Picasso.with(mContext)
                .load(posts[position].getUrl())
                .resize(150, 150)
                .placeholder(R.drawable.travelamen_ph)
                .error(R.drawable.refresh)
                .into(im);
        System.out.println("XXX:"+posts[position].getTitle());
        System.out.println("initialized2");
    }

    @Override
    public void onClick(View v) {

    }

    // Not use static
    public class ViewHolder extends RecyclerView.ViewHolder {
        int i=0;
        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    i=getAdapterPosition();

                }
            });
        }
    }
}