package lam.fpoly.myrelaxstar.AsyncTask.LoadTinTuc;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import lam.fpoly.myrelaxstar.Activity.ActivityLogin;
import lam.fpoly.myrelaxstar.Activity.MainActivity2;
import lam.fpoly.myrelaxstar.Adapter.MyAdapter_Rcv;
import lam.fpoly.myrelaxstar.Fragment.Diff_Fragment.Account_Fragment;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase3;
import lam.fpoly.myrelaxstar.Object.InputUser;
import lam.fpoly.myrelaxstar.Object.TinTuc;
import lam.fpoly.myrelaxstar.Object.TinTucRoom;
import lam.fpoly.myrelaxstar.R;

public class DownloadTinTuc extends AsyncTask<String,Void, List<TinTuc>> {
    FragmentActivity fragment = null;
    RecyclerView recyclerView = null;

    private int currentPage = 1;
    private int totalPage = 10;

    public DownloadTinTuc(FragmentActivity fragment, RecyclerView recyclerView) {
        this.fragment = fragment;
        this.recyclerView = recyclerView;
    }

    public List<TinTuc> list = new ArrayList<TinTuc>();
    MyAdapter_Rcv myAdapter_rcv;
    TinTucRoom tinTucRoom;

    @Override
    protected List<TinTuc> doInBackground(String... strings) {

        TinTucLoader loader = new TinTucLoader();

        // tạo url Connection để tải RSS
        String urlRss = strings[0];

        try {
            URL url = new URL(urlRss);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            if(urlConnection.getResponseCode() ==200){
                // kết nối thành công thì mới lấy luồng dữ liệu
                InputStream inputStream = urlConnection.getInputStream();
                list = loader.getTinTucList(inputStream);

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    protected void onPostExecute(List<TinTuc> tinTucs) {
        super.onPostExecute(tinTucs);

        List<String> mList = new ArrayList<>();

        Log.d("zzzzz", "onPostExecute: số lượng bài viết = " + tinTucs.size());
        for(int i = 0; i< tinTucs.size(); i++){
            Log.d("zzzzz", "Tiêu đề bài viết:  " + tinTucs.get(i).getTitle());
            Log.d("zzzzz1222222", "Nội dung:  " + tinTucs.get(i).getDescription());
            Log.d("zzzzzz333333", "SRC:  " + tinTucs.get(i).getImg());
            Log.d("zzzzzz333333", "URI:  " + tinTucs.get(i).getLink());
            mList.add(tinTucs.get(i).getTitle());
        }

        myAdapter_rcv = new MyAdapter_Rcv(new MyAdapter_Rcv.InterClickItemData() {
            @Override
            public void clickItemData(TinTuc tinTuc) {
                Intent intent = new Intent(fragment, MainActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("key",tinTuc);
                intent.putExtras(bundle);
                fragment.startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(fragment);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(fragment, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setLayoutManager(manager);
        recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(myAdapter_rcv);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBackMethod);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        setFirstData();

    }
    private void setFirstData(){
        myAdapter_rcv.setData(list);
    }

    public List<TinTuc> getList(){
        List<TinTuc> tinTucList = new ArrayList<>();
        tinTucList.addAll(list);
        return tinTucList;
    }

    ItemTouchHelper.SimpleCallback callBackMethod = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }


        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getLayoutPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                      setFirstData();

                    InputUser inputUser = MyDataBase3.getInstance(fragment).inputUser_dao().getUser();
                    try {
                        tinTucRoom = new TinTucRoom(inputUser.getName(),
                                list.get(position).getTitle(), list.get(position).getDescription()
                                , list.get(position).getLink(), list.get(position).getImg());
                    }catch (Exception e){}
                    if(!Account_Fragment.checkLogin){
                        AlertDialog.Builder builder = new AlertDialog.Builder(fragment);
                        builder.setTitle("Thông báo!");
                        builder.setMessage("Yêu cầu đang nhập");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(fragment, ActivityLogin.class);
                                fragment.startActivity(intent);
                            }
                        });
                        builder.setNegativeButton("No",null);
                        builder.show();
                        return;
                    }

                    if (isDataExist(tinTucRoom)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(fragment);
                        builder.setTitle("Thông báo!");
                        builder.setMessage("Đã yêu thích từ trước");
                        builder.setPositiveButton("Yes",null);
                        builder.show();
                        return;
                    }
                    MyDataBase.getInstance(fragment).tintuc_dao().insertData(tinTucRoom);
                    break;
            }
        }
        @Override
        public void onChildDraw (Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive){

            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_favorite_border_24)
                    .addSwipeLeftBackgroundColor(fragment.getResources().getColor(me.relex.circleindicator.R.color.abc_hint_foreground_material_light))
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private boolean isDataExist(TinTucRoom tinTucRoom){
        List<TinTucRoom> list = MyDataBase.getInstance(fragment).tintuc_dao().checkData(tinTucRoom.getTitle());
        return list != null && !list.isEmpty();
    }
}
