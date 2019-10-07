package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ming.slowly.Play2Activity;
import com.ming.slowly.Play3Activity;
import com.ming.slowly.PlayActivity;
import com.ming.slowly.R;

public class Fragment2 extends Fragment {
    CardView cardView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.layout_fragment2,container,false);
        cardView=view.findViewById(R.id.cardview2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Play2Activity.class);
                startActivity(intent);
            }
        });
        return view;

    }
}
