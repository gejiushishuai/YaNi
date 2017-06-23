package com.handsome.didi.Controller;

import com.handsome.didi.Base.BaseController;
import com.handsome.didi.Bean.Banner;
import com.handsome.didi.Bean.Card;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2017/2/1.
 */
public class CardController extends BaseController {

    public static CardController bannerController;

    public static CardController getInstance() {
        if (bannerController == null) {
            synchronized (CardController.class) {
                if (bannerController == null) {
                    bannerController = new CardController();
                }
            }
        }
        return bannerController;
    }

    /**
     * 查询卡券
     *
     * @param listener
     */
    public void query(final OnBmobListener listener) {
        BmobQuery<Card> query = new BmobQuery<>();
        query.setCachePolicy(mPolicy);
        query.setLimit(limit_page);
        query.findObjects(new FindListener<Card>() {
            @Override
            public void done(List<Card> list, BmobException e) {
                if (e != null) {
                    listener.onError("Server Error");
                    return;
                }
                if (list.isEmpty()) {
                    listener.onError("list is empty");
                    return;
                }
                if (listener != null) {
                    listener.onSuccess(list);
                }
            }
        });
    }
}
