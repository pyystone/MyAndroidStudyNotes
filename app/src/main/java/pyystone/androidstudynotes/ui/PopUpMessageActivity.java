package pyystone.androidstudynotes.ui;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pyystone.androidstudynotes.R;

/**
 * Created by pyysotne on 2016/01/18/0021.
 * email: pyystone@163.com
 * QQ: 862429936
 * 消息提示Activity
 */
public class PopUpMessageActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int POPUP_BOX = 1;
    private ListView mListView;
    private PopupBoxFragment mPopupBoxFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_message);
        initUI();
    }

    private void initUI() {
        mListView = (ListView) findViewById(R.id.listview);
        BaseListAdapter adapter = new BaseListAdapter(getListViewData(), this);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    public List<ListItemData> getListViewData() {
        ArrayList<ListItemData> listViewData = new ArrayList<>();
        ListItemData item = new ListItemData();
        item.mTitle = "PopUpBox";
        item.mTagId = POPUP_BOX;
        listViewData.add(item);
        return listViewData;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int tag = (int) view.getTag();
        if (tag == POPUP_BOX) {
            if (mPopupBoxFragment == null) {
                mPopupBoxFragment = PopupBoxFragment.newInstance();
            }
            replaceFragment(mPopupBoxFragment);
            mPopupBoxFragment.showPopUpBox();
        }
    }

    private void replaceFragment(Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(R.id.popUpView,fragment).commit();
    }
}
