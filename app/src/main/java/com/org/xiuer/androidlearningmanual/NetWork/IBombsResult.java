package com.org.xiuer.androidlearningmanual.NetWork;

import com.org.xiuer.androidlearningmanual.model.MainGuides;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;



/**
 * Created by zhangxiu on 2017/1/9.
 */
public interface IBombsResult<T>  {
     void  onSuccess(List<T>bombs );
     void  onFailed(BmobException e);
}
