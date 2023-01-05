package lam.fpoly.myrelaxstar.Fragment.Fragment_Newspaper;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import lam.fpoly.myrelaxstar.Activity.MainActivity2;
import lam.fpoly.myrelaxstar.Adapter.MyAdapter_Rcv;
import lam.fpoly.myrelaxstar.Adapter.MyAdapter_Rcv_favourites;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.InputUser;
import lam.fpoly.myrelaxstar.Object.TinTuc;
import lam.fpoly.myrelaxstar.Object.TinTucRoom;
import lam.fpoly.myrelaxstar.R;


public class Favourites_News_Fragment extends Fragment {
private RecyclerView idRcvFavoritesNew;
private List<TinTucRoom> list;
    MyAdapter_Rcv_favourites myAdapter_rcv_favourites;
    TinTucRoom tinTucRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_farourites__news_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idRcvFavoritesNew = view.findViewById(R.id.idRcvFavoritesNew);


        myAdapter_rcv_favourites = new MyAdapter_Rcv_favourites(new MyAdapter_Rcv_favourites.InterClickItemData() {
            @Override
            public void clickItemData(TinTucRoom tinTucRoom) {
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key",tinTucRoom);
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }

            @Override
            public void delete(TinTucRoom tinTucRoom) {
                clickDeleteData(tinTucRoom);
            }
        });

                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        list = new ArrayList<>();

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        idRcvFavoritesNew.addItemDecoration(itemDecoration);
        idRcvFavoritesNew.setLayoutManager(manager);
        idRcvFavoritesNew.setFocusable(false);
        idRcvFavoritesNew.setNestedScrollingEnabled(false);
        idRcvFavoritesNew.setAdapter(myAdapter_rcv_favourites);
        loadData();

    }

    private void clickDeleteData(TinTucRoom tinTucRoom) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm delete data");
        builder.setMessage("Are you sure delete ("+tinTucRoom.getTitle()+") ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDataBase.getInstance(getActivity()).tintuc_dao().deleteData(tinTucRoom);
                Toast.makeText(getActivity(), "Delete Data Successfully!", Toast.LENGTH_SHORT).show();
                loadData();
            }
        });
        builder.setNegativeButton("NO", null);
        builder.show();
    }


    private void loadData(){
        InputUser inputUser =  MyDataBase3.getInstance(getActivity()).inputUser_dao().getUser();
        try {
            list = MyDataBase.getInstance(getActivity()).tintuc_dao().getList(inputUser.getName());
            myAdapter_rcv_favourites.setData(list);
        }catch (Exception e){}
    }
}