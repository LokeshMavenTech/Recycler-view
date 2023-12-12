package app.scrollfrom.contactsapp;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel>arrContacts;
    RecyclerContactAdapter(Context context, ArrayList<ContactModel>arrContacts){
      this.context=context;
      this.arrContacts=arrContacts;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.contacts_card,parent,false);
       ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//       holder.imageContact.setImageResource(arrContacts.get(position).img);
        holder.txtName.setText(arrContacts.get(position).name);
        holder.txtNumber.setText(arrContacts.get(position).number);
    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtNumber;
        ImageView imageContact;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.txtName);
            txtNumber=itemView.findViewById(R.id.txtNumber);
//            imageContact=itemView.findViewById(R.id.imageContact);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.add_update);
                    EditText edtName = dialog.findViewById(R.id.edtName);
                    EditText edtNumber= dialog.findViewById(R.id.edtNumber);
                    Button btnAction= dialog.findViewById(R.id.btnAction);

                    TextView txtTitel = dialog.findViewById(R.id.txtTitel);
                    txtTitel.setText("Update Contact");
                    btnAction.setText("Update");
                  edtName.setText(arrContacts.get(getAdapterPosition()).name);
                  edtNumber.setText(arrContacts.get(getAdapterPosition()).number);


                    btnAction.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String name = "", number = "";
                            if (!edtName.getText().toString().equals("")) {
                                name = edtName.getText().toString();
                            }
                            else {
                                Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show();
                            }

                            if (!edtNumber.getText().toString().equals("")) {
                                number = edtNumber.getText().toString();
                            } else {
                                Toast.makeText(context, "Please Enter Number", Toast.LENGTH_SHORT).show();
                            }
                            arrContacts.set(getAdapterPosition(),new ContactModel(name,number));
                            notifyItemChanged(getAdapterPosition());
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }
}
