package attendance.fixnix.com.attendanceapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;


/**
 * Created by akila on 6/1/17.
 */

public class CustomList extends ArrayAdapter<String> {
    private  ArrayList<Group> group;
    private String[] names;
    private String[] desc;
    private Integer[] imageid;
    private Activity context;
    private TextView textViewName,textViewDesc;
    private ImageView image;
    int size = 0;


    public CustomList(Activity context, String[] names, String[] desc, Integer[] imageid) {
        super(context, R.layout.activity_notification, names);
        this.context = context;
        this.names = names;
        this.desc = desc;
        this.imageid = imageid;

    }

//    public CustomList(Activity context, ArrayList<Group> group){
//        super(context, R.layout.acti_listview);
//        this.context = context;
//        this.group = group;
//        this.group = new ArrayList<Group>();
//        this.group.addAll(group);
//
//
//    }



    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }


    public int getGroupCount() {
        return group.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    static class ViewHolderItem{
        TextView names,desc;
        ImageView imageid;
        public TextView textViewName;
        public TextView textViewDesc;
    }


//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolderItem holder;
//        holder = new ViewHolderItem();
//        Group group = (Group) getGroup(position);
//
//        if (convertView == null) {
//
//            LayoutInflater inf = (LayoutInflater) context
//                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
//            convertView = inf.inflate(R.layout.custom_listview, null);
//            holder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);
//            holder.textViewDesc = (TextView) convertView.findViewById(R.id.textViewDesc);
////            holder.image = (ImageView) convertView.findViewById(R.id.imageView_round);
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolderItem) convertView.getTag();
//        }
//        holder.textViewName.setText("akill");
//        holder.textViewDesc.setText("dsfdf");
////
////        try{
////            Picasso.with(context)
////                    //.load("http://res.cloudinary.com/dkvhjc3ia/image/upload/v1459163722/x9jbh4rbuasnwncvwibf.jpg")
////                    .load(group.getImage())
////                    .resize(250,200)
////                    .into(holder.image);}catch (Exception e){
////            e.printStackTrace();
////        }
//        return convertView;
//
//    }
//
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater = context.getLayoutInflater();

            View listViewItem = inflater.inflate(R.layout.custom_listview, null, true);
           textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
            textViewDesc = (TextView) listViewItem.findViewById(R.id.textViewDesc);image = (ImageView) listViewItem.findViewById(R.id.imageView);


        textViewName.setText(names[position]);
        textViewDesc.setText(desc[position]);
        image.setImageResource(imageid[position]);
        return  listViewItem;
    }
}