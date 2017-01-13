package com.org.xiuer.androidlearningmanual.Fragment.AndroidTheory;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.org.xiuer.androidlearningmanual.Adapter.AndroidTheory.HomeAdapter;
import com.org.xiuer.androidlearningmanual.R;
import com.org.xiuer.androidlearningmanual.View.CompositeView;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.Catalog;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.THomeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础理论的首页导航菜单
 */
public class HomeFragment_theory extends Fragment {

    private  static String TAG="HomeFragment_theory";

    private static final String ARG_THomeModelS = "THomeModelS";



    private List<THomeModel>tHomeModels;

    public List<THomeModel> gettHomeModels() {
        return tHomeModels;
    }

    public void settHomeModels(List<THomeModel> tHomeModels) {
        this.tHomeModels = tHomeModels;
    }





   // private OnFragmentInteractionListener mListener;

    public HomeFragment_theory() {
        // Required empty public constructor
    }

    /**
     *
     * @param tHomeModels
     * \
     * @return
     */
    public static HomeFragment_theory newInstance(ArrayList<THomeModel> tHomeModels) {
        HomeFragment_theory fragment = new HomeFragment_theory();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_THomeModelS,tHomeModels);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tHomeModels = getArguments().getParcelableArrayList(ARG_THomeModelS);

            Log.i(TAG, "onCreate: "+tHomeModels);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View contentView=inflater.inflate(R.layout.fragment_home_fragment_theory, container, false);
        GridView gridView=(GridView) contentView.findViewById(R.id.gv_home_theory);


        gridView.setAdapter(new HomeAdapter(getActivity(), tHomeModels, new HomeAdapter.ICompositeOnItemClick() {
            @Override
            public void onItemClick(CompositeView view, Catalog catalog) {

                if (view.getMainTitle().equals("UI")){
                    UIFragment_theory theory=UIFragment_theory.newInstance(view.getMainTitle());
                    theory.setmCatalog(catalog);
                    FragmentTransaction  transation= getFragmentManager().beginTransaction();
                    transation.replace(R.id.center_content_frame,theory);
                    transation.commit();

                }else if (view.getMainTitle().equals("图形动画")){
                    Log.i(TAG, "onItemClick: "+"图形动画");
                    Toast.makeText(getActivity(),"当前点击的是图形动画:"+catalog.getName(),Toast.LENGTH_SHORT).show();
                }
                else  if(view.getMainTitle().equals("数据存储")){
                    Log.i(TAG, "onItemClick: "+"数据存储");
                    Toast.makeText(getActivity(),"当前点击的是数据存储:"+catalog.getName(),Toast.LENGTH_SHORT).show();
                }
                else  if(view.getMainTitle().equals("组件")){
                    Log.i(TAG, "onItemClick: "+"组件");
                    Toast.makeText(getActivity(),"当前点击的是组件:"+catalog.getName(),Toast.LENGTH_SHORT).show();
                }
                else if(view.getMainTitle().equals("有硬件相关的")){
                    Log.i(TAG, "onItemClick: "+"与硬件相关的");
                    Toast.makeText(getActivity(),"当前点击的是与硬件相关的"+catalog.getName(),Toast.LENGTH_SHORT).show();
                }


            }
        }));

        return contentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
