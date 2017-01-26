package com.example.ecs.myapplication;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by E C S on 22/01/2017.
 */
public class MainActivityFragment extends Fragment {
    int x=4;
    Spinner sp;
    Button b1,b2,b3,Buttonok,ButtonCancel,showdialog;
    EditText e1,tv,tv1,e;
    DbConnection db;
    int i=0;
    ArrayList<String>arr,arr2;

    ArrayAdapter<String> adapter;
    private Context context;
    //  String []arabic=new String[]{"سبحان الله", "الحمد لله", "لا اله الا الله", "الله اكبر" };
    //  String []english=new String[]{"sophan allah","al hamd li alah","la alah ala allah","allah akbr"};
    public MainActivityFragment() {
    }
String item;
    SharedPreferences sh;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_main, container, false);
        tv=(EditText)view.findViewById(R.id.tv);
        tv1=(EditText)view.findViewById(R.id.tv1);
        showdialog=(Button)view.findViewById(R.id.db);
        b1=(Button)view.findViewById(R.id.b1);
        b2=(Button)view.findViewById(R.id.b2);
        b3=(Button)view.findViewById(R.id.b3);
         sp=(Spinner) view.findViewById(R.id.sp);
        e1=(EditText)view.findViewById(R.id.e1);


       arr=  new ArrayList<>() ;
      arr.add(0,"سبحان الله");
        arr.add(1,"الحمد لله");
        arr.add(2,"لا اله الا الله");
        arr.add(3,"الله اكبر");
        final Context context;
        context=this.getActivity();
        adapter =new ArrayAdapter<String>(getActivity(),R.layout.layout_spinner,R.id.tvsp,arr);
sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                         @Override
                                         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                             if(position<=3) {
                                                 Log.i("here", String.valueOf(sp.getItemIdAtPosition(position)));
                                                 if (context != null) {
                                                     db = new DbConnection(context);
                                                     get = db.select((int) sp.getItemIdAtPosition(position));
                                                     tv.setText(get.get(0));
                                                     tv1.setText(get.get(1));
                                                 } else {
                                                     Log.i("conre", String.valueOf(context));

                                                 }
                                             }
                                             else

                                             {

                                                 tv.setText(sp.getItemAtPosition(position).toString());
                                                 tv1.setText(null);
                                             }

                                         }


                                         @Override
                                         public void onNothingSelected(AdapterView<?> parent) {

                                         }
                                     });




            showdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);

                dialog.setTitle("add another taspeh");

                e=(EditText)dialog.findViewById(R.id.e);
                Buttonok=(Button)dialog.findViewById(R.id.ok);
                ButtonCancel=(Button)dialog.findViewById(R.id.cancel);
                Buttonok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db=new DbConnection(getActivity());


                        db.insertuser_taspeh(e.getText().toString());





                        if(!adapter.isEmpty())
                        {dialog.cancel();
                        }



                    }

                });

                ButtonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
                x++;
                dialog.show();
            }
        });
        db=new DbConnection(getActivity());
        arr2=db.getUtaspeh();
        for(int i=0;i<arr2.size();i++)
        { arr.add(arr2.get(i));
        }

        adapter =new ArrayAdapter<String>(getActivity(),R.layout.layout_spinner,R.id.tvsp,arr);
        sp.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                e1.setText(Integer.toString(i));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                e1.setText(Integer.toString(i));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=0;
                e1.setText(Integer.toString(i));
            }
        });

        return  view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.

        menuInflater.inflate(R.menu.menu_main, menu);
        menu.add(Menu.NONE, 5, Menu.NONE,e.getText().toString() );

    }

    ArrayList<String> get=new ArrayList<>();

}
