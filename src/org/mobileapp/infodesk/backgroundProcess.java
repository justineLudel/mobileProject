package org.mobileapp.infodesk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

public class backgroundProcess extends AsyncTask<String, Void, String >{
	Context context;
	AlertDialog alertDialog;
	public String accCobf;
	
	backgroundProcess(Context cxt){
		context = cxt;
	}
	
	@Override
	protected String doInBackground(String... params) {
		String type = params[0];
		//String login_url = "185.27.134.10/login.php";
		String login_url = "http://formobileapps.000webhostapp.com/login.php";
		if(type.equals("login")){
			try {
				String nameUse = params[1];
				String passUse = params[2];
				URL url = new URL(login_url);
				HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
				httpURLConnection.setRequestMethod("POST");
				httpURLConnection.setDoOutput(true);
				httpURLConnection.setDoInput(true);
				
				

				
				OutputStream outputStream = httpURLConnection.getOutputStream();
				BufferedWriter buffWrite = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
				String post_data = URLEncoder.encode("nameUse", "UTF-8")+"="+URLEncoder.encode(nameUse,"UTF-8")+"&"+URLEncoder.encode("passUse", "UTF-8")+"="+URLEncoder.encode(passUse,"UTF-8");
				buffWrite.write(post_data);
				buffWrite.flush();
				
				buffWrite.close();
				outputStream.close();
				InputStream inputStream = httpURLConnection.getInputStream();
				BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
				
				String result="";
				String line="";
				
				
				
				while((line = buffReader.readLine()) != null){
					result += line;
					accCobf = line;
				}
				buffReader.close();
				inputStream.close();
				httpURLConnection.disconnect();
				return result;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@Override
	protected void onPreExecute() {
		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle("Login status");
	}
	
	@Override
	protected void onPostExecute(String result) {
		alertDialog.setMessage(result);
		alertDialog.show();
	}
	
	
	@Override
	protected void onProgressUpdate(Void... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	
	public void confirmation(){
		
	}
	
}
