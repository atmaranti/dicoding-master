package comm.example.asus_pc.traveltalk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Array
    public int[] slide_images = {
      R.drawable.bg1,
      R.drawable.bg2,
      R.drawable.bg3
    };

    public String[] slide_heading = {
      "EAT",
      "SLEEP",
      "CODE"
    };

    public String[] slide_desc = {
            "halo semua kelek" + "aliqua",
            "halo semua kelek" + "aliqua",
            "halo semua kelek" + "aliqua"

    };

    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImg = (ImageView) view.findViewById(R.id.slideImg);
        TextView slideHeading = (TextView) view.findViewById(R.id.slideHeading);
        TextView slideDesc = (TextView) view.findViewById(R.id.slideDesc);

        slideImg.setImageResource(slide_images[position]);
        slideHeading.setText(slide_heading[position]);
        slideDesc.setText(slide_desc[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
