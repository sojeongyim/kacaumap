package com.kacau.kacaumap;

import android.app.ProgressDialog;

import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;

import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.ListAdapter;

import android.widget.ListView;

import android.widget.SimpleAdapter;

import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;

import org.json.JSONException;

import org.json.JSONObject;


import java.io.BufferedReader;


import java.io.InputStream;

import java.io.InputStreamReader;


import java.io.OutputStream;

import java.net.HttpURLConnection;

import java.net.URL;

import java.util.ArrayList;

import java.util.HashMap;

import static android.R.attr.tag;


public class Search extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public String inputPurpose = "";
    private static final String TAG_JSON = "webnautes";
    private static final String TAG_Building = "building";
    private static final String TAG_RoomNum = "roomNum";
    private static final String TAG_Name = "name";
    private static final String TAG_Purpose = "purpose";
    private static final String TAG_Dept = "dept";
    private static final String TAG_Telephone = "telephone";

    private static String TAG = "phpquerytest";
    ArrayList<HashMap<String, String>> mArrayList;
    String mJsonString;
    ListView mListViewList;
    EditText mEditTextSearchKeyword;
    private TextView mTextViewResult;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextViewResult = (TextView) findViewById(R.id.textView_main_result);

        mListViewList = (ListView) findViewById(R.id.listView_main_list);

        mEditTextSearchKeyword = (EditText) findViewById(R.id.editSearch);

        Button button_search = (Button) findViewById(R.id.searchbtn);
        Button button_1 = (Button) findViewById(R.id.btn1);
        Button button_2 = (Button) findViewById(R.id.btn2);
        Button button_3 = (Button) findViewById(R.id.btn3);
        Button button_4 = (Button) findViewById(R.id.btn4);
        Button button_5 = (Button) findViewById(R.id.btn5);
        Button button_6 = (Button) findViewById(R.id.btn6);
        Button button_7 = (Button) findViewById(R.id.btn7);
        Button button_8 = (Button) findViewById(R.id.btn8);

        String SearchKeywordOnMap = null;
        Intent intent = getIntent();
        SearchKeywordOnMap = intent.getStringExtra("SearchKeywordOnMap");
        mEditTextSearchKeyword.setText(SearchKeywordOnMap);

        if (SearchKeywordOnMap != null) {
            GetData task = new GetData();
            task.execute(SearchKeywordOnMap);
        }

        button_search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                task.execute(mEditTextSearchKeyword.getText().toString());

                inputPurpose = "";

            }

        });

        button_1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                //inPutData= ((EditText)(findViewById(R.id.editSearch))).getText().toString();
                inputPurpose = "학생지원";

                task.execute(mEditTextSearchKeyword.getText().toString());
            }

        });

        button_2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                //inPutData= ((EditText)(findViewById(R.id.editSearch))).getText().toString();
                inputPurpose = "강의실";

                task.execute(mEditTextSearchKeyword.getText().toString());
            }

        });

        button_3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                //inPutData= ((EditText)(findViewById(R.id.editSearch))).getText().toString();
                inputPurpose = "실습실";

                task.execute(mEditTextSearchKeyword.getText().toString());
            }

        });

        button_4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                //inPutData= ((EditText)(findViewById(R.id.editSearch))).getText().toString();
                inputPurpose = "연구실";

                task.execute(mEditTextSearchKeyword.getText().toString());
            }

        });

        button_5.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                inputPurpose = "학생활동실";

                task.execute(mEditTextSearchKeyword.getText().toString());
            }

        });

        button_6.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                inputPurpose = "열람실";

                task.execute(mEditTextSearchKeyword.getText().toString());
            }

        });

        button_7.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                inputPurpose = "편의시설";

                task.execute(mEditTextSearchKeyword.getText().toString());
            }

        });

        button_8.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                mArrayList.clear();

                GetData task = new GetData();

                inputPurpose = "기타";

                task.execute(mEditTextSearchKeyword.getText().toString());
            }

        });


        mListViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = String.valueOf(parent.getItemAtPosition(position));
//                Toast.makeText(Search.this, item, Toast.LENGTH_SHORT).show();

                String[] searchPOI = {mArrayList.get(position).get(TAG_Building), mArrayList.get(position).get(TAG_RoomNum)};

                Intent intent = new Intent(Search.this, ResultMap.class);
                intent.putExtra("POI", searchPOI);
                startActivity(intent);

            }
        });
        mArrayList = new ArrayList<>();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showResult() {

        Log.e("Result", "Result");
        Log.e("mJson", mJsonString);

        try {

            JSONObject jsonObject = new JSONObject(mJsonString);

            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            Log.e("try", "try");

            for (int i = 0; i < jsonArray.length(); i++) {

                Log.e("hashmap", "hashmap");

                JSONObject item = jsonArray.getJSONObject(i);


                String building = item.getString(TAG_Building);

                String roomNum = item.getString(TAG_RoomNum);

                String name = item.getString(TAG_Name);

                String purpose = item.getString(TAG_Purpose);

                String dept = item.getString(TAG_Dept);
                if(dept.equals("null")) dept = "";

                String telephone = item.getString(TAG_Telephone);
                if(telephone.equals("null")) telephone = "";


                HashMap<String, String> hashMap = new HashMap<>();

                hashMap.put(TAG_Building, building);

                hashMap.put(TAG_RoomNum, roomNum);

                hashMap.put(TAG_Name, name);

                hashMap.put(TAG_Purpose, purpose);

                hashMap.put(TAG_Dept, dept);

                hashMap.put(TAG_Telephone, telephone);

                mArrayList.add(hashMap);

            }


            ListAdapter adapter = new SimpleAdapter(

                    Search.this, mArrayList, R.layout.item_list,

                    new String[]{TAG_Building, TAG_RoomNum, TAG_Name, TAG_Purpose, TAG_Dept, TAG_Telephone},

                    new int[]{R.id.textView_list_building, R.id.textView_list_roomNum, R.id.textView_list_name,
                            R.id.textView_list_purpose, R.id.textView_list_dept, R.id.textView_list_telephone}//gateNum 추가 안함

            );

            mListViewList.setAdapter(adapter);


        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }


    }

    private class GetData extends AsyncTask<String, Void, String> {


        ProgressDialog progressDialog;

        String errorString = null;

        @Override

        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = ProgressDialog.show(Search.this,

                    "Please Wait", null, true, true);

        }

        @Override

        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            progressDialog.dismiss();

            mTextViewResult.setText(result);

            Log.d(TAG, "response - " + result);

            if (result == null) {

                mTextViewResult.setText(errorString);

            } else {

                mJsonString = result;

                showResult();
                Log.e("onPost", "onPostElse");

            }

        }

        @Override

        protected String doInBackground(String... params) {

            String searchKeyword = params[0];

            String serverURL = "http://hyeonixd.cafe24.com/searchQuery.php";

            //String postParameters = "keyword=" + searchKeyword;


            StringBuffer buffer = new StringBuffer();

            buffer.append("keyword").append("=").append(searchKeyword).append("&");
            buffer.append("purpose").append("=").append(inputPurpose);


            try {

                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);

                httpURLConnection.setConnectTimeout(5000);

                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoInput(true);

                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();

                outputStream.write(buffer.toString().getBytes("UTF-8"));
                //요기 부분이 서버로 값을 전송하는 부분

                outputStream.flush();

                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();

                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;

                if (responseStatusCode == HttpURLConnection.HTTP_OK) {

                    inputStream = httpURLConnection.getInputStream();

                } else {

                    inputStream = httpURLConnection.getErrorStream();

                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();

                String line;

                while ((line = bufferedReader.readLine()) != null) {

                    sb.append(line);
                }

                bufferedReader.close();

                return sb.toString().trim();

            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                errorString = e.toString();

                return null;

            }

        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            Intent intent = new Intent(Search.this, Search.class);
            startActivity(intent);
        } else if (id == R.id.nav_navigation) {
            Intent intent = new Intent(Search.this, Navigation.class);
            startActivity(intent);
        } else if (id == R.id.nav_adminlogin) {
            Intent intent = new Intent(Search.this, ManagerLogin.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
