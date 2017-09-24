package com.search.hinaikhan.newyorktimessearch.network.gson;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by hinaikhan on 9/20/17.
 */

//public class GsonRequest<T> extends Request<T>  {
//
//    private Gson mGson= new Gson();
//    private Class<T> responseType;
//    private Map<String, String> headers;
//    private final Response.Listener<T> mListener;
//
//    public GsonRequest(int method, String url, Response.ErrorListener listener, Class<T> responseType, Map<String, String> headers, Response.Listener<T> mListener) {
//        super(method, url, listener);
//        this.responseType = responseType;
//        this.headers = headers;
//        this.mListener = mListener;
//    }
//
////    @Override
////    protected Response<T> parseNetworkResponse(NetworkResponse response) {
////
////        try {
////            String json = new String(
////                    response.data,
////                    HttpHeaderParser.parseCharset(response.headers));
////            return Response.success(
////                    mGson.fromJson(json, responseType),
////                    HttpHeaderParser.parseCacheHeaders(response));
////        } catch (UnsupportedEncodingException e) {
////            return Response.error(new ParseError(e));
////        } catch (JsonSyntaxException e) {
////            return Response.error(new ParseError(e));
////        }
////    }
////        return null;
////    }
//
//    @Override
//    protected void deliverResponse(T response) {
//
//    }
//}
