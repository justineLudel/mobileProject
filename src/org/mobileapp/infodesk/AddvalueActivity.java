package org.mobileapp.infodesk;



import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddvalueActivity extends Activity {
	
	private DatabaseHelper db;
	
	private EditText edt1, edt2;
	private Button btnAdd;
	private ListView LV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addvalue);
		
		db = new DatabaseHelper(this);
		
		edt1 = (EditText)findViewById(R.id.editText1);
		edt2 = (EditText)findViewById(R.id.editText2);
		LV = (ListView)findViewById(R.id.listView1);
		
		btnAdd = (Button)findViewById(R.id.button1);
		
		btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				addValues();
				
			}
		});
	}
	
	void showUser(){
		Cursor cursor = db.getUser();
		
		if(cursor.moveToFirst()){
			String[] users = new String[(cursor.getCount())];
			int i = 0;
			do{
				users[i] = cursor.getString(cursor.getColumnIndex(DatabaseHelper.user_USER));
				i++;
			}while(cursor.moveToNext());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, users);
		LV.setAdapter(adapter);
		}
				
	}
	
	void addValues(){
		String name2 = edt1.getText().toString().trim();
		String name = edt2.getText().toString().trim();
		
		
		if(TextUtils.isEmpty(name2))
		{
			Toast.makeText(this, "enter a name", Toast.LENGTH_LONG).show();
			return;
		}
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "enter a value", Toast.LENGTH_LONG).show();
			return;
		}
		
		if(db.addACC(name2) || db.addCont(name)){
			Toast.makeText(this, "name value added", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(this, "not added. error", Toast.LENGTH_LONG).show();
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addvalue, menu);
		return true;
	}

}
