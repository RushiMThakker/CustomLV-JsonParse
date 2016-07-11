package service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Astound Rushi on 04-07-2016.
 */
public class WebService
{
    DefaultHttpClient hc=new DefaultHttpClient();
    HttpResponse hr=null;
    HttpEntity Entity=null;

    public String getJSONFROMURL(String URL)
    {
        String Response=null;
        HttpPost hp=new HttpPost(URL);
        try {
            hr = hc.execute(hp);
            Entity = hr.getEntity();
             Response= EntityUtils.toString(Entity);

        } catch (Exception networkException) {
            networkException.printStackTrace();
        }

        return Response;
    }
}
