package comm.example.asus_pc.traveltalk;

        import android.os.Bundle;

class list1ImplImpl extends list1Impl {
    list1 lst;
    String[] stationeries={"Pen","Pencils","Mildliners","Fineliners","Correctors"};
    String[] desc={"These are pen","These are pencils","These are mildliners",
            "These are fineliners","These are correctors"};
    Integer[] imgid={R.drawable.pen,R.drawable.pencils, R.drawable.mildliners,
            R.drawable.fineliners, R.drawable.correctors };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);
    }
}
