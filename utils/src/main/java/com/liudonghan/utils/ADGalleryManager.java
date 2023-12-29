package com.liudonghan.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:12/29/23
 */
public class ADGalleryManager<T> {

    private List<T> data;

    public ADGalleryManager(List<T> data) {
        this.data = data;
    }

    public Builder<T> from(Context context) {
        return new Builder<>(context, data);
    }


    public static class Builder<T> {
        private Context context;
        private int layoutId;
        private ViewPager viewPager;
        private List<T> data;
        private int position = 0;

        public Builder(Context context, List<T> data) {
            this.context = context;
            this.data = data;
        }

        public Builder<T> layoutResourcesId(int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder<T> viewPager(ViewPager viewPager) {
            this.viewPager = viewPager;
            return this;
        }

        public void adapter(OnGalleryAdapterListener<T> onGalleryAdapterListener) {
            viewPager.setAdapter(new GalleryPageAdapter<T>(data) {
                @Override
                protected View getLayoutResourcesId(ViewGroup container, int position, T entity, List<T> list) {
                    View inflate = View.inflate(context, layoutId, null);
                    return onGalleryAdapterListener.getLayoutResourcesId(inflate, position, entity, list);
                }
            });
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    onGalleryAdapterListener.onPageSelected(position,data);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            viewPager.setCurrentItem(position);
        }

        public Builder<T> position(int position) {
            this.position = position;
            return this;
        }
    }

    public interface OnGalleryAdapterListener<T> {

        View getLayoutResourcesId(View inflate, int position, T entity, List<T> list);

        void onPageSelected(int position, List<T> data);
    }

    public abstract static class GalleryPageAdapter<T> extends PagerAdapter {

        private List<T> list;

        public GalleryPageAdapter(List<T> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            // TODO Auto-generated method stub
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            // TODO Auto-generated method stub
            View layoutResourcesId = getLayoutResourcesId(container, position, list.get(position), list);
            container.addView(layoutResourcesId);
            return layoutResourcesId;
        }

        protected abstract View getLayoutResourcesId(ViewGroup container, int position, T entity, List<T> list);
    }
}
