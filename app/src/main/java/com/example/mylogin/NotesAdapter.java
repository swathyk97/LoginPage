package com.example.mylogin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.viewHolder>{
    Context context;
    Activity activity;
    ArrayList<NoteModel> arrayList;
    DBHelper dbHelper;
    EditText editId, editName, editAge, editPlace, editDesignation;
    Button btnUpdate;

    public NotesAdapter(Context context,Activity activity, ArrayList<NoteModel> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
    }

    @Override
    public NotesAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotesAdapter.viewHolder holder, final int position) {
        holder.id.setText("ID:"+arrayList.get(position).get_id());
        holder.name.setText("Name:"+arrayList.get(position).getName());
        holder.place.setText("Place:"+arrayList.get(position).getPlace());
        holder.des.setText("Designation:"+arrayList.get(position).getDesignation());
        holder.age.setText("Age:"+arrayList.get(position).getAge());
        dbHelper = new DBHelper(context);

        holder.delete.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                //deleting note
                dbHelper.deleteData(arrayList.get(position).get_id());
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {
                //display edit dialog
                showDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView id,name,place,des,age;
        ImageView delete, edit;
        public viewHolder(View itemView) {
            super(itemView);
            id =  itemView.findViewById(R.id.id);
            name =  itemView.findViewById(R.id.name);
            place=itemView.findViewById(R.id.place);
            des =  itemView.findViewById(R.id.designation);
            age =  itemView.findViewById(R.id.age);
            delete = itemView.findViewById(R.id.delete);
            edit =itemView.findViewById(R.id.edit);
        }
    }

    public void showDialog(final int pos) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        dialog.setContentView(R.layout.activity_user);
        params.copyFrom(dialog.getWindow().getAttributes());
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        btnUpdate =dialog.findViewById(R.id.btn_add);
        editId = dialog.findViewById(R.id.et_id);
        editName = dialog.findViewById(R.id.et_name);
        editAge =dialog.findViewById(R.id.et_age);
        editPlace = dialog.findViewById(R.id.et_place);
        editDesignation = dialog.findViewById(R.id.et_Designation);

        editId.setText(arrayList.get(pos).get_id());
        editName.setText(arrayList.get(pos).getName());
        editAge.setText(arrayList.get(pos).getAge());
        editPlace.setText(arrayList.get(pos).getPlace());
        editDesignation.setText(arrayList.get(pos).getDesignation());


        btnUpdate.setOnClickListener(new View.OnClickListener() {;
            @Override
            public void onClick(View v) {

                dbHelper.updateData(arrayList.get(pos).get_id(), editName.getText().toString(),editPlace.getText().toString()
                         ,editDesignation.getText().toString(),editAge.getText().toString());
                    arrayList.get(pos).set_id(editId.getText().toString());
                    arrayList.get(pos).setName(editName.getText().toString());
                    arrayList.get(pos).setPlace(editPlace.getText().toString());
                    arrayList.get(pos).setDesignation(editDesignation.getText().toString());
                    arrayList.get(pos).setAge(editAge.getText().toString());
                    dialog.cancel();
                    //notify list
                    notifyDataSetChanged();

            }
        });
    }
}
